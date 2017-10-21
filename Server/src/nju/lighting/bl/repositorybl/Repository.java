package nju.lighting.bl.repositorybl;

import nju.lighting.blservice.repositoryblservice.RepositoryBLService;
import nju.lighting.vo.RepositoryChangeVO;
import nju.lighting.vo.RepositoryTableVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Repository implements RepositoryBLService {
    @Override
    public ArrayList<RepositoryChangeVO> getRepositoryChanges(long startTime, long endTime) throws RemoteException {
        return null;
    }

    @Override
    public RepositoryTableVO getRepositoryTable() throws RemoteException {
        return null;
    }
}
