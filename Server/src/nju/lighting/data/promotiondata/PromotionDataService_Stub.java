package nju.lighting.data.promotiondata;

import nju.lighting.dataservice.promotiondataservice.PromotionDataService;

import nju.lighting.po.promotion.PromotionPO;
import shared.ResultMessage;

import java.util.ArrayList;
import shared.CustomerGrade;

/**
 * Created on 2017/10/22.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionDataService_Stub implements PromotionDataService {
    @Override
    public ArrayList<PromotionPO> getBenefitsPlan(int customerLevel, ArrayList<String> commodityList, double total) {
        return null;
    }

    @Override
    public ArrayList<PromotionPO> getPromotionList() {
//
//        PromotionPO po1=new PromotionPO(1,10000,20000, CustomerGrade.THREE,0,null,null,
//                10,0,0);
//
//        ArrayList<PromotionPO> promotionPOs=new ArrayList<>();
//        promotionPOs.add(po1);
//        return promotionPOs;
        return null;
    }

    @Override
    public ResultMessage insert(PromotionPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(PromotionPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(PromotionPO po) {
        return ResultMessage.SUCCESS;
    }
}
