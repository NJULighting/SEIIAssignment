package nju.lighting.bl.promotionbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.po.promotion.PromotionPO;
import nju.lighting.po.promotion.PromotionPackageItemPO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.*;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        PromotionPO po = promotion.buildPOForCreation();
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

    /**
     * Get promotion strategies which satisfy the condition you passed
     * @param customerLevel   level of customer
     * @param commodityIDList ids of commodities
     * @param total           price target for promotion
     * @return promotion vos meet these conditions
     * @throws IllegalArgumentException if customerLevel or commodityIDList is null
     */
    List<PromotionVO> getBenefitsPlan(CustomerGrade customerLevel, List<String> commodityIDList, double total) {
        // Check null todo time's problem
        if (customerLevel == null || commodityIDList == null) {
            throw new IllegalArgumentException("CustomerGrade and commodityList mustn't be null");
        }
        try {
            List<PromotionPO> poList = dataService.getPromotionList();
            Predicate<PromotionPO> filterPredicate =
                    promotionPO -> promotionPO.getLevel().compareTo(customerLevel) <= 0
                            || containsItemOfList(commodityIDList, promotionPO)
                            || promotionPO.getTotal() < total;
            return poList.stream()
                    .filter(filterPredicate)
                    .map(po -> new Promotion(po).toVO()).collect(Collectors.toList());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    List<PromotionVO> getPromotionList() {
        try {
            List<PromotionPO> poList = dataService.getPromotionList();
            return poList.stream().map(po -> new Promotion(po).toVO()).collect(Collectors.toList());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Check the goods of po whether have same item of target commodity list
     * @param commodityIDList ids of commodities
     * @param po              po to be matched
     * @return <code>true</code> if matches any, <code>false</code> if po's list is null or matches none
     */
    private boolean containsItemOfList(List<String> commodityIDList, PromotionPO po) {
        if (po.getGoods() == null)
            return false;

        for (PromotionPackageItemPO itemPO : po.getGoods()) {
            if (commodityIDList.contains(itemPO.getCommodityId())) {
                return true;
            }
        }
        return false;
        // TODO: 2017/12/7 Something of structured programming
    }

    ResultMessage modify(PromotionVO vo) {
        try {
            PromotionPO po = dataService.getPromotionById(vo.getId());
            if (po == null)
                return ResultMessage.FAILURE;
            // Change info and log
            Promotion promotion = new Promotion(po);
            ResultMessage res = promotion.changeInfo(vo);
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.MODIFY, "修改促销策略" + vo.getId());
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }
}
