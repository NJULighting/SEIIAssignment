package nju.lighting.bl.commoditybl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.CommodityVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Commodity implements CommodityBLService {

    @Override
    public ArrayList<CommodityVO> getCommodityList() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage addCommodity(CommodityVO newCommodity) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CommodityVO> findCommodityByName(String commodityName) throws RemoteException {
        return null;
    }

    @Override
    public CommodityVO findCommodityById(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage deleteCommodity(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityVO commodity) throws RemoteException {
        return null;
    }
}
