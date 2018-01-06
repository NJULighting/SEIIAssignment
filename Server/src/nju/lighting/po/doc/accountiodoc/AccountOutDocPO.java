package nju.lighting.po.doc.accountiodoc;

import nju.lighting.po.doc.DocPO;
import shared.DocType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
@Entity
@Table(name = "ACCOUNT_OUT_DOC")
public class AccountOutDocPO extends DocPO implements Serializable {

    private static final long serialVersionUID = -56354721179246487L;

    private String customerID;

    private List<AccountTransferItemPO> transferAccountList;

    private double total;

    public AccountOutDocPO() {

    }

    public AccountOutDocPO(String id, DocType docType, String userId, Date time
            , String customerID, List<AccountTransferItemPO> transferAccountList, double total) {
        super(id, docType, userId, time);
        this.customerID = customerID;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    @Column(name = "CUSTOMER_ID", nullable = false, length = 30)
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Transient
    public List<AccountTransferItemPO> getTransferAccountList() {
        return transferAccountList;
    }

    public void setTransferAccountList(List<AccountTransferItemPO> transferAccountList) {
        this.transferAccountList = transferAccountList;
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
        list.addAll(transferAccountList);
        return list;
    }

    @Override
    public void setItems(List<Object> list) {
        transferAccountList = new ArrayList<>();
        for (Object o: list) {
            transferAccountList.add((AccountTransferItemPO)o);
        }
    }
}
