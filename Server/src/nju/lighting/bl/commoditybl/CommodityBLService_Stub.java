package nju.lighting.bl.commoditybl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.CommodityVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CommodityBLService_Stub implements CommodityBLService {

    @Override
    public ArrayList<CommodityVO> getCommodityList() throws RemoteException {
        ArrayList<CommodityVO> arrayList = new ArrayList<>();
        CommodityVO commodityVO1 = new CommodityVO("日本LED无障碍灯泡", "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityVO commodityVO2 = new CommodityVO("日本LED无障碍灯泡-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        arrayList.add(commodityVO1);
        arrayList.add(commodityVO2);
        return arrayList;
    }

    @Override
    public ResultMessage addCommodity(CommodityVO newCommodity) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<CommodityVO> findCommodityByName(String commodityName) throws RemoteException {
        ArrayList<CommodityVO> arrayList = new ArrayList<>();
        CommodityVO commodityVO1 = new CommodityVO(commodityName, "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityVO commodityVO2 = new CommodityVO(commodityName + "-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        arrayList.add(commodityVO1);
        arrayList.add(commodityVO2);
        return arrayList;
    }

    @Override
    public CommodityVO findCommodityById(String id) throws RemoteException {
        return new CommodityVO("美国金坷垃电灯泡", "金坷垃", id,
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
    }

    @Override
    public ResultMessage deleteCommodity(String id) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityVO commodity) throws RemoteException {
        return ResultMessage.SUCCESS;
    }
}
