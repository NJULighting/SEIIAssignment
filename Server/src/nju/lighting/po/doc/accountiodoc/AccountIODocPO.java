package nju.lighting.po.doc.accountiodoc;

import nju.lighting.po.doc.DocPO;
import shared.AccountIODocType;
import shared.DocType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
@Entity
@Table(name = "ACCOUNT_IO_DOC")
public class AccountIODocPO extends DocPO {

    private AccountIODocType ioType;

    private String customerID;

    private List<AccountTransferItemPO> transferAccountList;

    private double total;

    public AccountIODocPO() {

    }

    public AccountIODocPO(String id, DocType docType, String userId, Date time
            , AccountIODocType ioType, String customerID, List<AccountTransferItemPO> transferAccountList, double total) {
        super(id, docType, userId, time);
        this.ioType = ioType;
        this.customerID = customerID;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "IO_TYPE", nullable = false, length = 20)
    public AccountIODocType getIOType() {
        return ioType;
    }

    public void setIOType(AccountIODocType ioType) {
        this.ioType = ioType;
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
