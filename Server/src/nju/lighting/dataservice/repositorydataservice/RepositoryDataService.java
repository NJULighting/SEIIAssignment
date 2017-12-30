package nju.lighting.dataservice.repositorydataservice;

import nju.lighting.po.repository.RepositoryChangePO;
import nju.lighting.po.repository.RepositoryTablePO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Date;

public interface RepositoryDataService extends Remote {

    List<RepositoryChangePO> getRepositoryChanges(Date startDate, Date endDate) throws RemoteException;

    RepositoryTablePO getRepositoryTable() throws RemoteException;

    ResultMessage changeRepository(RepositoryChangePO changePO) throws RemoteException;

    byte[] exportExcel() throws RemoteException;

}
