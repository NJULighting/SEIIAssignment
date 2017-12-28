package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.salesdoc.SalesDoc;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.bl.utils.DataServiceBiFunction;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.bl.utils.ListTransformer;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import shared.*;

import javax.naming.NamingException;
import java.rmi.RemoteException;
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
    public TwoTuple<ResultMessage, String> commitDoc(DocVO doc) {
        TwoTuple<ResultMessage, String> res = DataServiceFunction.commit(doc.toPO(), dataService::commitDoc);
        if (res.t == ResultMessage.SUCCESS)
            logger.add(OPType.ADD, "提交单据" + res.r);
        return res;
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
        DocFactory docFactory = new DocFactory();
        List<Doc> docList = DataServiceFunction
                .findAndFilterToList(userInfo.getIDOfSignedUser(), dataService::findByUserId,
                        docFactory::poToDoc, filter.getPredicateForDoc());

        // Filter them and transform them to HistoryDocVO list
        return ListTransformer.toList(docList, Doc::toHistoryDocVO);
    }

    List<BusinessConditionItemVO> findSaleRecords(DocumentFilter filter) {
        // Get doc list and filter it
        List<Doc> docList = DataServiceBiFunction
                .findAndFilterToList(DocType.SALES, DocState.APPROVAL,
                        dataService::findByTypeAndState, SalesDoc::new, filter.getPredicateForDoc());

        List<BusinessConditionItemVO> res = new ArrayList<>();
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
        DocFactory factory = new DocFactory();
        List<Doc> docList = DataServiceBiFunction.findAndFilterToList(filter.getStart(), filter.getEnd(),
                dataService::findByTime, factory::poToDoc, filter.getPredicateForDoc());

        return ListTransformer.toList(docList, Doc::toBusinessHistoryItemVO);
    }
}
