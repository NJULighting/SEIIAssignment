package nju.lighting.data.documentdata;

import nju.lighting.dataservice.documentdataservice.ViewTablesDataService;
import nju.lighting.po.*;
import nju.lighting.po.doc.DocType;
import nju.lighting.po.doc.DocPO;


import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description
 *
 * @author 陈俊宇
 */
public class ViewTableDataService_Stub implements ViewTablesDataService {
    @Override
    public ArrayList<DocPO> findSaleRecords(long startDate, long endDate, String Commodity, String customer, String user, String repository) {

        CustomerPO customerVO=new CustomerPO(0001, CustomerType.SALESPERSON, CustomerGrade.FIVE,"张三","111","xxx","1213"
                ,"213213@qq.com",200,0,300,"xl");
        UserPO userVO=new UserPO("ml","0000","0000", Identity.FINANCE);


        SalesTypeDocPO salesDocVO1=new SalesTypeDocPO("XSD-20171022-00001",customerVO,"xl","NO.1",userVO,null,"",
                100,10,5,85);

        ArrayList<DocPO> salesDocPOS=new ArrayList<>();

        salesDocPOS.add(salesDocVO1);
        return salesDocPOS;
    }

    @Override
    public ArrayList<DocPO> findDocuments(long startDate, long endDate, DocType type, String customer, String user, String repository) {

       //同上
        return null;
    }

    @Override
    public ArrayList<DocPO> findRevenueAndExpenditure(long startDate, long endDate) {

        //同上
        return null;
    }
}
