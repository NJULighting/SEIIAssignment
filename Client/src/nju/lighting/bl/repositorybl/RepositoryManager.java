package nju.lighting.bl.repositorybl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public enum RepositoryManager implements RepositoryInfo {
    INSTANCE;

    private RepositoryDataService dataService;
    private Logger logger;

    RepositoryManager() {
        try {
            dataService = DataFactory.getDataBase(RepositoryDataService.class);
            logger = new UserLogger();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    public List<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate) {
        return null;
    }

    public RepositoryTableVO getRepositoryTable() {
        return null;
    }

    /**
     * 提供给其他模块用于变更库存信息
     * @param change
     * @return
     */
    @Override
    public ResultMessage changeRepository(RepositoryChange change) {
        return null;
    }
}
