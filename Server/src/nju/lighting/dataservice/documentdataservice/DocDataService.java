package nju.lighting.dataservice.documentdataservice;

import shared.DocumentFilter;
import shared.SaleRecordFilter;
import nju.lighting.po.BusinessHistoryItemPO;
import nju.lighting.po.RevenueAndExpenditurePO;
import shared.DocFilter;
import nju.lighting.po.doc.DocPO;

import nju.lighting.po.ResultMessage;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

public interface DocDataService {

    String create(DocType type);

    ResultMessage commitDoc(DocPO doc);

    ArrayList<DocPO> getDocs(DocFilter filter);

    ArrayList<DocPO> findSaleRecords(SaleRecordFilter filter);

    ArrayList<BusinessHistoryItemPO> findDocuments(DocumentFilter filter);

    RevenueAndExpenditurePO findRevenueAndExpenditure(Date startDate, Date endDate);


}
