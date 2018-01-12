package nju.lighting.bl.repositorybl;

import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.bl.utils.DataServiceBiFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import nju.lighting.po.repository.RepositoryTablePO;
import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableItemVO;
import nju.lighting.vo.repository.RepositoryTableVO;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

enum RepositoryManager {
    
    INSTANCE;

    private RepositoryDataService dataService;

    RepositoryManager() {
        try {
            dataService = DataFactory.getDataBase(RepositoryDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    public List<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate) {
        return DataServiceBiFunction.findToList(startDate, endDate,
                dataService::getRepositoryChanges, po -> new RepositoryChange(po).toVO());
    }

    public RepositoryTableVO getRepositoryTable() {
        try {
            RepositoryTablePO tablePO = dataService.getRepositoryTable();
            List<RepositoryTableItemVO> itemVOList = CollectionTransformer.toList(tablePO.getRepositoryTableItemPOS(),
                    po -> new RepositoryTableItemVO(po.getCommodityId(), po.getModelNumber(), po.getName(), po.getRepCount(),
                            po.getRecentInPrice(), po.getBatch(), po.getBatchNumber(), po.getDateOfProduction()));
            return new RepositoryTableVO(itemVOList);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new RepositoryTableVO(Collections.emptyList());
        }
    }
}
