package nju.lighting.blservice.promotionblservice;


import nju.lighting.vo.commodity.BasicCommodityTreeVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerGrade;
import shared.PromotionBuildInfo;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.List;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface PromotionBLService {

    List<PromotionVO> getBenefitsPlan(CustomerGrade customerLevel, List<String> CommodityList, double total); //得到满足条件的销售策略

    List<PromotionVO> getPromotionList();

    BasicCommodityTreeVO create();

    TwoTuple<ResultMessage, PromotionVO> commit(PromotionBuildInfo info);

    ResultMessage modify(PromotionVO vo);

    ResultMessage delete(int promotionID);
}
