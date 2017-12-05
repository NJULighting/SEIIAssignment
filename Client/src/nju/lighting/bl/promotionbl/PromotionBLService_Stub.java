package nju.lighting.bl.promotionbl;

import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.BasicCommodityTreeVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.ComboPromotionVO;
import nju.lighting.vo.promotion.CustomerOrientedPromotionVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerGrade;
import shared.PromotionType;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionBLService_Stub implements PromotionBLService {

    @Override
    public ArrayList<PromotionVO> getBenefitsPlan(int customerLevel, ArrayList<String> CommodityList, double total) {
        BasicCommodityItemVO commodityVO1 = new BasicCommodityItemVO("xx0002222", "日本LED无障碍灯泡", null,
                100, 100, 100);
        BasicCommodityItemVO commodityVO2 = new BasicCommodityItemVO("xx0002223", "日本LED无障碍灯泡" + "-b", null,
                100, 100, 100);

        ArrayList<GiftItemVO> gifts1 = new ArrayList<>();

        GiftItemVO giftItemVO1 = new GiftItemVO(commodityVO1, 10);
        GiftItemVO giftItemVO2 = new GiftItemVO(commodityVO2, 1);

        gifts1.add(giftItemVO1);
        gifts1.add(giftItemVO2);


        PromotionVO promotionVo1 = new CustomerOrientedPromotionVO("店庆酬宾",PromotionType.CustomerOriented,
                null,null,CustomerGrade.FIVE,gifts1,0,0,null);
        PromotionVO promotionVo2 = new ComboPromotionVO("店庆酬宾",PromotionType.Combo,null,null,
                gifts1,300,50);

        ArrayList<PromotionVO> promotionVOs = new ArrayList<>();
        promotionVOs.add(promotionVo1);
        promotionVOs.add(promotionVo2);

        return promotionVOs;
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList() {

        Calendar c = Calendar.getInstance();

        PromotionVO vo1 =new CustomerOrientedPromotionVO("店庆酬宾",PromotionType.CustomerOriented,
                new Date(),new Date(),CustomerGrade.FIVE,null,30,0,null);
        PromotionVO vo2 =  new ComboPromotionVO("店庆酬宾",PromotionType.PriceOriented,new Date(),new Date(),
                null,300,100);

        ArrayList<PromotionVO> promotionVOs = new ArrayList<>();
        promotionVOs.add(vo1);
        promotionVOs.add(vo2);

        return promotionVOs;
    }

    @Override
    public BasicCommodityTreeVO create() {
        return null;
    }

    @Override
    public ResultMessage commit(PromotionVO vo) {
        return null;
    }

    @Override
    public ResultMessage modify(PromotionVO vo) {
        return null;
    }

    @Override
    public ResultMessage delete(PromotionVO vo) {
        return ResultMessage.FAILURE;
    }
}
