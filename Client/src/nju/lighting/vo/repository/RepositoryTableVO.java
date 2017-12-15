package nju.lighting.vo.repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryTableVO {

    private List<RepositoryTableItemVO> repositoryTableItemVOS;

    public RepositoryTableVO(List<RepositoryTableItemVO> repositoryTableItemVOS) {
        this.repositoryTableItemVOS = repositoryTableItemVOS;
    }

    public List<RepositoryTableItemVO> getRepositoryTableItemVOS() {
        return repositoryTableItemVOS;
    }

    public void setRepositoryTableItemVOS(ArrayList<RepositoryTableItemVO> repositoryTableItemVOS) {
        this.repositoryTableItemVOS = repositoryTableItemVOS;
    }
}
