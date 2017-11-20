package nju.lightingServer.po.doc.costdoc;

import nju.lightingServer.po.doc.DocPO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class CostDocPO extends DocPO {
    private String accountID;
    private ArrayList<CostDocItemPO> itemList;
    private double total;

    public CostDocPO(String id, String userId, Date time
            , String accountID, ArrayList<CostDocItemPO> itemList, double total) {
        super(id, DocType.COST, userId, time);
        this.accountID = accountID;
        this.itemList = itemList;
        this.total = total;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public ArrayList<CostDocItemPO> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<CostDocItemPO> itemList) {
        this.itemList = itemList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
