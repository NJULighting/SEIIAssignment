package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.po.doc.accountiodoc.AccountOutDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocType;
import shared.ResultMessage;

/**
 * Created on 2017/11/7.
 * Description: 负责创建初始的收付款单
 * @author Liao
 */
public class AccountIODoc extends Doc {

    // TODO: 2017/12/18 Modify about the io type
    private String customerID;
    private double total;
    private AccountDocItemList itemList;
    private InOutStrategy strategy;

    /**
     * Constructor for approval module
     */
    public AccountIODoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);

        if (docType == DocType.ACCOUNT_IN) {
            strategy = new InStrategy();
        } else {
            strategy = new OutStrategy();
        }

        // Assign specific fields
        AccountIODocVO docVO = (AccountIODocVO) historyDocVO.getDocVO();
        customerID = docVO.getCustomer();
        total = docVO.getTotal();
        itemList = new AccountDocItemList();
        itemList.addAll(docVO.getTransferAccountList());
    }

    /**
     * Used for searching
     */
    public AccountIODoc(DocPO po) {
        super(po);
        strategy = docType == DocType.ACCOUNT_IN ? new InStrategy() : new OutStrategy();

        if (docType == DocType.ACCOUNT_IN) {
            // Assign specific attributes
            AccountIODocPO accountIODocPO = (AccountIODocPO) po;
            customerID = accountIODocPO.getCustomerID();
            total = accountIODocPO.getTotal();
            itemList = new AccountDocItemList(accountIODocPO.getTransferAccountList());
        } else {
            AccountOutDocPO accountOutDocPO = (AccountOutDocPO) po;
            customerID = accountOutDocPO.getCustomerID();
            total = accountOutDocPO.getTotal();
            itemList = new AccountDocItemList(accountOutDocPO.getTransferAccountList());
        }
    }

    @Override
    public void approve() {
        strategy.approve(this);
    }

    @Override
    public ResultMessage reject() {
        return strategy.reject(this);
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
            return new AccountIODocVO(createTime, userId, id, docType, customerID, itemList.toVO());
    }

    @Override
    public DocPO toPO() {
        if (docType == DocType.ACCOUNT_IN)
            return new AccountIODocPO(id, docType, userId, createTime, checkTime,
                    approvalComment, state, approvalId, null, customerID, itemList.toPO(id), total);
        else return new AccountOutDocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, null, customerID, itemList.toPO(id), total);
    }

    @Override
    public boolean containsCustomer(String customerId) {
        return this.customerID.equals(customerId);
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return false;
    }

    @Override
    public boolean containsRepository(String repository) {
        return false;
    }

}
