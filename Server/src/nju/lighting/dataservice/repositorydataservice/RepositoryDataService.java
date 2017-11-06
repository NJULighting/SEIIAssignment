package nju.lighting.dataservice.repositorydataservice;

import nju.lighting.po.repository.RepositoryChangePO;
import java.util.ArrayList;

public interface RepositoryDataService {

    ArrayList<RepositoryChangePO> get (long start, long end);

}
