package nju.lighting.po.doc.accountiodoc;

import nju.lighting.po.doc.DocPO;
import shared.AccountIODocType;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class AccountIODocPO extends DocPO {
    private AccountIODocType ioType;
    private String customerID;
    private ArrayList<AccountTransferItemPO> transferAccountList;
    private double total;

    public AccountIODocPO(String id, DocType docType, String userId, Date time
            , AccountIODocType ioType, String customerID, ArrayList<AccountTransferItemPO> transferAccountList, double total) {
        super(id, docType, userId, time);
        this.ioType = ioType;
        this.customerID = customerID;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    public AccountIODocType getIOType() {
        return ioType;
    }

    public void setIOType(AccountIODocType ioType) {
        this.ioType = ioType;
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
