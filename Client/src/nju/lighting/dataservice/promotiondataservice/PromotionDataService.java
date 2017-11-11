package nju.lighting.dataservice.promotiondataservice;

import nju.lighting.po.promotion.PromotionPO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface PromotionDataService {

    ArrayList<PromotionPO> getBenefitsPlan(int customerLevel, ArrayList<String> commodityList, double total);

    ArrayList<PromotionPO> getPromotionList();

    ResultMessage insert(PromotionPO po);

    ResultMessage update(PromotionPO po);

    ResultMessage delete(PromotionPO po);

}
