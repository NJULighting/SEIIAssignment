package nju.lighting.bl.approvalbl;

import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.CommodityVO;
import nju.lighting.vo.GiftDocVO;
import nju.lighting.vo.GiftItemVO;
import nju.lighting.vo.HistoryDocVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class ApprovalBLService_Stub implements ApprovalBLService {
    @Override
    public ArrayList<HistoryDocVO> getDocumentList() throws RemoteException {
        CommodityVO commodityVO1 = new CommodityVO("日本LED无障碍灯泡", "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityVO commodityVO2 = new CommodityVO("日本LED无障碍灯泡" + "-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        CommodityVO commodityVO3 = new CommodityVO("日本LED无障碍灯泡" + "-c", "LED", "xx0002224",
                100, 100, new ArrayList<Double>(), 130.0, new ArrayList<Double>());

        GiftItemVO giftItemVO1=new GiftItemVO(commodityVO1,3);
        GiftItemVO giftItemVO2=new GiftItemVO(commodityVO2,1);
        GiftItemVO giftItemVO3=new GiftItemVO(commodityVO3,10);

        ArrayList<GiftItemVO> gifts1=new ArrayList<>();
        ArrayList<GiftItemVO> gifts2=new ArrayList<>();

        gifts1.add(giftItemVO1);
        gifts1.add(giftItemVO2);
        gifts2.add(giftItemVO2);
        gifts2.add(giftItemVO3);


        GiftDocVO vo1=new GiftDocVO(gifts1,"ZPD-20171021-0001","甲方","N0.1");
        GiftDocVO vo2=new GiftDocVO(gifts2,"ZPD-20171021-0010","甲方","N0.1");

        ArrayList<HistoryDocVO> historyDocVOs=new ArrayList<>();
        historyDocVOs.add(new HistoryDocVO(vo1,false,false,""));
        historyDocVOs.add(new HistoryDocVO(vo2,false,false,""));

        return historyDocVOs;


    }

    @Override
    public ResultMessage approve(HistoryDocVO vo) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage approveAll(ArrayList<HistoryDocVO> voList) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage reject(HistoryDocVO vo) throws RemoteException {
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage saveAndApprove(HistoryDocVO vo) throws RemoteException {
        return ResultMessage.SUCCESS;
    }
}
