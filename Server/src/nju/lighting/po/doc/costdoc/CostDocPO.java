package nju.lighting.po.doc.costdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
@Entity
@Table(name = "COST_DOC")
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

    public CostDocPO() {

    }

    @Column(name = "ACCOUNT_ID", nullable = false, length = 20)
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    @Transient
    public ArrayList<CostDocItemPO> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<CostDocItemPO> itemList) {
        this.itemList = itemList;
    }

    @Column(name = "TOTAL", nullable = false)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    @Transient
    public List<Object> getItems() {
        List<Object> list = new ArrayList<>();
        list.addAll(itemList);
        return list;
    }

    @Override
    public void setItems(List<Object> list) {
        this.itemList = new ArrayList<>();
        for (Object o: list) {
            itemList.add((CostDocItemPO)o);
        }
    }
}
