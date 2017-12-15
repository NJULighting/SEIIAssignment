package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import shared.AccountIODocType;
import shared.DocType;
import shared.ResultMessage;

import java.util.Date;

/**
 * Created on 2017/11/7.
 * Description: 负责创建初始的收付款单
 * @author Liao
 */
public class AccountIODoc extends Doc{

    private AccountIODocType ioType;
    private String customerID;
    private double total;
    private AccountDocItemList itemList;
    private InOutStrategy strategy;

    public AccountIODoc(DocPO po) {
        super(po);
        strategy = docType == DocType.ACCOUNT_IN ? new InStrategy() : new OutStrategy();
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
        return null;
    }

    @Override
    public DocPO toPO() {
        return new AccountIODocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, ioType, customerID, itemList.toPO(id), total);
    }

    @Override
    protected void assignWithPO(DocPO po) {
        AccountIODocPO accountIODocPO = (AccountIODocPO) po;
        ioType = accountIODocPO.getIOType();
        customerID = accountIODocPO.getCustomerID();
        total = accountIODocPO.getTotal();
        itemList = new AccountDocItemList(accountIODocPO.getTransferAccountList());
    }
}
