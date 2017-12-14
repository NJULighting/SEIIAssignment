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

    public AccountIODocVO(Date time, String creatorId, String docId, DocType type, AccountIODocType type1,
                          String customer, AccountTransferItemPO transferAccountList, int total) {
        super(time, creatorId, docId, type);
        this.type = type1;
        this.customer = customer;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    public AccountIODocVO(Date time, String creatorId, DocType docType) {
        super(time, creatorId, docType);
        type = docType == DocType.ACCOUNT_IN ? AccountIODocType.IN : AccountIODocType.OUT;
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
