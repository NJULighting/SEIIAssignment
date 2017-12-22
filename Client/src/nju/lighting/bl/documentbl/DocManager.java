package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.accountiodoc.AccountDocVOFactory;
import nju.lighting.bl.documentbl.alertdoc.AlertDocVOFactory;
import nju.lighting.bl.documentbl.costdoc.CostDocVOFactory;
import nju.lighting.bl.documentbl.giftdoc.GiftDocVOFactory;
import nju.lighting.bl.documentbl.lossandgaindoc.LossAndGainDocVOFactory;
import nju.lighting.bl.documentbl.salesdoc.SalesDocVOFactory;
import nju.lighting.bl.documentbl.salesdoc.SalesReturnDocVOFactory;
import nju.lighting.bl.documentbl.stockdoc.StockDocVOFactory;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import shared.*;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created on 2017/11/7.
 * Description: 处理单据提交、创建、查找业务
 * @author Liao
 */
public enum DocManager {
    INSTANCE;

    private Map<DocType, Supplier<DocVOFactory>> factoryMap;
    private DocDataService dataService;
    private Logger logger;

    DocManager() {
        factoryMap = new HashMap<>();
        factoryMap.put(DocType.ACCOUNT_OUT, AccountDocVOFactory::new);
        factoryMap.put(DocType.ACCOUNT_IN, AccountDocVOFactory::new);
        factoryMap.put(DocType.ALERT, AlertDocVOFactory::new);
        factoryMap.put(DocType.COST, CostDocVOFactory::new);
        factoryMap.put(DocType.GIFT, GiftDocVOFactory::new);
        factoryMap.put(DocType.LOSS_AND_GAIN, LossAndGainDocVOFactory::new);
        factoryMap.put(DocType.SALES, SalesDocVOFactory::new);
        factoryMap.put(DocType.SALES_RETURN, SalesReturnDocVOFactory::new);
        factoryMap.put(DocType.STOCK, StockDocVOFactory::new);
        factoryMap.put(DocType.STOCK_RETURN, StockDocVOFactory::new);

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
    public TwoTuple<String, ResultMessage> commitDoc(DocVO doc) {
        TwoTuple<String, ResultMessage> res = new TwoTuple<>();
        res.r = ResultMessage.FAILURE;

        // Commit to the database
        try {
            TwoTuple<ResultMessage, String> commitRes = dataService.commitDoc(doc.toPO());
            if (commitRes.t == ResultMessage.SUCCESS) {
                logger.add(OPType.ADD, "提交单据" + commitRes.r);
                res.t = commitRes.r;
                res.r = commitRes.t;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
        try {
            UserInfo userInfo = new UserInfoImpl();
            List<DocPO> poList = dataService.findByUserId(userInfo.getIDOfSignedUser());

            // Filter them and transform them to HistoryDocVO list
            return poList.stream()
                    .filter(filter.getPredicate())
                    .map(po -> factoryMap.get(po.getDocType()).get().poToHistoryDocVO(po, userInfo))
                    .collect(Collectors.toList());
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    List<BusinessConditionItemVO> findSaleRecords(BusinessConditionFilter filter) {
        try {
            Set<DocPO> docPOS = new HashSet<>();

            List<DocPO> salesPOList = dataService.findByType(DocType.SALES);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
