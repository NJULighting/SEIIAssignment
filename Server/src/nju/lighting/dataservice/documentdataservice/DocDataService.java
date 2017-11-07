package nju.lighting.dataservice.documentdataservice;

import shared.DocumentFilter;
import nju.lighting.po.doc.DocPO;

import shared.ResultMessage;
import shared.DocType;

import java.util.ArrayList;

public interface DocDataService {

    String create(DocType type);

    ResultMessage commitDoc(DocPO doc);

    ArrayList<DocPO> getDocs(DocumentFilter filter);

    ArrayList<DocPO> findSaleRecords(SaleRecordFilter filter);





}
