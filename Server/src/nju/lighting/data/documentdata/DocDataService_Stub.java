package nju.lighting.data.documentdata;

import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import shared.*;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class DocDataService_Stub implements DocDataService {

    @Override
    public TwoTuple<ResultMessage, String> commitDoc(DocPO doc) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage updateDoc(DocPO doc) throws RemoteException {
        return null;
    }

    @Override
    public List<DocPO> findByUserId(String id) throws RemoteException {
        return null;
    }

    @Override
    public List<DocPO> findByState(DocState docState) throws RemoteException {
        return null;
    }

    @Override
    public List<DocPO> findByType(DocType type) throws RemoteException {
        return null;
    }

    @Override
    public List<DocPO> findByTime(Date from, Date to) throws RemoteException {
        return null;
    }

    @Override
    public List<DocPO> findByTimeAndType(Date from, Date to, DocType type) throws RemoteException {
        return null;
    }
}
