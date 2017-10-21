package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.HistoryDocVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface HistoryDocBLService extends Remote {

    ArrayList<HistoryDocVO> getHistoryDocs(String useid, int startCount, int endCount) throws RemoteException;

}
