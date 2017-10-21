package nju.lighting.data.commoditydata;

import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.CommodityPO;
import nju.lighting.po.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CommodityDataService_Stub implements CommodityDataService {

    @Override
    public ArrayList<CommodityPO> getAll() throws RemoteException {
        ArrayList<CommodityPO> arrayList = new ArrayList<>();
        CommodityPO commodityVO1 = new CommodityPO("日本LED无障碍灯泡", "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityPO commodityVO2 = new CommodityPO("日本LED无障碍灯泡-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        arrayList.add(commodityVO1);
        arrayList.add(commodityVO2);
        return arrayList;
    }

    @Override
    public ResultMessage insert(CommodityPO pp) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<CommodityPO> findByName(String commodityName) throws RemoteException {
        ArrayList<CommodityPO> arrayList = new ArrayList<>();
        CommodityPO commodityVO1 = new CommodityPO(commodityName, "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityPO commodityVO2 = new CommodityPO(commodityName + "-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        arrayList.add(commodityVO1);
        arrayList.add(commodityVO2);
        return arrayList;
    }

    @Override
    public CommodityPO find(String id) throws RemoteException {
        CommodityPO commodityVO1 = new CommodityPO("日本LED无障碍灯泡", "LED", id,
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        return commodityVO1;
    }

    @Override
    public ResultMessage delete(String id) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(CommodityPO commodity) {
        return ResultMessage.SUCCESS;
    }
}
