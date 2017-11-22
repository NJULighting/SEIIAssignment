package nju.lighting.data.approvaldata;

import nju.lighting.dataservice.approvaldataservice.ApprovalDataService;
import nju.lighting.po.doc.historydoc.HistoryDocPO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description
 *
 * @author 陈俊宇
 */
public class ApprovalDataService_Stub implements ApprovalDataService{
    @Override
    public ArrayList<HistoryDocPO> getDocumentList() {
//        CommodityPO commodityVO1 = new CommodityPO("日本LED无障碍灯泡", "LED", "xx0002222",
//                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
//        CommodityPO commodityVO2 = new CommodityPO("日本LED无障碍灯泡" + "-b", "LED", "xx0002223",
//                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
//        CommodityPO commodityVO3 = new CommodityPO("日本LED无障碍灯泡" + "-c", "LED", "xx0002224",
//                100, 100, new ArrayList<Double>(), 130.0, new ArrayList<Double>());
//
//        GiftItemPO giftItemVO1=new GiftItemPO(commodityVO1,3);
//        GiftItemPO giftItemVO2=new GiftItemPO(commodityVO2,1);
//        GiftItemPO giftItemVO3=new GiftItemPO(commodityVO3,10);
//
//        ArrayList<GiftItemPO> gifts1=new ArrayList<>();
//        ArrayList<GiftItemPO> gifts2=new ArrayList<>();
//
//        gifts1.add(giftItemVO1);
//        gifts1.add(giftItemVO2);
//        gifts2.add(giftItemVO2);
//        gifts2.add(giftItemVO3);
//
//
//
//        GiftDocPO vo1=new GiftDocPO(gifts1,"ZPD-20171021-0001","甲方","N0.1");
//        GiftDocPO vo2=new GiftDocPO(gifts2,"ZPD-20171021-0010","甲方","N0.1");
//
//        ArrayList<HistoryDocPO> historyDocVOs=new ArrayList<>();
//        historyDocVOs.add(new HistoryDocPO(vo1,false,false,""));
//        historyDocVOs.add(new HistoryDocPO(vo2,false,false,""));
//
//        return historyDocVOs;
        return null;
    }

    @Override
    public ResultMessage update(HistoryDocPO po) {
        return ResultMessage.SUCCESS;
    }
}
