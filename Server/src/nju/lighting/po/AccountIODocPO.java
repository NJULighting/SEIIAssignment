package nju.lighting.po;

import java.util.ArrayList;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class AccountIODocPO extends DocPO{
    private String ioDocID;
    private AccountIODocType type;
    private String customerID;
    private ArrayList<AccountTransferItemPO> transferAccountList;
    private int total;

    public AccountIODocPO(String ioDocID, AccountIODocType type, String customerID, ArrayList<AccountTransferItemPO> transferAccountList, int total) {
        this.ioDocID = ioDocID;
        this.type = type;
        this.customerID = customerID;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    public String getIoDocID() {
        return ioDocID;
    }

    public void setIoDocID(String ioDocID) {
        this.ioDocID = ioDocID;
    }

    public AccountIODocType getAccountIODocType() {
        return type;
    }

    public void setAccountIODocType(AccountIODocType type) {
        this.type = type;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public ArrayList<AccountTransferItemPO> getTransferAccountList() {
        return transferAccountList;
    }

    public void setTransferAccountList(ArrayList<AccountTransferItemPO> transferAccountList) {
        this.transferAccountList = transferAccountList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
