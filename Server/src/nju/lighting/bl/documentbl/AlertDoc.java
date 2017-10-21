package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.AlertDocBLService;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.AlertDocVO;
import nju.lighting.vo.RepositoryChangeVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AlertDoc implements AlertDocBLService {

    @Override
    public ResultMessage addAlertDoc(AlertDocVO alertDoc) throws RemoteException {
        return null;
    }

    @Override
    public AlertDocVO getAlertDoc(String docID) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<AlertDocVO> getAlertDocs(String userid) throws RemoteException {
        return null;
    }

    @Override
    public void triggered(RepositoryChangeVO change) throws RemoteException {

    }
}
