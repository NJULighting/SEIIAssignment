package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.AlertDocBLService;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.AlertDocItemVO;
import nju.lighting.vo.AlertDocVO;
import nju.lighting.vo.RepositoryChangeVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AlertDocBLService_Stub implements AlertDocBLService {

    @Override
    public ResultMessage addAlertDoc(AlertDocVO alertDoc) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public AlertDocVO getAlertDoc(String docID) throws RemoteException {
        AlertDocItemVO vo1 = new AlertDocItemVO("xx002202", 100);
        AlertDocItemVO vo2 = new AlertDocItemVO("xx002203", 100);
        ArrayList<AlertDocItemVO> arrayList = new ArrayList<>();
        arrayList.add(vo1);
        arrayList.add(vo2);
        AlertDocVO alertDocVO = new AlertDocVO(arrayList, "抢手货！最近销售速度非常快！");
        return alertDocVO;
    }

    @Override
    public ArrayList<AlertDocVO> getAlertDocs(String userid) throws RemoteException {
        AlertDocItemVO vo1 = new AlertDocItemVO("xx002202", 100);
        AlertDocItemVO vo2 = new AlertDocItemVO("xx002203", 100);
        ArrayList<AlertDocItemVO> arrayList = new ArrayList<>();
        arrayList.add(vo1);
        arrayList.add(vo2);
        AlertDocVO alertDocVO = new AlertDocVO(arrayList, "抢手货！最近销售速度非常快！");
        ArrayList<AlertDocVO> docs = new ArrayList<>();
        docs.add(alertDocVO);
        return docs;
    }

    @Override
    public void triggered(RepositoryChangeVO change) throws RemoteException {
        return;
    }
}
