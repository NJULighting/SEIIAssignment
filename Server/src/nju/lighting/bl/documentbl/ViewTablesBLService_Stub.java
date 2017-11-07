package nju.lighting.bl.documentbl;


import nju.lighting.blservice.documentblservice.ViewTablesBLService;
import nju.lighting.vo.*;
import shared.CustomerGrade;
import shared.CustomerType;
import shared.DocType;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class ViewTablesBLService_Stub implements ViewTablesBLService {


    @Override
    public ArrayList<SaleRecordItemVO> findSaleRecords(long startDate, long endDate, String Commodity, String customer, String user, String repository) throws RemoteException {

        CustomerVO customerVO=new CustomerVO(0001, CustomerType.SALESPERSON, CustomerGrade.FIVE,"张三","111","xxx","1213"
                ,"213213@qq.com",200,0,300,"xl");




        ArrayList<SaleRecordItemVO> saleRecordItemVOs=new ArrayList<>();
        saleRecordItemVOs.add(new SaleRecordItemVO(1000,"灯泡","-a",3,20,60,null
        ) );

        return saleRecordItemVOs;
    }

    @Override
    public ArrayList<BusinessHistoryItemVO> findDocuments(long startDate, long endDate, DocType type, String customer, String user, String repository) throws RemoteException {

        CommodityVO commodityVO1 = new CommodityVO("日本LED无障碍灯泡", "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityVO commodityVO2 = new CommodityVO("日本LED无障碍灯泡" + "-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        ArrayList<GiftItemVO> gifts1=new ArrayList<>();

        GiftItemVO giftItemVO1=new GiftItemVO(commodityVO1,1);
        GiftItemVO giftItemVO2=new GiftItemVO(commodityVO2,1);
        gifts1.add(giftItemVO1);
        gifts1.add(giftItemVO2);



        GiftDocVO vo1=new GiftDocVO(gifts1,"ZPD-20171021-0001","甲方","N0.1");

        ArrayList <BusinessHistoryItemVO> businessHistoryItemVOs= new ArrayList<BusinessHistoryItemVO>();
        businessHistoryItemVOs.add(new BusinessHistoryItemVO(1000,DocType.GIFTDOC,vo1,"xl","xm","No.1"));

        return businessHistoryItemVOs;
    }

    @Override
    public RevenueAndExpenditureVO findRevenueAndExpenditure(long startDate, long endDate) throws RemoteException {

        RevenueAndExpenditureVO vo1=new RevenueAndExpenditureVO(100,100,100,0,
                2, 5,15,10,50,217);


        return vo1;
    }
}
