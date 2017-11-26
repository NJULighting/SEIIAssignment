package nju.lighting.po.repository;

import java.util.ArrayList;

public class RepositoryTablePO {

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
