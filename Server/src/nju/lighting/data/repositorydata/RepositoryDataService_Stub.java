package nju.lighting.data.repositorydata;

import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import nju.lighting.po.repository.RepositoryChangePO;
import nju.lighting.po.repository.RepositoryTablePO;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

public class RepositoryDataService_Stub implements RepositoryDataService {

    @Override
    public ArrayList<RepositoryChangePO> getRepositoryChanges(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public RepositoryTablePO getRepositoryTable() {
        return null;
    }

    @Override
    public ResultMessage changeRepository(RepositoryChangePO changePO) {
        return null;
    }

}
