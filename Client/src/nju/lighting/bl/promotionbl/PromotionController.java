package nju.lighting.bl.promotionbl;

import nju.lighting.blservice.promotionblservice.PromotionBLService;
import shared.ResultMessage;
import nju.lighting.vo.promotion.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/11/7.
 * Description
 * @author 陈俊宇
 */
public class PromotionController implements PromotionBLService {
    @Override
    public ArrayList<PromotionVO> getBenefitsPlan(int customerLevel, ArrayList<String> CommodityList, double total)  {
        return null;
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList()  {
        return null;
    }

    @Override
    public ResultMessage newAndSave(PromotionVO vo)  {
        return null;
    }

    @Override
    public ResultMessage modifyAndSave(PromotionVO vo)  {
        return null;
    }

    @Override
    public ResultMessage delete(PromotionVO vo)  {
        return null;
    }
}
