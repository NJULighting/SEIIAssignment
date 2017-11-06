package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.doc.GeneralDocPO;
import nju.lighting.po.InitPO;
import nju.lighting.po.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DocDataService {

    ResultMessage insert(GeneralDocPO doc);

    ArrayList<GeneralDocPO> findByUser(String user);

    GeneralDocPO find(String id);

    ResultMessage update(GeneralDocPO doc);

    ArrayList<GeneralDocPO> findByTime(long start, long end);

    ResultMessage newAccount(InitPO po);

    ArrayList<InitPO> getInitInfo() throws RemoteException;

    void init();

    void finish();
}
