package nju.lighting.bl.promotionbl;

import nju.lighting.bl.approvalbl.ApprovalBLService_Stub;
import nju.lighting.bl.userbl.UserBLServie_Stub;
import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class PromotionBLService_Stub implements PromotionBLService {

    @Override
    public List<PromotionVO> getBenefitsPlan(CustomerGrade customerLevel, List<String> CommodityList, double total) {
        BasicCommodityItemVO commodityVO1 = new BasicCommodityItemVO("xx0002222", "日本LED无障碍灯泡",
                100, 100, 100);
        BasicCommodityItemVO commodityVO2 = new BasicCommodityItemVO("xx0002223", "日本LED无障碍灯泡" + "-b",
                100, 100, 100);

        ArrayList<GiftItemVO> gifts1 = new ArrayList<>();

        GiftItemVO giftItemVO1 = new GiftItemVO(commodityVO1, 10);
        GiftItemVO giftItemVO2 = new GiftItemVO(commodityVO2, 1);

        gifts1.add(giftItemVO1);
        gifts1.add(giftItemVO2);

        UserVO creator = new UserBLServie_Stub().getUser("0");

        PromotionVO promotionVo1 = new PromotionVO(0, "店庆酬宾", creator, PromotionType.CustomerOriented,
                null, null, CustomerGrade.FIVE, 0, gifts1, 0, 0, null);
        PromotionVO promotionVo2 = new PromotionVO(1, "店庆酬宾", creator, PromotionType.Combo, null, null
                , null, 300, gifts1, 50, 0, null);

        ArrayList<PromotionVO> promotionVOs = new ArrayList<>();
        promotionVOs.add(promotionVo1);
        promotionVOs.add(promotionVo2);

        return promotionVOs;
    }

    @Override
    public List<PromotionVO> getPromotionList() {
        UserVO creator = new UserBLServie_Stub().getUser("0");
        List<DocVO> gifts = ((new ApprovalBLService_Stub().getDocumentList()));

        Date date = new Date();
        date.setYear(300);
        PromotionVO vo1 = new PromotionVO(1, "店庆酬宾", creator, PromotionType.CustomerOriented,
                new Date(), date, CustomerGrade.FIVE, 0, null, 30, 0, new Date());
        PromotionVO vo2 = new PromotionVO(2, "店庆酬宾", creator, PromotionType.PriceOriented,
                new Date(), date, null, 150, ((GiftDocVO) gifts.get(0)).getGifts(),
                0, 300, new Date());
        PromotionVO vo3 = new PromotionVO(3, "店庆酬宾", creator, PromotionType.PriceOriented, new Date(), new Date(),
                null, 150, null, 0, 300, new Date());
        PromotionVO vo4 = new PromotionVO(2, "店庆酬宾", creator, PromotionType.Combo, new Date(), new Date(),
                null, 150,
                ((GiftDocVO) gifts.get(1)).getGifts(),
                0, 0, new Date());

        ArrayList<PromotionVO> promotionVOs = new ArrayList<>();
        promotionVOs.add(vo1);
        promotionVOs.add(vo2);
        promotionVOs.add(vo3);
        promotionVOs.add(vo4);

        return promotionVOs;
    }

    @Override
    public Result<PromotionVO> commit(PromotionBuildInfo.Builder builder) {
        if (builder.build().getType().equals(PromotionType.Combo))
            return new Result<>(ResultMessage.SUCCESS, getPromotionList().get(0));
        else
            return new Result<>(ResultMessage.FAILURE, getPromotionList().get(0));
    }

    @Override
    public ResultMessage modify(PromotionVO vo) {
        return null;
    }

}
