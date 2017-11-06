package nju.lighting.blservice.commodityblservice;

import nju.lighting.po.ResultMessage;
import nju.lighting.vo.commodity.CommodityCategoryItemVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CommodityController implements CommodityBLService {

    @Override
    public CommodityTreeVO getCommodityTree() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage addCommodity(CommodityItemVO newCommodity) {
        return null;
    }

    @Override
    public ArrayList<CommodityItemVO> findCommodityByName(String commodityName) {
        return null;
    }

    @Override
    public CommodityItemVO findCommodityById(String id) {
        return null;
    }

    @Override
    public ResultMessage deleteCommodity(String id) {
        return null;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityItemVO commodity) {
        return null;
    }

    @Override
    public ResultMessage addCategory(CommodityCategoryItemVO newCategory) {
        return null;
    }

    @Override
    public ResultMessage deleteCategory(int id) {
        return null;
    }

    @Override
    public ResultMessage modifyCategory(CommodityCategoryItemVO categoryItemVO) {
        return null;
    }
}
