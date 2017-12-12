package nju.lighting.data.commoditydata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import shared.ResultMessage;

import java.io.*;
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

    private static final String recentChangeFilePath = "Date.out";

    private void updateRecentChangeTime() {
        Date date = new Date();
        File file = new File(recentChangeFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        try (ObjectOutputStream oj = new ObjectOutputStream(new FileOutputStream(file))) {
            oj.writeObject(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommodityData() throws RemoteException {
        this.commodityItemPOCommonOperation = new CommonOperation<>(CommodityItemPO.class.getName());
        this.categoryPOCommonOperation = new CommonOperation<>(CommodityCategoryPO.class.getName());
    }

    @Override
    public List<CommodityItemPO> findByCategory(int categoryID) throws RemoteException {
        return commodityItemPOCommonOperation.getListBySingleField("categoryId", categoryID);
    }

    @Override
    public Date getRecentChangeTime() throws RemoteException {
        File file = new File(recentChangeFilePath);
        if (!file.exists())
            return null;
        Date date = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            date = (Date) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
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
        ResultMessage resultMessage =  categoryPOCommonOperation.add(commodityCategoryPO);
        if (resultMessage == ResultMessage.SUCCESS)
            updateRecentChangeTime();
        return resultMessage;
    }

    @Override
    public ResultMessage deleteCategory(int id) throws RemoteException {
        ResultMessage resultMessage =  categoryPOCommonOperation.deleteBySingleField("id", id);
        if (resultMessage == ResultMessage.SUCCESS)
            updateRecentChangeTime();
        return resultMessage;
    }
}
