package nju.lighting.bl.commoditybl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.utils.CommodityPathParser;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.OPType;
import shared.ResultMessage;
import shared.TwoTuple;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.List;

enum CommodityManager {
    INSTANCE;

    private CommodityCategoriesTree commodityTree;
    private CommodityDataService dataService;
    private Logger logger;

    CommodityManager() {
        try {
            dataService = DataFactory.getDataBase(CommodityDataService.class);
            logger = new UserLogger();
            buildCommodityTree();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    CommodityCategoriesTreeVO getCommodityCategoriesTreeVO() {
        updateFromDatabase();
        return commodityTree.toVO();
    }


    TwoTuple<ResultMessage, String> addCommodity(CommodityItemVO newCommodity, String categoryPath) {
        // Check category id
        updateFromDatabase();
        TwoTuple<ResultMessage, String> res = new TwoTuple<>();
        if (!commodityTree.isLeaf(categoryPath)) {
             res.t = ResultMessage.FAILURE;
             return res;
        }

        try {
            // Generate commodity id
            int parentCategoryID = CommodityPathParser.getLastNumOfPath(categoryPath);
            List<CommodityItemPO> poList = dataService.findByCategory(parentCategoryID);
            int sequenceNum = poList.stream()
                    .max(Comparator.comparing(CommodityItemPO::getSequenceNumber))
                    .map(CommodityItemPO::getSequenceNumber)
                    .orElse(0) + 1;
            String commodityID = categoryPath + CommodityBLService.SEPARATOR + sequenceNum;

            // Create po and add it to the database
            newCommodity.setId(commodityID);
            CommodityItem commodityItem = new CommodityItem(newCommodity, parentCategoryID);
            ResultMessage addResult = dataService.add(commodityItem.toPO());
            res.t = addResult;
            if (addResult == ResultMessage.SUCCESS) {
                logger.add(OPType.ADD, "添加商品" + commodityID);
                res.r = commodityID;
            }
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            res.t =  ResultMessage.NETWORK_FAIL;
            return res;
        }

    }


    List<CommodityItemVO> findCommodityByCategory(int categoryID) {
        return findByToList(categoryID, dataService::findByCategory);
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
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.MODIFY, "修改商品 " + commodity.getId());
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
    TwoTuple<ResultMessage, Integer> addCategory(CommodityCategoryVO newCategory) {
        // Update and check
        updateFromDatabase();
        TwoTuple<ResultMessage, Integer> res = new TwoTuple<>();
        if (!commodityTree.contains(newCategory.getParentPath())
                || containsItem(newCategory.getUpperCategory().getId())) {
            res.t = ResultMessage.FAILURE;
            return res;
        }

        // Add to the database
        try {
            TwoTuple<ResultMessage, Integer> insertResult =
                    dataService.add(new CommodityCategoryPO(newCategory.getName(),
                            newCategory.getUpperCategory().getId()));
            ResultMessage addResult = insertResult.t;
            if (addResult == ResultMessage.SUCCESS) {
                logger.add(OPType.ADD, "添加商品目录 " + newCategory.getName());
                res.r = insertResult.r;
            }
            res.t = addResult;
        } catch (RemoteException e) {
            e.printStackTrace();
            res.t = ResultMessage.NETWORK_FAIL;
        }

        return res;
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
        return DataServiceFunction.findByToEntity(condition, function, po -> new CommodityItem(po).toVO());
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

}
