package nju.lighting.data.repositorydata;

import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import nju.lighting.po.RepositoryChangePO;

import java.util.ArrayList;

public class RepositoryDataService_Stub implements RepositoryDataService {

    @Override
    public ArrayList<RepositoryChangePO> get(long start, long end) {
        RepositoryChangePO repositoryChangePO = new RepositoryChangePO(RepositoryChangePO.BUY, 1000, "xx002227", 100000.5);
        ArrayList<RepositoryChangePO> changePOS = new ArrayList<>();
        changePOS.add(repositoryChangePO);
        return changePOS;
    }
}
