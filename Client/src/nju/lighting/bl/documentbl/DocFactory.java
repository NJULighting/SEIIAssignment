package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

import java.util.List;
import java.util.function.Function;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
public interface DocFactory {
    Doc createDocForApproval(HistoryDocVO historyDocVO);

    DocVO poToDocVO(DocPO po);

    default HistoryDocVO poToHistoryDocVO(DocPO po, UserInfo userInfo) {
        DocVO docVO = poToDocVO(po);
        return new HistoryDocVO(userInfo.getUserVOByID(po.getUserId()), docVO, po.getApprovalComment(),
                po.getState(), po.getCheckTime(), userInfo.getUserVOByID(po.getApprovalId()));
    }

}
