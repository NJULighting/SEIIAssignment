package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.doc.DocPO;
import shared.DocType;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.SaleRecordFilter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DocDataService extends Remote {

    String create(DocType type) throws RemoteException;

    ResultMessage commitDoc(DocPO doc) throws RemoteException;

    ArrayList<DocPO> getDocs(DocumentFilter filter) throws RemoteException;

    ArrayList<DocPO> findSaleRecords(SaleRecordFilter filter) throws RemoteException;


}
