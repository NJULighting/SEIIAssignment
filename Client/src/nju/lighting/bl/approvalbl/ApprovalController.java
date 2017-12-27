package nju.lighting.bl.approvalbl;

import nju.lighting.bl.documentbl.DocInfo;
import nju.lighting.bl.documentbl.DocInfoImpl;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/11/19.
 * Description
 * @author 陈俊宇
 */
public class ApprovalController implements ApprovalBLService {
    private DocInfo docInfo = new DocInfoImpl();

    @Override
    public List<DocVO> getDocumentList() {
        return docInfo.findUnCheckedDoc();
    }

    @Override
    public ResultMessage approve(HistoryDocVO vo) {
        return docInfo.approve(vo);
    }

    @Override
    public ResultMessage approveAll(List<HistoryDocVO> voList) {
        return docInfo.approveAll(voList);
    }

    @Override
    public ResultMessage reject(HistoryDocVO vo) {
        return docInfo.reject(vo);
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) {
        return docInfo.save(vo);
    }

}
