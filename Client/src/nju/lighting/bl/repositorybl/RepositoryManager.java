package nju.lighting.bl.repositorybl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.utils.DataServiceBiFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableVO;

import javax.naming.NamingException;
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
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new RepositoryTableVO(commodityInfo.getRepositoryTableItemList());
    }
}
