package nju.lighting.bl.promotionbl;

import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerGrade;
import shared.PromotionBuildInfo;
import shared.Result;
import shared.ResultMessage;

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
    public Result<PromotionVO> commit(PromotionBuildInfo.Builder builder) {
        return manager.commit(builder);
    }

    @Override
    public ResultMessage modify(PromotionVO vo) {
        return manager.modify(vo);
    }
}
