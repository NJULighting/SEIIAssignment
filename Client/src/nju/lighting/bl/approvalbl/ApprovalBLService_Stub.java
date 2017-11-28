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
        BasicCommodityItemVO commodityVO1 = new BasicCommodityItemVO("xx0002222","日本LED无障碍灯泡", null,
                100, 100,100);
        BasicCommodityItemVO commodityVO2 = new BasicCommodityItemVO("xx0002223","日本LED无障碍灯泡" + "-b", null,
                100, 100,100);
        BasicCommodityItemVO commodityVO3 = new BasicCommodityItemVO("xx0002224","日本LED无障碍灯泡" + "-c", null,
                100, 100, 100);

        GiftItemVO giftItemVO1 = new GiftItemVO(commodityVO1, 3);
        GiftItemVO giftItemVO2 = new GiftItemVO(commodityVO2, 1);
        GiftItemVO giftItemVO3 = new GiftItemVO(commodityVO3, 10);

        ArrayList<GiftItemVO> gifts1 = new ArrayList<>();
        ArrayList<GiftItemVO> gifts2 = new ArrayList<>();

        gifts1.add(giftItemVO1);
        gifts1.add(giftItemVO2);
        gifts2.add(giftItemVO2);
        gifts2.add(giftItemVO3);


        GiftDocVO vo1 = new GiftDocVO(null,"00001", "ZPD-20171021-0009",DocType.GIFT,gifts1,"hiahia");
        GiftDocVO vo2 = new GiftDocVO(null,"00001", "ZPD-20171021-0010",DocType.GIFT, gifts2, "N0.1");

        ArrayList<DocVO> DocVOs = new ArrayList<>();
        DocVOs.add(vo1);
        DocVOs.add(vo2);

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
