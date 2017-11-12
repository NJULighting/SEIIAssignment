package nju.lighting.bl.repositorybl;

import nju.lighting.blservice.repositoryblservice.RepositoryBLService;
import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class RepositoryController implements RepositoryBLService {

    @Override
    public ArrayList<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public RepositoryTableVO getRepositoryTable() {
        return null;
    }
}
