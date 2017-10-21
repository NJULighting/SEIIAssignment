package nju.lighting.po;

import nju.lighting.utility.TwoTuple;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class CostDocPO extends DocPO{
    private String costDocID;
    private List<AccountPO> accounts;
    private CostDocItemList itemList;
    private int total;

    public CostDocPO(String costDocID, List<AccountPO> accounts, CostDocItemList itemList, int total) {
        this.costDocID = costDocID;
        this.accounts = accounts;
        this.itemList = itemList;
        this.total = total;
    }

    public String getCostDocID() {
        return costDocID;
    }

    public void setCostDocID(String costDocID) {
        this.costDocID = costDocID;
    }

    public List<AccountPO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountPO> accounts) {
        this.accounts = accounts;
    }

    public CostDocItemList getItemList() {
        return itemList;
    }

    public void setItemList(CostDocItemList itemList) {
        this.itemList = itemList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
