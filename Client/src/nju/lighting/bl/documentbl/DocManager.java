package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.alertdoc.AlertDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesDoc;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.bl.utils.DataServiceBiFunction;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import shared.*;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/11/7.
 * Description: 处理单据提交、创建、查找业务
 * @author Liao
 */
public enum DocManager {
    INSTANCE;

    private DocDataService dataService;
    private Logger logger;

    DocManager() {
        try {
            dataService = DataFactory.getDataBase(DocDataService.class);
            logger = new UserLogger();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Commit a document. If commit successfully, it'll return a tuple contains
     * id and <tt>ResultMessage.SUCCESS</tt>. Otherwise, it'll return a tuple
     * contains <tt>ResultMessage.FAILURE</tt> and a null id;
     * @param doc doc to be committed
     * @return <tt>[ID,SUCCESS]</tt> if commit successfully
     */
    public Result<DocVO> commitDoc(DocVO doc) {
        Result<String> res = DataServiceFunction.addToDataBase(doc.toPO(), dataService::commitDoc);
        if (res.hasValue()) {
            logger.add(OPType.ADD, "提交单据" + res.getValue());
            doc.setDocId(res.getValue());
            return new Result<>(res.getResultMessage(), doc);
        }
        return new Result<>(res.getResultMessage(), null);
    }

    /**
     * Find documents with the constraints the filter defined. The user can only
     * see his own documents in this method. This method should be used when user
     * want to see the commit history, and shouldn't be used in approving.
     * @param filter constraints for finding documents
     * @return documents that satisfy these constraints
     */
    public List<HistoryDocVO> findDocuments(DocumentFilter filter) {
        UserInfo userInfo = new UserInfoImpl();
        DocFactory docFactory = DocFactory.INSTANT;
        List<Doc> docList = DataServiceFunction
                .findAndFilterToList(userInfo.getIDOfSignedUser(), dataService::findByUserId,
                        docFactory::poToDoc, filter.getPredicateForDoc());

        // Filter and transform them to list of HistoryDocVO
        return CollectionTransformer.toList(docList, Doc::toHistoryDocVO);
    }

    List<SalesDetailItemVO> findSaleRecords(DocumentFilter filter) {
        // Get doc list and filter it
        List<Doc> docList = DataServiceBiFunction
                .findAndFilterToList(DocType.SALES, DocState.APPROVAL,
                        dataService::findByTypeAndState, SalesDoc::new, filter.getPredicateForDoc());

        List<SalesDetailItemVO> res = new ArrayList<>();
        docList.forEach(doc -> res.addAll(((SalesDoc) doc).getBusinessCondition()));

        if (filter.getCommodity() == null) {
            return res;
        }

        // Filter items with the commodity name given
        return res.stream()
                .filter(item -> item.getName().equals(filter.getCommodity()))
                .collect(Collectors.toList());
    }

    List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) {
        DocFactory factory = DocFactory.INSTANT;
        List<Doc> docList = DataServiceBiFunction.findAndFilterToList(filter.getStart(), filter.getEnd(),
                dataService::findByTime, factory::poToDoc, filter.getPredicateForDoc());

        return CollectionTransformer.toList(docList, Doc::toBusinessHistoryItemVO);
    }

    public ResultMessage expireAlertDoc(AlertDocVO docVO) {
        AlertDoc alertDoc = new AlertDoc(docVO);
        return alertDoc.expireAlert();
    }
}
