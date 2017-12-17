package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.accountiodoc.AccountDocFactory;
import nju.lighting.bl.documentbl.alertdoc.AlertDocFactory;
import nju.lighting.bl.documentbl.costdoc.CostDocFactory;
import nju.lighting.bl.documentbl.giftdoc.GiftDocFactory;
import nju.lighting.bl.documentbl.lossandgaindoc.LossAndGainDocFactory;
import nju.lighting.bl.documentbl.salesdoc.SalesDocFactory;
import nju.lighting.bl.documentbl.salesdoc.SalesReturnDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesReturnDocFactory;
import nju.lighting.bl.documentbl.stockdoc.StockDocFactory;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.*;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created on 2017/11/7.
 * Description: 处理单据提交、创建、查找业务
 * @author Liao
 */
public enum DocManager {
    INSTANCE;

    private Map<DocType, Supplier<DocFactory>> factoryMap;
    private DocDataService dataService;
    private Logger logger;

    DocManager() {
        factoryMap = new HashMap<>();
        factoryMap.put(DocType.ACCOUNT_OUT, AccountDocFactory::new);
        factoryMap.put(DocType.ACCOUNT_IN, AccountDocFactory::new);
        factoryMap.put(DocType.ALERT, AlertDocFactory::new);
        factoryMap.put(DocType.COST, CostDocFactory::new);
        factoryMap.put(DocType.GIFT, GiftDocFactory::new);
        factoryMap.put(DocType.LOSS_AND_GAIN, LossAndGainDocFactory::new);
        factoryMap.put(DocType.SALES, SalesDocFactory::new);
        factoryMap.put(DocType.SALES_RETURN, SalesReturnDocFactory::new);
        factoryMap.put(DocType.STOCK, StockDocFactory::new);
        factoryMap.put(DocType.STOCK_RETURN, StockDocFactory::new);

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
     * contains <tt>ResulMessage.FAILURE</tt> and a null id;
     * @param doc doc to be committed
     * @return <tt>[ID,SUCCESS]</tt> if commit successfully
     */
    public TwoTuple<String,ResultMessage> commitDoc(DocVO doc) {
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
}
