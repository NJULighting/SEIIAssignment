package nju.lighting.data.commoditydata;

import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.po.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CommodityDataService_Stub implements CommodityDataService {

    @Override
    public ArrayList<CommodityItemPO> getAll() throws RemoteException {
        ArrayList<CommodityItemPO> arrayList = new ArrayList<>();
        CommodityItemPO commodityVO1 = new CommodityItemPO("日本LED无障碍灯泡", "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityItemPO commodityVO2 = new CommodityItemPO("日本LED无障碍灯泡-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        arrayList.add(commodityVO1);
        arrayList.add(commodityVO2);
        return arrayList;
    }

    @Override
    public ResultMessage insert(CommodityItemPO pp) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<CommodityItemPO> findByName(String commodityName) throws RemoteException {
        ArrayList<CommodityItemPO> arrayList = new ArrayList<>();
        CommodityItemPO commodityVO1 = new CommodityItemPO(commodityName, "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityItemPO commodityVO2 = new CommodityItemPO(commodityName + "-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        arrayList.add(commodityVO1);
        arrayList.add(commodityVO2);
        return arrayList;
    }

    @Override
    public CommodityItemPO find(String id) throws RemoteException {
        CommodityItemPO commodityVO1 = new CommodityItemPO("日本LED无障碍灯泡", "LED", id,
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        return commodityVO1;
    }

    @Override
    public ResultMessage delete(String id) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(CommodityItemPO commodity) {
        return ResultMessage.SUCCESS;
    }
}
