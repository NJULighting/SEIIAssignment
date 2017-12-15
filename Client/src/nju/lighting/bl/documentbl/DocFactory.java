package nju.lighting.bl.documentbl;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
public interface DocFactory {
    DocVO getVOForCreation();

    Doc createDocForApproval(HistoryDocVO historyDocVO);
}
