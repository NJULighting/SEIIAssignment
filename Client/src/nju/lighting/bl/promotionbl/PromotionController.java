package nju.lighting.bl.promotionbl;

import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.vo.commodity.BasicCommodityTreeVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerGrade;
import shared.PromotionBuildInfo;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.List;

/**
 * Created on 2017/11/7.
 * Description
 * @author 陈俊宇
 */
public class PromotionController implements PromotionBLService {
    private PromotionManager manager = PromotionManager.INSTANCE;

    @Override
    public List<PromotionVO> getBenefitsPlan(CustomerGrade customerLevel, List<String> commodityList, double total) {
        return manager.getBenefitsPlan(customerLevel, commodityList, total);
    }

    @Override
    public List<PromotionVO> getPromotionList() {
        return manager.getPromotionList();
    }

    @Override
    public BasicCommodityTreeVO create() {
        return null;
    }

    @Override
    public TwoTuple<ResultMessage, PromotionVO> commit(PromotionBuildInfo info) {
        return manager.commit(info);
    }

    @Override
    public ResultMessage modify(PromotionVO vo) {
        return manager.modify(vo);
    }


    @Override
    public ResultMessage delete(int promotionID) {
        return null; // TODO: 2017/12/7 Need this?
    }
}
