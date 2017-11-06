package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.doc.GeneralDocPO;
import nju.lighting.po.doc.DocType;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface ViewTablesDataService {
    ArrayList<GeneralDocPO> findSaleRecords(long startDate, long endDate, String Commodity, String customer, String user, String repository);

    ArrayList<GeneralDocPO> findDocuments(long startDate, long endDate, DocType type, String customer, String user, String repository);

    public ArrayList<GeneralDocPO> findRevenueAndExpenditure(long startDate, long endDate);


}
