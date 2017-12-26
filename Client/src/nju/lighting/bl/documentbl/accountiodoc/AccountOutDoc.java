package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerInfoImpl;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountOutDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

/**
 * Created on 2017/12/25.
 * Description:
 * @author Liao
 */
public class AccountOutDoc extends AccountIODoc {

    public AccountOutDoc(DocVO vo) {
        super(vo);
        assignItemList(vo, AccountIOType.OUT);
    }

    public AccountOutDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assignItemList(historyDocVO.getDocVO(), AccountIOType.OUT);
    }

    public AccountOutDoc(DocPO po) {
        super(po);
        AccountOutDocPO accountOutDocPO = (AccountOutDocPO) po;
        customerID = accountOutDocPO.getCustomerID();
        total = accountOutDocPO.getTotal();

        // Assign item list
        itemList = new AccountDocItemList(AccountIOType.OUT);
        accountOutDocPO.getTransferAccountList().forEach(itemList::add);
    }

    @Override
    public ResultMessage approve() {
        // Change account
        ResultMessage res = itemList.approve();
        if (res != ResultMessage.SUCCESS)
            return res;

        // Change customer's receivable
        CustomerInfo customerInfo = new CustomerInfoImpl();
        return customerInfo.changeReceivable(Integer.parseInt(customerID), -total);
    }

    @Override
    public DocPO toPO() {
        return new AccountOutDocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, customerID, itemList.toPO(id), total);
    }
}
