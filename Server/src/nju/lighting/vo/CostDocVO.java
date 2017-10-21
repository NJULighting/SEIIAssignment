package nju.lighting.vo;

import nju.lighting.po.AccountPO;
import nju.lighting.po.CostDocItemList;

import java.util.List;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class CostDocVO {
    private String costDocID;
    private List<String> accounts;
    private CostDocItemList itemList;
    private int total;

    public CostDocVO(String costDocID, List<String> accounts, CostDocItemList itemList, int total) {
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

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
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
