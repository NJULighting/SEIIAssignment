package nju.lighting.dataservice.documentdataservice;

import nju.lighting.bl.documentbl.DocumentFilter;
import nju.lighting.bl.documentbl.SaleRecordFilter;
import nju.lighting.po.BusinessHistoryItemPO;
import nju.lighting.po.RevenueAndExpenditurePO;
import nju.lighting.po.doc.DocFilter;
import nju.lighting.po.doc.DocPO;
<<<<<<< HEAD
import nju.lighting.po.init.InitPO;
=======
>>>>>>> 5b3f1742e46a77e053b522a2d8a46079947fcaa0
import nju.lighting.po.ResultMessage;
import nju.lighting.po.doc.DocType;

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
