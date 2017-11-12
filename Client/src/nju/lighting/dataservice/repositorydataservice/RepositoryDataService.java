package nju.lighting.dataservice.repositorydataservice;

import nju.lighting.po.repository.RepositoryChangePO;
import nju.lighting.po.repository.RepositoryTablePO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public interface RepositoryDataService {

    ArrayList<RepositoryChangePO> getRepositoryChanges(Date startDate, Date endDate) throws RemoteException;

    RepositoryTablePO getRepositoryTable() throws RemoteException;

}
