package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.doc.DocPO;
import nju.lighting.po.init.InitPO;
import nju.lighting.po.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DocDataService {

    ResultMessage insert(DocPO doc);

    ArrayList<DocPO> findByUser(String user);

    DocPO find(String id);

    ResultMessage update(DocPO doc);

    ArrayList<DocPO> findByTime(long start, long end);

    ResultMessage newAccount(InitPO po);

    ArrayList<InitPO> getInitInfo() throws RemoteException;

    void init();

    void finish();
}
