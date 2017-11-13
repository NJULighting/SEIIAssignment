package nju.lighting.bl.promotionbl;

import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerGrade;
import shared.PromotionType;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class PromotionBLService_Stub implements PromotionBLService {

    @Override
    public ArrayList<PromotionVO> getBenefitsPlan(int customerLevel, ArrayList<String> CommodityList, double total)  {
        BasicCommodityItemVO commodityVO1 = new BasicCommodityItemVO("xx0002222","日本LED无障碍灯泡", null,
                100, 100,100);
        BasicCommodityItemVO commodityVO2 = new BasicCommodityItemVO("xx0002223","日本LED无障碍灯泡" + "-b", null,
                100, 100,100);

        ArrayList<GiftItemVO> gifts1 = new ArrayList<>();

        GiftItemVO giftItemVO1 = new GiftItemVO(commodityVO1, 10);
        GiftItemVO giftItemVO2 = new GiftItemVO(commodityVO2, 1);

        gifts1.add(giftItemVO1);
        gifts1.add(giftItemVO2);

        Calendar c = Calendar.getInstance();
        PromotionVO promotionVo1 = new PromotionVO(PromotionType.CustomerOriented, c.getTime(), c.getTime(), c.getTime(), CustomerGrade.THREE,
                0, gifts1, 0, 0, c.getTime());
        PromotionVO promotionVo2 = new PromotionVO(PromotionType.TotalOriented, c.getTime(), c.getTime(), c.getTime(), null, 399,
                null, 30, 0, c.getTime());

        ArrayList<PromotionVO> promotionVOs = new ArrayList<>();
        promotionVOs.add(promotionVo1);
        promotionVOs.add(promotionVo2);

        return promotionVOs;
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList() {

        Calendar c = Calendar.getInstance();

        PromotionVO vo1 = new PromotionVO(PromotionType.CustomerOriented, c.getTime(), c.getTime(), c.getTime(), CustomerGrade.THREE,
                0, null, 0, 0, c.getTime());
        PromotionVO vo2 = new PromotionVO(PromotionType.TotalOriented, c.getTime(), c.getTime(), c.getTime(), null, 399,
                null, 30, 0, c.getTime());

        ArrayList<PromotionVO> promotionVOs = new ArrayList<>();
        promotionVOs.add(vo1);
        promotionVOs.add(vo2);

        return promotionVOs;
    }

    @Override
    public ResultMessage newAndSave(PromotionVO vo) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyAndSave(PromotionVO vo) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(PromotionVO vo) {
        return ResultMessage.FAILURE;
    }
}
