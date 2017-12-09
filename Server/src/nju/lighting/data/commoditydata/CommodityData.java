package nju.lighting.data.commoditydata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 商品模块数据层实现
 * @author iznauy
 */
public class CommodityData extends UnicastRemoteObject implements CommodityDataService, CommodityService {

    private CommonOperation<CommodityItemPO> commodityItemPOCommonOperation;

    private CommonOperation<CommodityCategoryPO> categoryPOCommonOperation;

    public CommodityData() throws RemoteException {
        this.commodityItemPOCommonOperation = new CommonOperation<>(CommodityItemPO.class.getName());
        this.categoryPOCommonOperation = new CommonOperation<>(CommodityCategoryPO.class.getName());
    }

    @Override
    public List<CommodityItemPO> findByCategory(String categoryID) throws RemoteException {
        return null;
    }

    @Override
    public Date getRecentChangeTime() throws RemoteException {
        return null;
    }

    @Override
    public List<CommodityItemPO> getAllCommodity() throws RemoteException {
        return commodityItemPOCommonOperation.getAll();
    }

    @Override
    public List<CommodityCategoryPO> getAllCommodityCategory() throws RemoteException {
        return categoryPOCommonOperation.getAll();
    }

    @Override
    public CommodityItemPO findById(String id) throws RemoteException {
        return commodityItemPOCommonOperation.getBySingleField("id", id);
    }

    @Override
    public List<CommodityItemPO> findByName(String name) throws RemoteException {
        return commodityItemPOCommonOperation.getListBySingleField("name", name);
    }


    @Override
    public ResultMessage add(CommodityItemPO commodityItemPO) throws RemoteException {
        return commodityItemPOCommonOperation.add(commodityItemPO);
    }

    @Override
    public ResultMessage update(CommodityItemPO commodityItemPO) throws RemoteException {
        return commodityItemPOCommonOperation.update(commodityItemPO);
    }

    @Override
    public ResultMessage deleteCommodity(String id) throws RemoteException {
        return commodityItemPOCommonOperation.deleteBySingleField("id", id);
    }

    @Override
    public ResultMessage add(CommodityCategoryPO commodityCategoryPO) throws RemoteException {
        return categoryPOCommonOperation.add(commodityCategoryPO);
    }

    @Override
    public ResultMessage deleteCategory(int id) throws RemoteException {
        return categoryPOCommonOperation.deleteBySingleField("id", id);
    }
}
