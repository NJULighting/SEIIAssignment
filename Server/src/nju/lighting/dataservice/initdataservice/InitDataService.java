package nju.lighting.dataservice.initdataservice;

import nju.lighting.po.init.InitPO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/27.
 * Description:
 * @author iznauy
 */
public interface InitDataService extends Remote{

    ResultMessage createInit(String userId, Date date) throws RemoteException;

    List<InitPO> getAllInit() throws RemoteException;

}
