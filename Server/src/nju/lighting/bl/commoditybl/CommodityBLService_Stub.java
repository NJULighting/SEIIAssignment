package nju.lighting.bl.commoditybl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import shared.ResultMessage;
import nju.lighting.vo.commodity.CommodityCategoryItemVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CommodityBLService_Stub implements CommodityBLService {

    @Override
    public CommodityTreeVO getCommodityTree() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage addCommodity(CommodityItemVO newCommodity) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CommodityItemVO> findCommodityByName(String commodityName) throws RemoteException {
        return null;
    }

    @Override
    public CommodityItemVO findCommodityById(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage deleteCommodity(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityItemVO commodity) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage addCategory(CommodityCategoryItemVO newCategory) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage deleteCategory(int id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyCategory(CommodityCategoryItemVO categoryItemVO) throws RemoteException {
        return null;
    }
}
