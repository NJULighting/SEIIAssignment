package nju.lighting.dataservice.repositorydataservice;

import nju.lighting.po.repository.RepositoryTablePO;
import nju.lighting.po.repository.RepositoryChangePO;

import java.util.Date;
import java.util.ArrayList;

public interface RepositoryDataService {

    ArrayList<RepositoryChangePO> getRepositoryChanges(Date startDate, Date endDate);

    RepositoryTablePO getRepositoryTable();

}
