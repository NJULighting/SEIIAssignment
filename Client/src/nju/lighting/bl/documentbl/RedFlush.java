package nju.lighting.bl.documentbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import shared.OPType;
import shared.Result;
import shared.ResultMessage;

import javax.naming.NamingException;

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
        factory = DocFactory.INSTANT;
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
        doc.executeRedFlush();

        // Commit and log
        Result<String> commitResult = DataServiceFunction.addToDataBase(doc.toPO(), dataService::commitDoc);
        if (commitResult.hasValue()) {
            logger.add(OPType.ADD, "红冲单据" + doc.getId() + " 红冲单据id为：" + commitResult.getValue());
        }
        return commitResult.getResultMessage();
    }

}
