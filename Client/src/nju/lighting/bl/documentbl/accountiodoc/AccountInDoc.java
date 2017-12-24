package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.po.doc.DocPO;
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
    }
}
