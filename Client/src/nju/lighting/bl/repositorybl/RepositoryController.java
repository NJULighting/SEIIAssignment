package nju.lighting.bl.repositorybl;

import nju.lighting.blservice.repositoryblservice.RepositoryBLService;
import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableVO;

import java.util.Date;
import java.util.List;

public class RepositoryController implements RepositoryBLService {

    @Override
    public List<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public RepositoryTableVO getRepositoryTable() {
        return null;
    }
}
