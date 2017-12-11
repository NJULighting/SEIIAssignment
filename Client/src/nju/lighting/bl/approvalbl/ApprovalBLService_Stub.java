package nju.lighting.bl.approvalbl;

import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocType;
import shared.HistoryDocType;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class ApprovalBLService_Stub implements ApprovalBLService {
    @Override
    public ArrayList<DocVO> getDocumentList() {
        BasicCommodityItemVO commodityVO1 = new BasicCommodityItemVO("xx0002222","日本LED无障碍灯泡",
                100, 100,100);
        BasicCommodityItemVO commodityVO2 = new BasicCommodityItemVO("xx0002223","日本LED无障碍灯泡" + "-b",
                100, 100,100);
        BasicCommodityItemVO commodityVO3 = new BasicCommodityItemVO("xx0002224","日本LED无障碍灯泡" + "-c",
                100, 100, 100);

        GiftItemVO giftItemVO1 = new GiftItemVO(commodityVO1, 3);
        GiftItemVO giftItemVO2 = new GiftItemVO(commodityVO2, 1);
        GiftItemVO giftItemVO3 = new GiftItemVO(commodityVO3, 11);
        GiftItemVO giftItemVO4 = new GiftItemVO(commodityVO3, 12);
        GiftItemVO giftItemVO5 = new GiftItemVO(commodityVO3, 13);
        GiftItemVO giftItemVO6 = new GiftItemVO(commodityVO3, 14);
        GiftItemVO giftItemVO7 = new GiftItemVO(commodityVO3, 15);
        GiftItemVO giftItemVO8 = new GiftItemVO(commodityVO3, 16);
        GiftItemVO giftItemVO9 = new GiftItemVO(commodityVO3, 17);
        GiftItemVO giftItemV10 = new GiftItemVO(commodityVO3, 18);
        GiftItemVO giftItemV11 = new GiftItemVO(commodityVO3, 19);
        GiftItemVO giftItemV12 = new GiftItemVO(commodityVO3, 20);
        GiftItemVO giftItemV13 = new GiftItemVO(commodityVO3, 21);
        GiftItemVO giftItemV14 = new GiftItemVO(commodityVO3, 22);
        GiftItemVO giftItemV15 = new GiftItemVO(commodityVO3, 23);
        GiftItemVO giftItemV16 = new GiftItemVO(commodityVO3, 24);
        GiftItemVO giftItemV17 = new GiftItemVO(commodityVO3, 25);
        GiftItemVO giftItemV18 = new GiftItemVO(commodityVO3, 26);







        ArrayList<GiftItemVO> gifts1 = new ArrayList<>();
        ArrayList<GiftItemVO> gifts2 = new ArrayList<>();

        gifts1.add(giftItemVO1);
        gifts1.add(giftItemVO2);
        gifts1.add(giftItemVO3);
        gifts1.add(giftItemVO4);
        gifts1.add(giftItemVO5);
        gifts1.add(giftItemVO6);
        gifts1.add(giftItemVO7);
        gifts1.add(giftItemVO8);
        gifts1.add(giftItemVO9);
        gifts1.add(giftItemV10);
        gifts1.add(giftItemV11);
        gifts1.add(giftItemV12);
        gifts1.add(giftItemV13);
        gifts1.add(giftItemV14);
        gifts1.add(giftItemV15);
        gifts1.add(giftItemV16);
        gifts1.add(giftItemV17);
        gifts1.add(giftItemV18);



        gifts2.add(giftItemVO2);
        gifts2.add(giftItemVO3);
        gifts2.add(giftItemVO3);


        GiftDocVO vo1 = new GiftDocVO(null,"00001", "ZPD-20171021-0009",DocType.GIFT,gifts1,"hiahia");
        GiftDocVO vo2 = new GiftDocVO(null,"00001", "ZPD-20171021-0010",DocType.GIFT, gifts2, "N0.1");
        GiftDocVO vo3 = new GiftDocVO(null,"00001", "ZPD-20171021-0011",DocType.GIFT, gifts2, "N0.1");
        GiftDocVO vo4 = new GiftDocVO(null,"00001", "ZPD-20171021-0012",DocType.GIFT, gifts2, "N0.1");
        GiftDocVO vo5 = new GiftDocVO(null,"00001", "ZPD-20171021-0013",DocType.GIFT, gifts2, "N0.1");
        GiftDocVO vo6 = new GiftDocVO(null,"00001", "ZPD-20171021-0015",DocType.GIFT, gifts2, "N0.1");
        ArrayList<DocVO> DocVOs = new ArrayList<>();
        DocVOs.add(vo1);
        DocVOs.add(vo2);
        DocVOs.add(vo3);
        DocVOs.add(vo4);
        DocVOs.add(vo5);
        DocVOs.add(vo6);

        return DocVOs;


    }

    @Override
    public ResultMessage approve(HistoryDocVO vo) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage approveAll(ArrayList<HistoryDocVO> voList) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage reject(HistoryDocVO vo) {
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage saveAndApprove(HistoryDocVO vo) {
        return ResultMessage.SUCCESS;
    }
}
