package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

/**
 * Created on 2017/12/25.
 * Description:
 * @author Liao
 */
public class AccountInDoc extends AccountIODoc {

    public AccountInDoc(DocVO vo) {
        super(vo);
    }

    public AccountInDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
    }

    public AccountInDoc(DocPO po) {
        super(po);
        AccountIODocPO accountIODocPO = (AccountIODocPO) po;
        customerID = accountIODocPO.getCustomerID();
        total = accountIODocPO.getTotal();
        accountIODocPO.getTransferAccountList().forEach(itemList::add);
    }

    @Override
    public void approve() {

    }

    @Override
    public DocPO toPO() {
        return new AccountIODocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, customerID, itemList.toPO(id), total);
    }
}
