package nju.lighting.bl.promotionbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.po.promotion.PromotionPO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.OPType;
import shared.PromotionBuildInfo;
import shared.ResultMessage;
import shared.TwoTuple;

import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created on 2017/11/14.
 * Description
 * @author 陈俊宇
 */
enum PromotionManager {
    INSTANCE;

    private PromotionDataService dataService;
    private Logger logger;

    PromotionManager() {
        try {
            dataService = DataFactory.getDataBase(PromotionDataService.class);
            logger = new UserLogger();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    TwoTuple<ResultMessage, PromotionVO> commit(PromotionBuildInfo info) {
        Promotion promotion = new Promotion(info);
        PromotionPO po = promotion.buildPO();
        TwoTuple<ResultMessage, PromotionVO> res = new TwoTuple<>();

        // Write to database
        try {
            TwoTuple<ResultMessage, Integer> insertResult = dataService.insert(po);
            res.t = insertResult.t;
            if (insertResult.t == ResultMessage.SUCCESS) {
                logger.add(OPType.ADD, "创建促销策略" + insertResult.r);
                promotion.setId(insertResult.r);
                res.r = promotion.toVO();
            }

            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            res.t = ResultMessage.NETWORK_FAIL;
            return res;
        }
    }


}
