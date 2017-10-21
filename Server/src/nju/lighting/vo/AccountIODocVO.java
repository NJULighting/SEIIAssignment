package nju.lighting.vo;

import nju.lighting.po.AccountIODocType;
import nju.lighting.po.AccountPO;
import nju.lighting.po.AccountTransferList;
import nju.lighting.utility.TwoTuple;

import java.util.Map;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountIODocVO extends DocVO{
    private String ioDocID;
    private AccountIODocType type;
    private String customer;
    private AccountTransferList transferAccountList;
    private int total;

    public AccountIODocVO(String ioDocID, AccountIODocType type, String customer, AccountTransferList transferAccountList, int total) {
        this.ioDocID = ioDocID;
        this.type = type;
        this.customer = customer;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    public String getIoDocID() {
        return ioDocID;
    }

    public void setIoDocID(String ioDocID) {
        this.ioDocID = ioDocID;
    }

    public AccountIODocType getAccountIODdocType() {
        return type;
    }

    public void setAccountIODocType(AccountIODocType type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public AccountTransferList getTransferAccountList() {
        return transferAccountList;
    }

    public void setTransferAccountList(AccountTransferList transferAccountList) {
        this.transferAccountList = transferAccountList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
