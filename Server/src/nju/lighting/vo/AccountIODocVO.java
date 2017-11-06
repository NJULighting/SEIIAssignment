package nju.lighting.vo;

import shared.AccountIODocType;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountIODocVO extends DocVO{
    private String ioDocID;
    private AccountIODocType type;
    private String customer;
    private AccountTransferItemPO transferAccountList;
    private int total;

    public AccountIODocVO(String ioDocID, AccountIODocType type, String customer, AccountTransferItemPO transferAccountList, int total) {
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

    public AccountTransferItemPO getTransferAccountList() {
        return transferAccountList;
    }

    public void setTransferAccountList(AccountTransferItemPO transferAccountList) {
        this.transferAccountList = transferAccountList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
