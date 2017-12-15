package nju.lighting.bl.repositorybl;

import nju.lighting.blservice.repositoryblservice.RepositoryBLService;
import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableVO;

import java.util.Date;
import java.util.List;

public class RepositoryController implements RepositoryBLService {
    private RepositoryManager manager = RepositoryManager.INSTANCE;

    @Override
    public List<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate) {
        return manager.getRepositoryChanges(startDate, endDate);
    }

    @Override
    public RepositoryTableVO getRepositoryTable() {
        return manager.getRepositoryTable();
    }
}
