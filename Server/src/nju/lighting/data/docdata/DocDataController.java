package nju.lighting.data.docdata;

import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/29.
 * Description:
 *
 * @author iznauy
 */
public class DocDataController implements DocDataService {

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
