package nju.lighting.blservice.repositoryblservice;

import java.rmi.Remote;
import nju.lighting.vo.RepositoryChangeVO;
import nju.lighting.vo.RepositoryTableVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RepositoryBLService extends Remote{

    ArrayList<RepositoryChangeVO> getRepositoryChanges (long startTime, long endTime) throws  RemoteException;

    RepositoryTableVO getRepositoryTable() throws RemoteException;

}
