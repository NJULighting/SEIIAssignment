package nju.lighting.blservice.initblservice;

import shared.ResultMessage;
import nju.lighting.vo.InitVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface InitializationBLService {
    InitVO getInitInfo() throws RemoteException;

    ResultMessage commit(InitVO vo) throws RemoteException;

    ArrayList<InitVO> getHistoryInfo() throws RemoteException;
}
