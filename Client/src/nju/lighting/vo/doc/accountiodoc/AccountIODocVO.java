package nju.lighting.vo.doc.accountiodoc;

import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.DocVO;
import shared.AccountIODocType;
import shared.DocType;

import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountIODocVO extends DocVO {
    private AccountIODocType type;
    private String customer;
    private AccountTransferItemPO transferAccountList;
    private int total;

    public AccountIODocVO(Date time, String creatorId, String docId, AccountIODocType type
            , String customer, AccountTransferItemPO transferAccountList, int total) {
        super(time, creatorId, docId, DocType.ACCOUNT_INOUT);
        this.type = type;
        this.customer = customer;
        this.transferAccountList = transferAccountList;
        this.total = total;
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
