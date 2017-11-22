package nju.lighting.po.repository;

import java.util.ArrayList;

public class RepositoryTablePO {

    private ArrayList<RepositoryTableItemPO> repositoryTableItemPOS;

    public RepositoryTablePO(ArrayList<RepositoryTableItemPO> repositoryTableItemPOS) {
        this.repositoryTableItemPOS = repositoryTableItemPOS;
    }

    public ArrayList<RepositoryTableItemPO> getRepositoryTableItemPOS() {
        return repositoryTableItemPOS;
    }

    public void setRepositoryTableItemPOS(ArrayList<RepositoryTableItemPO> repositoryTableItemPOS) {
        this.repositoryTableItemPOS = repositoryTableItemPOS;
    }
}
