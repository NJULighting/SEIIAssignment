package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.accountiodoc.AccountInDoc;
import nju.lighting.bl.documentbl.accountiodoc.AccountOutDoc;
import nju.lighting.bl.documentbl.costdoc.CostDoc;
import nju.lighting.bl.documentbl.giftdoc.GiftDoc;
import nju.lighting.bl.documentbl.lossandgaindoc.LossAndGainDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesReturnDoc;
import nju.lighting.bl.documentbl.stockdoc.StockDoc;
import nju.lighting.bl.documentbl.stockdoc.StockReturnDoc;
import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import shared.DocType;
import shared.OPType;
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

    private DocFactory factory;
    private DocDataService dataService;
    private Logger logger;

    RedFlush() {
        factory = new DocFactory();
        try {
            dataService = DataFactory.getDataBase(DocDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        logger = new UserLogger();
    }

    /**
     * 直接进行红冲操作
     * @param docVO 需要进行红冲的单据
     * @return 红冲的结果
     */
    public ResultMessage redFlush(DocVO docVO) {
        Doc doc = factory.voToDoc(docVO);
        doc.redFlush();
        try {
            ResultMessage res =  dataService.commitDoc(doc.toPO()).t;
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.ADD, "红冲单据" + doc.getId());
            return res;
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
    DocVO redFlushAndCopy(DocVO target) {
        Doc doc = factory.voToDoc(target);
        doc.redFlush();
        logger.add(OPType.ADD, "对单据" + doc.getId() + "使用了红冲并复制操作");
        return doc.toVO();
    }
}
