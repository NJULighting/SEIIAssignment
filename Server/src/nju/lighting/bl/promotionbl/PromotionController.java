package nju.lighting.bl.promotionbl;

import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.vo.ResultMessage;
import nju.lighting.vo.promotion.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/11/7.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionController implements PromotionBLService {
    @Override
    public ArrayList<PromotionVO> getBenefitsPlan(int customerLevel, ArrayList<String> CommodityList, double total) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage newAndSave(PromotionVO vo) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyAndSave(PromotionVO vo) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage delete(PromotionVO vo) throws RemoteException {
        return null;
    }
}
