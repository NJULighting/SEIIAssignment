package nju.lighting.dataservice.initdataservice;

import nju.lighting.po.init.InitPO;
import shared.Result;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/27.
 * Description:
 * @author iznauy
 */
public interface InitDataService {

    Result<InitPO> createInit(String userId, Date date) throws RemoteException;

    List<InitPO> getAllInit() throws RemoteException;

}
