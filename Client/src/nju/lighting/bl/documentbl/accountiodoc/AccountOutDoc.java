package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountOutDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

/**
 * Created on 2017/12/25.
 * Description:
 * @author Liao
 */
public class AccountOutDoc extends AccountIODoc {
    public AccountOutDoc(DocVO vo) {
        super(vo);
    }

    public AccountOutDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
    }

    public AccountOutDoc(DocPO po) {
        super(po);
        AccountOutDocPO accountOutDocPO = (AccountOutDocPO) po;
        customerID = accountOutDocPO.getCustomerID();
        total = accountOutDocPO.getTotal();
        accountOutDocPO.getTransferAccountList().forEach(itemList::add);
    }

    @Override
    public void approve() {

    }

    @Override
    public DocPO toPO() {
        return new AccountOutDocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, customerID, itemList.toPO(id), total);
    }
}
