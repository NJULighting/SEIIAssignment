package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.DocFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

import java.util.List;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
public class AccountDocFactory implements DocFactory {
    @Override
    public Doc createDocForApproval(HistoryDocVO historyDocVO) {
        return null;
    }

    @Override
    public DocVO poToDocVO(DocPO po) {
        AccountIODocPO accountIODocPO = (AccountIODocPO) po;
        List<AccountTransferItemVO> itemVOList =
                VPOTransformer.toVPOList(accountIODocPO.getTransferAccountList(),
                        itemPO -> new AccountTransferItemVO(itemPO.getAmount(), itemPO.getComments(), itemPO.getAccountID(), itemPO.getId()));
        return new AccountIODocVO(po.getCreateTime(), po.getUserId(), po.getId(), po.getDocType(),
                accountIODocPO.getCustomerID(), itemVOList);
    }
}
