package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.HistoryDocBLService;
import nju.lighting.vo.HistoryDocVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HistoryDoc implements HistoryDocBLService {

    @Override
    public ArrayList<HistoryDocVO> getHistoryDocs(String useid, int startCount, int endCount) throws RemoteException {
        return null;
    }
}
