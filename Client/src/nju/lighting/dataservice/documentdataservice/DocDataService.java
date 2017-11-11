package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.doc.DocPO;
import shared.DocType;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.SaleRecordFilter;

import java.util.ArrayList;

public interface DocDataService {

    String create(DocType type);

    ResultMessage commitDoc(DocPO doc);

    ArrayList<DocPO> getDocs(DocumentFilter filter);

    ArrayList<DocPO> findSaleRecords(SaleRecordFilter filter);





}
