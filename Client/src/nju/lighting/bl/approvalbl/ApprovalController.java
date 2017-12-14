package nju.lighting.bl.approvalbl;

import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/19.
 * Description
 *
 * @author 陈俊宇
 */
public class ApprovalController implements ApprovalBLService {
    private ApprovalManager manager = ApprovalManager.INSTANCE;

    @Override
    public List<DocVO> getDocumentList() {
        return manager.getDocumentList();
    }

    @Override
    public ResultMessage approve(HistoryDocVO vo) {
        return null;
    }

    @Override
    public ResultMessage approveAll(ArrayList<HistoryDocVO> voList) {
        return null;
    }

    @Override
    public ResultMessage reject(HistoryDocVO vo) {
        return null;
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) {
        return null;
    }

    @Override
    public ResultMessage saveAndApprove(HistoryDocVO vo) {
        return null;
    }
}
