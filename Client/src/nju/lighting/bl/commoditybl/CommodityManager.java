package nju.lighting.bl.commoditybl;

import javafx.scene.image.Image;
import nju.lighting.bl.documentbl.DocInfo;
import nju.lighting.bl.documentbl.DocInfoImpl;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.utils.CommodityPathParser;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.bl.utils.FuzzySeekingHelper;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import shared.CommodityBuildInfo;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.OPType;
import shared.Result;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.List;

enum CommodityManager {
    INSTANCE;

    private CommodityCategoriesTree commodityTree;
    private CommodityDataService dataService;
    private Logger logger;
    private FuzzySeekingHelper<CommodityItemPO, CommodityItemVO> fuzzySeekingHelper;

    CommodityManager() {
        try {
            dataService = DataFactory.getDataBase(CommodityDataService.class);
            logger = new UserLogger();
            buildCommodityTree();

            // Initialize fuzzy seeking helper
            fuzzySeekingHelper = new FuzzySeekingHelper<>(po -> new CommodityItem(po).toVO());
            fuzzySeekingHelper.registerFunctionForString(dataService::fuzzySearchByName, dataService::fuzzySearchByModel,
                    dataService::fuzzySearchById);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    CommodityCategoriesTreeVO getCommodityCategoriesTreeVO() {
        updateFromDatabase();
        return commodityTree.toVO();
    }


    Result<CommodityItemVO> addCommodity(CommodityBuildInfo.CommodityBuilder builder) {
        updateFromDatabase();

        CommodityBuildInfo buildInfo = builder.build();
        String categoryPath = buildInfo.getCategoryPath();

        if (!commodityTree.isLeaf(categoryPath))
            return new Result<>(ResultMessage.FAILURE, null);

        try {
            String commodityID = getCommodityId(categoryPath);

            // Create po and add it to the database
            ResultMessage addResult = dataService.add(buildInfo.toPO(commodityID));
            if (addResult.success()) {
                logger.add(OPType.ADD, "添加商品" + commodityID);
                return new Result<>(addResult, buildInfo.toVO(commodityID));
            }

            return new Result<>(ResultMessage.FAILURE, null);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new Result<>(ResultMessage.NETWORK_FAIL, null);
        }
    }

    Image getTrend(String commodityId) {
        try {
            return dataService.getTrend(commodityId);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }


    private String getCommodityId(String categoryPath) throws RemoteException {

        int parentCategoryID = CommodityPathParser.getLastNumOfPath(categoryPath);
        List<CommodityItemPO> poList = dataService.findByCategory(parentCategoryID);
        // Get max number and plus one, then we get the id of the commodity
        int sequenceNum = poList.stream()
                .max(Comparator.comparing(CommodityItemPO::getSequenceNumber))
                .map(CommodityItemPO::getSequenceNumber)
                .orElse(0) + 1;
        return categoryPath + CommodityBLService.SEPARATOR + sequenceNum;
    }

    List<CommodityItemVO> findCommodityByCategory(int categoryID) {
        return findByToList(categoryID, dataService::findByCategory);
    }

    List<BasicCommodityItemVO> findBasicCommodityByCategory(int categoryID) {
        return DataServiceFunction.findByToList(categoryID, dataService::findByCategory,
                po -> new CommodityItem(po).toBasicCommodityItem().toVo());
    }

    List<CommodityItemVO> findCommodityVOByName(String commodityName) {
        return findByToList(commodityName, dataService::findByName);
    }

    CommodityItemVO findCommodityVOById(String id) {
        return findByToEntity(id, dataService::findById);
    }

    ResultMessage deleteCommodity(String id) {
        try {
            ResultMessage res = dataService.deleteCommodity(id);
            if (res == ResultMessage.SUCCESS) {
                logger.add(OPType.DELETE, "删除商品 " + id);
            }
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    ResultMessage modifyCommodity(CommodityItemVO commodity) {
        try {
            CommodityItemPO po = dataService.findById(commodity.getId());
            if (po == null)
                return ResultMessage.INVALID_ID;

            CommodityItem item = new CommodityItem(commodity, po.getCategoryId());
            ResultMessage res = dataService.update(item.toPO());
            if (res.success()) {
                logger.add(OPType.MODIFY, "修改商品 " + commodity.getId());

                // Trigger alert document
                DocInfo docInfo = new DocInfoImpl();
                docInfo.triggerAlertDoc(commodity.getId(), commodity.getRepCount());
            }
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * Add a new category. The parent category's vo should be obtained in the vo tree.
     * @param newCategory category you want to add
     * @return <tt>SUCCESS</tt> if add successfully,
     * <tt>FAILURE</tt> if parent category don't exists any more or it contains commodity items.
     */
    Result<CommodityCategoryVO> addCategory(CommodityCategoryVO newCategory) {
        // Update and check
        updateFromDatabase();
        if (!commodityTree.contains(newCategory.getParentPath())
                || containsItem(newCategory.getUpperCategory().getId())) {
            return new Result<>(ResultMessage.FAILURE, null);
        }

        // Add to the database
        CommodityCategoryPO categoryPO = new CommodityCategoryPO(newCategory.getName(),
                newCategory.getUpperCategory().getId());
        Result<Integer> addResult = DataServiceFunction.addToDataBase(categoryPO, dataService::add);

        if (addResult.hasValue()) {
            logger.add(OPType.ADD, "添加商品目录 " + newCategory.getName());
            newCategory.setId(addResult.getValue());
            return new Result<>(addResult.getResultMessage(), newCategory);
        }

        return new Result<>(addResult.getResultMessage(), null);
    }

    private boolean containsItem(int category) {
        return !findCommodityByCategory(category).isEmpty();
    }

    ResultMessage deleteCategory(int id) {
        try {
            ResultMessage res = dataService.deleteCategory(id);
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.DELETE, "删除目录" + id);
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    ResultMessage changeCategoryName(CommodityCategoryVO vo) {
        updateFromDatabase();
        if (vo.getId() == -1 || !commodityTree.contains(vo.getParentPath()))
            return ResultMessage.FAILURE;

        // Execute the change
        CommodityCategoriesTree.CommodityCategory category = commodityTree.get(vo.getPath());
        category.setCategoryName(vo.getName());
        try {
            ResultMessage res = dataService.update(category.toPO());
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.MODIFY, "修改商品目录 " + vo.getId() + " 名称为 " + vo.getName());
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    private <C> List<CommodityItemVO> findByToList(C condition, DataServiceFunction<C, List<CommodityItemPO>> function) {
        return DataServiceFunction.findByToList(condition, function, itemPO -> new CommodityItem(itemPO).toVO());
    }

    private <C> CommodityItemVO findByToEntity(C condition, DataServiceFunction<C, CommodityItemPO> function) {
        return DataServiceFunction.findToEntity(condition, function, po -> new CommodityItem(po).toVO());
    }

    /**
     * Build the tree of commodity categories
     */
    private void buildCommodityTree() {
        try {
            List<CommodityCategoryPO> categoryPOList = dataService.getAllCommodityCategory();
            CommodityCategoriesTree.TreeBuilder treeBuilder = new CommodityCategoriesTree.TreeBuilder(categoryPOList);
            commodityTree = treeBuilder.build();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Just rebuild the tree orz
     */
    private void updateFromDatabase() {
        buildCommodityTree();
    }

    List<CommodityItemVO> searchCommodity(String keyword) {
        return fuzzySeekingHelper.executeSeeking(keyword);
    }
}
