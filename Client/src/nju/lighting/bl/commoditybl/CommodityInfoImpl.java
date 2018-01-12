package nju.lighting.bl.commoditybl;

import nju.lighting.bl.documentbl.DocInfo;
import nju.lighting.bl.documentbl.DocInfoImpl;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.utils.CommodityPathParser;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.OPType;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created on 2017/12/9.
 * Description:
 * @author Liao
 */
public class CommodityInfoImpl implements CommodityInfo {

    private CommodityDataService dataService;
    private Logger logger;

    public CommodityInfoImpl() {
        try {
            logger = new UserLogger();
            dataService = DataFactory.getDataBase(CommodityDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BasicCommodityItemVO getBasicCommodityItemVO(String id) {
        return DataServiceFunction.findToEntity(id, dataService::findById,
                itemPO -> new BasicCommodityItemVO(id, itemPO.getName(), itemPO.getRepCount(),
                        itemPO.getRecentInPrice(), itemPO.getRecentSellPrice(), itemPO.getModelNumber()));
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

        return DataServiceFunction.findToEntity(categoryId, dataService::findCategoryById, CommodityCategoryPO::getName);
    }

    @Override
    public double getCommodityInPrice(String id) {
        return DataServiceFunction.findToEntity(id, dataService::findById, CommodityItemPO::getInPrice);
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
    public ResultMessage addCommodityNumber(String id, int count) {
        try {
            CommodityItemPO item = dataService.findById(id);

            // Check whether the number of the commodity is positive
            if (item.getRepCount() + count < 0)
                return ResultMessage.FAILURE;

            // Update number of the commodity
            item.addRepositoryCount(count);
            ResultMessage res = dataService.update(item);

            if (res.success()) {
                // See whether triggered an alert document
                DocInfo docInfo = new DocInfoImpl();
                docInfo.triggerAlertDoc(id, item.getRepCount());
            }

            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

}
