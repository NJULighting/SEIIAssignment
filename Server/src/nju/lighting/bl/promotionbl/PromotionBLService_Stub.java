package nju.lighting.bl.promotionbl;

import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.po.CustomerGrade;
import nju.lighting.vo.CommodityVO;
import nju.lighting.vo.GiftItemVO;
import nju.lighting.vo.PromotionVO;
import nju.lighting.vo.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionBLService_Stub implements PromotionBLService {

    @Override
    public ArrayList<PromotionVO> getBenefitsPlan(int customerLevel, ArrayList<String> CommodityList, double total) throws RemoteException {
        CommodityVO commodityVO1 = new CommodityVO("日本LED无障碍灯泡", "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityVO commodityVO2 = new CommodityVO("日本LED无障碍灯泡" + "-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());

        ArrayList<GiftItemVO> gifts1=new ArrayList<>();

        GiftItemVO giftItemVO1=new GiftItemVO(commodityVO1,10);
        GiftItemVO giftItemVO2=new GiftItemVO(commodityVO2,1);

        gifts1.add(giftItemVO1);
        gifts1.add(giftItemVO2);


        PromotionVO promotionVo1=new PromotionVO(1,1000000000,2000000000, CustomerGrade.THREE,0,
                null,gifts1,0,0,0);
        PromotionVO promotionVo2=new PromotionVO(3,1000000000,2000000000,null,399,
                null,null,30,0,0);

        ArrayList<PromotionVO> promotionVOs =new ArrayList<>();
        promotionVOs.add(promotionVo1);
        promotionVOs.add(promotionVo2);

        return promotionVOs;
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList() throws RemoteException {


        PromotionVO vo1=new PromotionVO(1,1000000000,2000000000,CustomerGrade.THREE,0,null,null,0,10,2000000000);
        PromotionVO vo2=new PromotionVO(3,1000000000,2000000000,CustomerGrade.THREE,399,null,null,30,0,0);

        ArrayList<PromotionVO> promotionVOs =new ArrayList<>();
        promotionVOs.add(vo1);
        promotionVOs.add(vo2);

        return promotionVOs;
    }

    @Override
    public ResultMessage newAndSave(PromotionVO vo) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyAndSave(PromotionVO vo) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(PromotionVO vo) throws RemoteException {
        return ResultMessage.FAILURE;
    }
}
