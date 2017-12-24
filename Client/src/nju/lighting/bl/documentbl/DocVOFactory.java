package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
public interface DocVOFactory {

    DocVO poToDocVO(DocPO po);

    default HistoryDocVO poToHistoryDocVO(DocPO po, UserInfo userInfo) {
        DocVO docVO = poToDocVO(po);
        return new HistoryDocVO(userInfo.getUserVOByID(po.getUserId()), docVO, po.getApprovalComment(),
                po.getState(), po.getCheckTime(), userInfo.getUserVOByID(po.getApprovalId()));
    }
}
