package nju.lighting.bl.commoditybl;

import nju.lighting.bl.utils.CommodityPathParser;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.bl.utils.DataServiceSupplier;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.repository.RepositoryTableItemVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created on 2017/12/9.
 * Description:
 * @author Liao
 */
public class CommodityInfoImpl implements CommodityInfo {

    private CommodityDataService dataService;

    public CommodityInfoImpl() {
        try {
            dataService = DataFactory.getDataBase(CommodityDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BasicCommodityItemVO getBasicCommodityItemVO(String id) {
        return DataServiceFunction.findByToEntity(id, dataService::findById,
                itemPO -> new BasicCommodityItemVO(id, itemPO.getName(), itemPO.getRepCount(),
                        itemPO.getRecentInPrice(), itemPO.getRecentSellPrice()));
    }

    @Override
    public double getCommodityRecentSellPrice(String id) {
        try {
            CommodityItemPO po = dataService.findById(id);
            if (po == null)
                throw new NoSuchElementException("Invalid Id");
            return po.getRecentSellPrice();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getCommodityNameByID(String commodityID) {
        try {
            CommodityItemPO po = dataService.findById(commodityID);
            if (po == null)
                throw new NoSuchElementException("Invalid id of commodity");
            return po.getName();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get name of the commodity's category. If the commodity is the
     * child of the root category(id is -1), it won't query the database.
     * @param commodityID id of the commodity
     * @return name of the category
     */
    @Override
    public String getCommodityCategory(String commodityID) {
        int categoryId = CommodityPathParser.getCommodityCategory(commodityID);

        // Root is a dummy node, it can't be found in the database
        if (categoryId == -1)
            return CommodityCategoriesTree.ROOT_NAME;

        return Optional.ofNullable(DataServiceFunction.findByToPO(categoryId, dataService::findCategoryById))
                .map(CommodityCategoryPO::getName)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public double getCommodityInPrice(String id) {
        return Optional.ofNullable(DataServiceFunction.findByToPO(id, dataService::findById))
                .map(CommodityItemPO::getInPrice)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public double getCostAdjustRevenue() {
        try {
            return dataService.getAllCommodity().stream()
                    .mapToDouble(item -> (item.getRecentInPrice() - item.getInPrice()) * item.getRepCount())
                    .sum();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<RepositoryTableItemVO> getRepositoryTableItemList() {
        Function<CommodityItemPO, RepositoryTableItemVO> poTransformer = itemPO -> new RepositoryTableItemVO(itemPO.getId(), itemPO.getModelNumber(),
                itemPO.getName(), itemPO.getRepCount(), itemPO.getRecentInPrice(),
                itemPO.getBatch(), itemPO.getBatchNumber(), itemPO.getDateOfProduction());
        // Get commodity whose count is nonzero
        return DataServiceSupplier.getAll(dataService::getAllCommodity, poTransformer);
    }

    @Override
    public ResultMessage changeCommodityNumber(String id, int count) {
        try {
            CommodityItemPO item = dataService.findById(id);
            item.addRepositoryCount(count);

            return dataService.update(item);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public boolean reduceCommodityItem(String id, int count) {
        return false;
    }

    @Override
    public boolean achieveAlertLimit(String commodityId, int count) {
        try {
            CommodityItemPO item = dataService.findById(commodityId);
            return item.getRepCount() >= count;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
