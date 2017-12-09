package nju.lighting.blservice.repositoryblservice;

import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableVO;

import java.util.Date;
import java.util.List;

public interface RepositoryBLService {

    List<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate);

    RepositoryTableVO getRepositoryTable();

}
