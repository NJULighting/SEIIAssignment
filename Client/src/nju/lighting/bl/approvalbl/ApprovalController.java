package nju.lighting.bl.approvalbl;

import nju.lighting.bl.documentbl.DocInfo;
import nju.lighting.bl.documentbl.DocInfoImpl;
import nju.lighting.bl.logbl.Log;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.OPType;
import shared.ResultMessage;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created on 2017/11/19.
 * Description
 * @author 陈俊宇
 */
public class ApprovalController implements ApprovalBLService {
    private DocInfo docInfo = new DocInfoImpl();
    private Logger logger = new UserLogger();

    @Override
    public List<DocVO> getDocumentList() {
        return docInfo.findUnCheckedDoc();
    }

    @Override
    public ResultMessage approve(HistoryDocVO vo) {
        return workWithDocInfo(docInfo::approve, vo,
                l -> l.add(OPType.APPROVE, "通过单据" + vo.getDocVO().getDocId()));
    }

    @Override
    public ResultMessage approveAll(List<HistoryDocVO> voList) {
        // Create message of approval
        StringBuilder message = new StringBuilder();
        voList.forEach(vo -> message.append(vo.getDocVO().getDocId()).append(","));
        message.deleteCharAt(message.length() - 1);

        return workWithDocInfo(docInfo::approveAll, voList,
                l -> l.add(OPType.APPROVE, "通过单据：" + message.toString()));
    }

    @Override
    public ResultMessage reject(HistoryDocVO vo) {
        return workWithDocInfo(docInfo::reject, vo,
                l -> l.add(OPType.REJECT, "拒绝单据：" + vo.getDocVO().getDocId()));
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) {
        return workWithDocInfo(docInfo::save, vo,
                l -> l.add(OPType.MODIFY, "修改并保存单据：" + vo.getDocVO().getDocId()));
    }

    private <T> ResultMessage workWithDocInfo(Function<T, ResultMessage> function, T target, Consumer<Logger> message) {
        ResultMessage res = function.apply(target);
        if (res == ResultMessage.SUCCESS)
            message.accept(logger);
        return res;
    }
}
