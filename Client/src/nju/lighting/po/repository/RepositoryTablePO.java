package nju.lighting.po.repository;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositoryTablePO implements Serializable {
    private static final long serialVersionUID = 7451970813220395352L;

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
