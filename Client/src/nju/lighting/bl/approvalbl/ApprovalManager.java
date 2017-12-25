package nju.lighting.bl.approvalbl;

import nju.lighting.bl.documentbl.DocInfo;
import nju.lighting.bl.documentbl.DocInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.util.List;

/**
 * Created on 2017/11/13.
 * Description 审批单据的领域模型对象
 * @author 陈俊宇
 */
public enum ApprovalManager {
    INSTANCE;

    private DocInfo docInfo;

    ApprovalManager() {
        docInfo = new DocInfoImpl();
    }

    List<DocVO> getDocumentList() {
        return docInfo.findUnCheckedDoc();
    }

    ResultMessage reject(HistoryDocVO vo) {
        return docInfo.reject(vo);
    }
}
