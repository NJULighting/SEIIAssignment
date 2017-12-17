package nju.lighting.bl.documentbl;

import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
public interface DocFactory {
    Doc createDocForApproval(HistoryDocVO historyDocVO);

    DocVO poToDocVO(DocPO po);
}
