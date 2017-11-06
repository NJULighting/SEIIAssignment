package nju.lighting.po;

import java.util.ArrayList;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class AccountIODocPO extends DocPO {
    private AccountIODocType type;
    private String customerID;
    private ArrayList<AccountTransferItemPO> transferAccountList;
    private double total;

    public AccountIODocPO(AccountIODocType type, String customerID, ArrayList<AccountTransferItemPO> transferAccountList, int total) {
        this.type = type;
        this.customerID = customerID;
        this.transferAccountList = transferAccountList;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
