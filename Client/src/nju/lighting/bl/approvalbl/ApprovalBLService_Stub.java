package nju.lighting.bl.approvalbl;

import javafx.scene.control.Alert;
import nju.lighting.bl.documentbl.costdoc.CostDocItem;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.account.AccountLogVO;
import nju.lighting.vo.account.AccountVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import nju.lighting.vo.doc.alertdoc.AlertDocItemVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import nju.lighting.vo.doc.costdoc.CostDocVO;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocItemVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocVO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import nju.lighting.vo.doc.salesdoc.SalesReturnDocVO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;
import nju.lighting.vo.doc.stockdoc.StockDocVO;
import nju.lighting.vo.doc.stockdoc.StockReturnDocVO;
import shared.CostDocItemType;
import shared.DocType;
import shared.LossAndGainItemType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
@Deprecated
public class ApprovalBLService_Stub implements ApprovalBLService {
    @Override
    public ArrayList<DocVO> getDocumentList() {
        BasicCommodityItemVO commodityVO1 = new BasicCommodityItemVO("xx0002222", "日本LED无障碍灯泡",
                100, 100, 100);
        BasicCommodityItemVO commodityVO2 = new BasicCommodityItemVO("xx0002223", "日本LED无障碍灯泡" + "-b",
                100, 100, 100);
        BasicCommodityItemVO commodityVO3 = new BasicCommodityItemVO("xx0002224", "日本LED无障碍灯泡" + "-c",
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

        AccountTransferItemVO accountTransferItemVO1 = new AccountTransferItemVO(100, "h", "sadasdasd", 1);

        List<AccountTransferItemVO> accountTransferItemVOList = new ArrayList<>();

        accountTransferItemVOList.add(accountTransferItemVO1);
        accountTransferItemVOList.add(accountTransferItemVO1);
        accountTransferItemVOList.add(accountTransferItemVO1);
        accountTransferItemVOList.add(accountTransferItemVO1);


        AccountIODocVO accountIODocVO = new AccountIODocVO(new Date(), "00001", "SKD-20171211-00001",
                DocType.ACCOUNT_IN, "刘钦", accountTransferItemVOList);

        GiftDocVO vo1 = new GiftDocVO(new Date(), "00001", "ZPD-20171021-0009", gifts1, 1, 1);
        GiftDocVO vo2 = new GiftDocVO(new Date(), "00001", "ZPD-20171021-0010", gifts2, 3, 2);
        GiftDocVO vo3 = new GiftDocVO(new Date(), "00001", "ZPD-20171021-0011", gifts2, 4, 3);
        GiftDocVO vo4 = new GiftDocVO(new Date(), "00001", "ZPD-20171021-0012", gifts2, 1, 4);
        GiftDocVO vo5 = new GiftDocVO(new Date(), "00001", "ZPD-20171021-0013", gifts2, 1, 1);
        GiftDocVO vo6 = new GiftDocVO(new Date(), "00001", "ZPD-20171021-0015", gifts2, 1, 1);

        //报损报溢单
        ArrayList<LossAndGainDocItemVO> items = new ArrayList<>();
        LossAndGainDocItemVO lossItemVO1 = new LossAndGainDocItemVO(commodityVO1, 17, LossAndGainItemType.LOSS);
        LossAndGainDocItemVO lossItemVO2 = new LossAndGainDocItemVO(commodityVO2, 5, LossAndGainItemType.GAIN);
        LossAndGainDocItemVO lossItemVO3 = new LossAndGainDocItemVO(commodityVO3, 80, LossAndGainItemType.LOSS);
        items.add(lossItemVO1);
        items.add(lossItemVO2);
        items.add(lossItemVO3);
        LossAndGainDocVO lossAndGainDocVO = new LossAndGainDocVO(new Date(), "00001", "BSBY_01", DocType.LOSS_AND_GAIN, items, "我就觉得好玩然后创建了一个:)");

        //销售单
        ArrayList<SalesDocItemVO> salesDocItem = new ArrayList<>();
        SalesDocItemVO salesDocItemVO1 = new SalesDocItemVO(134,"假装有备注！",commodityVO1);
        SalesDocItemVO salesDocItemVO2 = new SalesDocItemVO(2000,"下一个没有备注",commodityVO2);
        SalesDocItemVO salesDocItemVO3 = new SalesDocItemVO(22,"",commodityVO3);
        salesDocItem.add(salesDocItemVO1);
        salesDocItem.add(salesDocItemVO2);
        salesDocItem.add(salesDocItemVO3);
        SalesDocVO salesDocVO = new SalesDocVO(new Date(), "00001", 1, "王大卫", "默认仓库", "这个销售单很重要，要好好保密",
                144.3, 19, salesDocItem);

        //销售退货单
        SalesReturnDocVO salesReturnDocVO = new SalesReturnDocVO(new Date(), "00001", 1, "王大卫", "默认仓库", "这个客户可以消失了",
                144.3, 19, salesDocItem);

        //进货单
        ArrayList<StockDocItemVO> stockDocItem = new ArrayList<>();
        StockDocItemVO stockDocItemVO1 = new StockDocItemVO(commodityVO1,233,"我说没有备注就没有备注");
        StockDocItemVO stockDocItemVO2 = new StockDocItemVO(commodityVO2,109,":)");
        StockDocItemVO stockDocItemVO3 = new StockDocItemVO(commodityVO3 ,100,"");
        stockDocItem.add(stockDocItemVO1);
        stockDocItem.add(stockDocItemVO2);
        stockDocItem.add(stockDocItemVO3);
        StockDocVO stockDocVO = new StockDocVO(new Date(),"00001","1","默认仓库","记得在货里藏点那东西",stockDocItem);

        //进货退货单
        StockReturnDocVO stockReturnDocVO = new StockReturnDocVO(new Date(),"00001","1","默认仓库","任务已完成",stockDocItem);

        //报警单
        ArrayList<AlertDocItemVO> alertDocItem = new ArrayList<>();
        AlertDocItemVO alertDocItemVO1 = new AlertDocItemVO(commodityVO1,2222);
        AlertDocItemVO alertDocItemVO2 = new AlertDocItemVO(commodityVO2,222);
        AlertDocItemVO alertDocItemVO3 = new AlertDocItemVO(commodityVO3,22);
        alertDocItem.add(alertDocItemVO1);
        alertDocItem.add(alertDocItemVO2);
        alertDocItem.add(alertDocItemVO3);
        AlertDocVO alertDocVO = new AlertDocVO("00001",new Date(),alertDocItem,"老板你看这个警戒设置是不是棒棒哒2");


        //账户
        AccountVO accountVO = new AccountVO("00001","匿名氏",20000, new ArrayList<>());
        //现金费用单
        ArrayList<CostDocItemVO> costDocItem = new ArrayList<>();
        CostDocItemVO costDocItemVO1 = new CostDocItemVO(CostDocItemType.ACCOMMODATION,123,"啦啦啦");
        CostDocItemVO costDocItemVO2 = new CostDocItemVO(CostDocItemType.BUSINESS_TRIP,132,"耶耶耶");
        CostDocItemVO costDocItemVO3 = new CostDocItemVO(CostDocItemType.COMMUNICATION,213,"突突突");
        CostDocItemVO costDocItemVO4 = new CostDocItemVO(CostDocItemType.MEAL,231,"咩咩咩");
        CostDocItemVO costDocItemVO5 = new CostDocItemVO(CostDocItemType.MEETING,312,"嘻嘻嘻");
        CostDocItemVO costDocItemVO6 = new CostDocItemVO(CostDocItemType.OFFICE,321,"...");
        CostDocItemVO costDocItemVO7 = new CostDocItemVO(CostDocItemType.OTHER,321,"..");
        CostDocItemVO costDocItemVO8 = new CostDocItemVO(CostDocItemType.RECEPTOIN,321,".");
        CostDocItemVO costDocItemVO9 = new CostDocItemVO(CostDocItemType.TRANSPORTATION,321,"");
        costDocItem.add(costDocItemVO1);
        costDocItem.add(costDocItemVO2);
        costDocItem.add(costDocItemVO3);
        costDocItem.add(costDocItemVO4);
        costDocItem.add(costDocItemVO5);
        costDocItem.add(costDocItemVO6);
        costDocItem.add(costDocItemVO7);
        costDocItem.add(costDocItemVO8);
        costDocItem.add(costDocItemVO9);
        CostDocVO costDocVO = new CostDocVO(new Date(),"00001", accountVO,costDocItem);



        ArrayList<DocVO> DocVOs = new ArrayList<>();
        DocVOs.add(vo1);
        DocVOs.add(vo2);
        DocVOs.add(vo3);
        DocVOs.add(vo4);
        DocVOs.add(vo5);
        DocVOs.add(vo6);
        DocVOs.add(accountIODocVO);
        DocVOs.add(lossAndGainDocVO);
        DocVOs.add(salesDocVO);
        DocVOs.add(salesReturnDocVO);
        DocVOs.add(stockDocVO);
        DocVOs.add(stockReturnDocVO);
        DocVOs.add(alertDocVO);
        DocVOs.add(costDocVO);


        return DocVOs;


    }

    @Override
    public ResultMessage approve(HistoryDocVO vo) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage approveAll(List<HistoryDocVO> voList) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage reject(HistoryDocVO vo) {
        if (vo.getComment().length() != 0)
            return ResultMessage.SUCCESS;
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) {
        return ResultMessage.SUCCESS;
    }

}
