package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.alertdoc.AlertDoc;
import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.DocType;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/12/13.
 * Description:
 * @author Liao
 */
public class DocInfoImpl implements DocInfo {

    private static final String APPROVAL_MAIL_HEADER = "审批结果";
    private static final String APPROVAL_MAIL_CONTENT = "您提交的单据";
    private static final String APPROVAL_PASS = "通过了";
    private static final String APPROVAL_REJECT = "被拒绝了";

    private DocDataService dataService;
    private DocFactory docFactory;

    public DocInfoImpl() {
        try {
            docFactory = DocFactory.INSTANT;
            dataService = DataFactory.getDataBase(DocDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DocVO> findUnCheckedDoc() {
        return DataServiceFunction.findAndFilterToList(DocState.UN_CHECKED,
                dataService::findByState, po -> docFactory.poToDoc(po).toVO(), vo -> vo.getType() != DocType.ALERT);
    }

    @Override
    public ResultMessage approve(HistoryDocVO vo) {
        Doc doc = docFactory.historyDocVOToDoc(vo);

        // Approve
        ResultMessage res = doc.approve();
        if (res == ResultMessage.FAILURE)
            return res;

        // Update
        return sentMailAndUpdate(doc, APPROVAL_PASS, vo.getComment());
    }

    @Override
    public ResultMessage approveAll(List<HistoryDocVO> voList) {
        List<Doc> docList = CollectionTransformer.toList(voList, docFactory::historyDocVOToDoc);
        if (docList.stream().anyMatch(doc -> doc.approve() != ResultMessage.SUCCESS))
            return ResultMessage.FAILURE;

        return docList.stream()
                .anyMatch(doc -> sentMailAndUpdate(doc, APPROVAL_PASS, doc.approvalComment) != ResultMessage.SUCCESS) ?
                ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage reject(HistoryDocVO vo) {
        Doc doc = docFactory.historyDocVOToDoc(vo);
        doc.reject();
        return sentMailAndUpdate(doc, APPROVAL_REJECT, vo.getComment());
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) {
        Doc doc = docFactory.historyDocVOToDoc(vo);
        return updateDoc(doc.toPO());
    }

    @Override
    public void triggerAlertDoc(String commodityId, int count) {
        List<Doc> alertDocs = DataServiceFunction.findByToList(DocType.ALERT,
                dataService::findByType, docFactory::poToDoc);
        for (Doc doc : alertDocs) {
            ((AlertDoc) doc).triggerAlert(commodityId, count);
        }
    }

    private ResultMessage sentMailAndUpdate(Doc target, String result, String comment) {
        try {
            dataService.sentMail(target.getUserId(), APPROVAL_MAIL_HEADER,
                    APPROVAL_MAIL_CONTENT + target.getId() + result + "\32审批意见：" + comment);
            return dataService.updateDoc(target.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    private ResultMessage updateDoc(DocPO po) {
        try {
            return dataService.updateDoc(po);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

}