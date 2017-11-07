package nju.lighting.vo.repository;

import java.util.ArrayList;

public class RepositoryTableVO {

    private ArrayList<RepositoryTableItemVO> repositoryTableItemVOS;

    public RepositoryTableVO(ArrayList<RepositoryTableItemVO> repositoryTableItemVOS) {
        this.repositoryTableItemVOS = repositoryTableItemVOS;
    }

    public ArrayList<RepositoryTableItemVO> getRepositoryTableItemVOS() {
        return repositoryTableItemVOS;
    }

    public void setRepositoryTableItemVOS(ArrayList<RepositoryTableItemVO> repositoryTableItemVOS) {
        this.repositoryTableItemVOS = repositoryTableItemVOS;
    }
}
