package nju.lighting.po.repository;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositoryTablePO implements Serializable {

    private static final long serialVersionUID = 7451970813220395352L;
    private ArrayList<RepositoryTableItemPO> repositoryTableItemPOS;

    /**
     * 无参构造器
     */
    public RepositoryTablePO() {
        repositoryTableItemPOS = new ArrayList<>();
    }

    public RepositoryTablePO(ArrayList<RepositoryTableItemPO> repositoryTableItemPOS) {
        this.repositoryTableItemPOS = repositoryTableItemPOS;
    }

    public ArrayList<RepositoryTableItemPO> getRepositoryTableItemPOS() {
        return repositoryTableItemPOS;
    }

    public void setRepositoryTableItemPOS(ArrayList<RepositoryTableItemPO> repositoryTableItemPOS) {
        this.repositoryTableItemPOS = repositoryTableItemPOS;
    }

    /**
     * 向表中添加商品
     * @param repositoryTableItemPO
     */
    public void addRepositoryItem(RepositoryTableItemPO repositoryTableItemPO) {
        repositoryTableItemPOS.add(repositoryTableItemPO);
    }
}
