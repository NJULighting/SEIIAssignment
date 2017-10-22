package nju.lighting.po;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class CostDocPO extends DocPO{
    private String costDocID;
    private List<AccountPO> accounts;
    private ArrayList<CostDocItem> itemList;
    private int total;

    public CostDocPO(String costDocID, List<AccountPO> accounts, ArrayList<CostDocItem> itemList, int total) {
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

    public ArrayList<CostDocItem> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<CostDocItem> itemList) {
        this.itemList = itemList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
