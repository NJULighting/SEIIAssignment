package nju.lighting.vo.doc.accountiodoc;

import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.account.AccountVO;
import shared.AccountIODocType;
import shared.DocType;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountIODocVO extends DocVO {
    private String customer;
    private List<AccountTransferItemVO> transferAccountList;
    private double total;
    private List<AccountVO> accountList;

    public AccountIODocVO(Date time, String creatorId, String docId, DocType type, AccountIODocType type1, String customer,
                          List<AccountTransferItemVO> transferAccountList, double total) {
        super(time, creatorId, docId, type);
        this.customer = customer;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    /**
     * This constructor should be used when the user want to create a new doc
     * @param time        time of creation
     * @param type        doc's type
     * @param accountList list of account
     */
    public AccountIODocVO(Date time, DocType type, List<AccountVO> accountList) {
        super(time, type);
        this.accountList = accountList;
    }

    //无需输入总价的构造函数
    public AccountIODocVO(Date time, String creatorId, String docId, DocType type, String customer,
                          List<AccountTransferItemVO> transferAccountList) {
        super(time, creatorId, docId, type);
        this.customer = customer;
        this.transferAccountList = transferAccountList;
        // Calculate value for total
        total = transferAccountList.stream()
                .mapToDouble(AccountTransferItemVO::getAmount)
                .sum();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<AccountTransferItemVO> getTransferAccountList() {
        return transferAccountList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<AccountVO> getAccountList() {
        return accountList;
    }

    @Override
    public DocPO toPO() {
        // Transform items
        AccountIODocType ioDocType = getType() == DocType.ACCOUNT_IN ? AccountIODocType.IN : AccountIODocType.OUT;
        List<AccountTransferItemPO> itemPOList = VPOTransformer.toVPOList(transferAccountList, AccountTransferItemVO::toPO);

        return new AccountIODocPO(getType(), getCreatorId(), getTime(), ioDocType, customer, itemPOList, total);
    }
}
