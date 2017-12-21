package nju.lighting.bl.commoditybl;

import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.bl.utils.DataServiceSupplier;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.repository.RepositoryTableItemVO;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;
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
    public CommodityCategoriesTree getCommodityCategoryTree() {
        return null;
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
    public List<CommodityItem> getCommodityItems(List<String> ids) {
        return null;
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

    @Override
    public List<RepositoryTableItemVO> getRepositoryTableItemList() {
        Function<CommodityItemPO, RepositoryTableItemVO> poTransformer = itemPO -> new RepositoryTableItemVO(itemPO.getId(), itemPO.getModelNumber(),
                itemPO.getName(), itemPO.getRepCount(), itemPO.getRecentInPrice(),
                itemPO.getBatch(), itemPO.getBatchNumber(), itemPO.getDateOfProduction());
        // Get commodity whose count is nonzero
        return DataServiceSupplier.getAll(dataService::getAllCommodity, poTransformer);
    }

    @Override
    public boolean addCommodityItem(String id, int count) {
        return false;
    }

    @Override
    public boolean reduceCommodityItem(String id, int count) {
        return false;
    }
}
