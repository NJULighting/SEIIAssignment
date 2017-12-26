package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerInfoImpl;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

/**
 * Created on 2017/12/25.
 * Description:
 * @author Liao
 */
public class AccountInDoc extends AccountIODoc {

    public AccountInDoc(DocVO vo) {
        super(vo);
        assignItemList(vo, AccountIOType.IN);
    }

    public AccountInDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assignItemList(historyDocVO.getDocVO(), AccountIOType.IN);
    }

    public AccountInDoc(DocPO po) {
        super(po);
        AccountIODocPO accountIODocPO = (AccountIODocPO) po;
        customerID = accountIODocPO.getCustomerID();
        total = accountIODocPO.getTotal();

        itemList = new AccountDocItemList(AccountIOType.IN);
        accountIODocPO.getTransferAccountList().forEach(itemList::add);
    }


    @Override
    public ResultMessage approve() {
        // Change account
        ResultMessage res = itemList.approve();
        if (res != ResultMessage.SUCCESS)
            return res;

        // Change customer's payable
        CustomerInfo customerInfo = new CustomerInfoImpl();
        return customerInfo.changePayable(Integer.parseInt(customerID), -total);
    }

    @Override
    public DocPO toPO() {
        return new AccountIODocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, customerID, itemList.toPO(id), total);
    }
}
