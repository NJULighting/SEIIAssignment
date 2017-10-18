package nju.lighting.po;

import nju.lighting.utility.TwoTuple;

import java.util.Map;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class AccountIODocPO extends DocPO{
    private String ioDocID;
    private AccountIODocType type;
    private CustomerPO customer;
    private Map<AccountPO, TwoTuple<Integer, String>> transferAccountList;
    private int total;

    public AccountIODocPO(String docID, AccountIODocType type, CustomerPO customer
            , Map<AccountPO, TwoTuple<Integer, String>> transferAccountList, int total) {
        this.ioDocID = docID;
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

    public AccountIODocType getIOType() {
        return type;
    }

    public void setIOType(AccountIODocType type) {
        this.type = type;
    }

    public CustomerPO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerPO customer) {
        this.customer = customer;
    }

    public Map<AccountPO, TwoTuple<Integer, String>> getTransferAccountList() {
        return transferAccountList;
    }

    public void setTransferAccountList(Map<AccountPO, TwoTuple<Integer, String>> transferAccountList) {
        this.transferAccountList = transferAccountList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
