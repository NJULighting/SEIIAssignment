package nju.lighting.vo.doc.accountiodoc;

import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.DocVO;
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

    /**
     * Constructor for pre
     */
    public AccountIODocVO(Date time, DocType type, String customerId, String creatorID, List<AccountTransferItemVO> transferAccountList) {
        super(time, type, creatorID);
        this.customer = customerId;
        this.transferAccountList = transferAccountList;
        total = calculateTotal();
    }

    /**
     * Constructor for bl
     */
    public AccountIODocVO(Date time, String creatorId, String docId, DocType type, String customer,
                          List<AccountTransferItemVO> transferAccountList) {
        super(time, creatorId, docId, type);
        this.customer = customer;
        this.transferAccountList = transferAccountList;
        // Calculate value for total
        total = calculateTotal();
    }

    private double calculateTotal() {
        return transferAccountList.stream()
                .mapToDouble(AccountTransferItemVO::getAmount)
                .sum();
    }

    public String getCustomer() {
        return customer;
    }

    public List<AccountTransferItemVO> getTransferAccountList() {
        return transferAccountList;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public DocPO toPO() {
        // Transform items
        AccountIODocType ioDocType = getType() == DocType.ACCOUNT_IN ? AccountIODocType.IN : AccountIODocType.OUT;
        List<AccountTransferItemPO> itemPOList = VPOTransformer.toVPOList(transferAccountList, AccountTransferItemVO::toPO);

        return new AccountIODocPO(getType(), getCreatorId(), getTime(), ioDocType, customer, itemPOList, total);
    }
}
