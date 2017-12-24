package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.po.doc.DocPO;
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
    }
}
