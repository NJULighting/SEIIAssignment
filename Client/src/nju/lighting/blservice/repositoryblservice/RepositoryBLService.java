package nju.lighting.blservice.repositoryblservice;

import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableVO;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

public interface RepositoryBLService extends Remote {

    ArrayList<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate) ;

    RepositoryTableVO getRepositoryTable() ;

}
