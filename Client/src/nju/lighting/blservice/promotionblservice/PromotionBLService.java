package nju.lighting.blservice.promotionblservice;

import nju.lighting.vo.promotion.PromotionVO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public interface PromotionBLService {

    ArrayList<PromotionVO> getBenefitsPlan(int customerLevel, ArrayList<String> CommodityList, double total) ; //得到满足条件的销售策略

    ArrayList<PromotionVO> getPromotionList() ;

    ResultMessage newAndSave(PromotionVO vo) ;

    ResultMessage modifyAndSave(PromotionVO vo) ;

    ResultMessage delete(PromotionVO vo) ;
}
