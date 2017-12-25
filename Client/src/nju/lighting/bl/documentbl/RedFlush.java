package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.accountiodoc.AccountInDoc;
import nju.lighting.bl.documentbl.accountiodoc.AccountOutDoc;
import nju.lighting.bl.documentbl.alertdoc.AlertDoc;
import nju.lighting.bl.documentbl.costdoc.CostDoc;
import nju.lighting.bl.documentbl.giftdoc.GiftDoc;
import nju.lighting.bl.documentbl.lossandgaindoc.LossAndGainDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesReturnDoc;
import nju.lighting.bl.documentbl.stockdoc.StockDoc;
import nju.lighting.bl.documentbl.stockdoc.StockReturnDoc;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import shared.DocType;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created on 2017/11/7.
 * Description: 处理红冲相关的业务功能
 * @author Liao
 */
public class RedFlush {
    public static final String RED_FLUSH_COMMENT = "红冲";

    private Map<DocType, Function<DocVO, Doc>> docMap;
    private DocDataService dataService;

    RedFlush() {
        docMap = new HashMap<>();

        docMap.put(DocType.ACCOUNT_IN, AccountInDoc::new);
        docMap.put(DocType.ACCOUNT_OUT, AccountOutDoc::new);
        docMap.put(DocType.COST, CostDoc::new);
        docMap.put(DocType.GIFT, GiftDoc::new);
        docMap.put(DocType.LOSS_AND_GAIN, LossAndGainDoc::new);
        docMap.put(DocType.SALES, SalesDoc::new);
        docMap.put(DocType.SALES_RETURN, SalesReturnDoc::new);
        docMap.put(DocType.STOCK, StockDoc::new);
        docMap.put(DocType.STOCK_RETURN, StockReturnDoc::new);

        try {
            dataService = DataFactory.getDataBase(DocDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接进行红冲操作
     * @param docVO 需要进行红冲的单据
     * @return 红冲的结果
     */
    public ResultMessage redFlush(DocVO docVO) {
        Doc doc = docMap.get(docVO.getType()).apply(docVO);
        doc.redFlush();
        try {
            return dataService.commitDoc(doc.toPO()).t;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * 红冲并复制功能
     * @param target 需要进行红冲并复制的单据对象
     * @return 相应的进行红冲过的对象
     */
    public DocVO redFlushAndCopy(DocVO target) {
        Doc doc = docMap.get(target.getType()).apply(target);
        doc.redFlush();
        return doc.toVO();
    }
}
