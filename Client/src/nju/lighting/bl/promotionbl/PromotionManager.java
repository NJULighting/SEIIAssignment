package nju.lighting.bl.promotionbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.bl.utils.DataServiceSupplier;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.po.promotion.PromotionPO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.*;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

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

    Result<PromotionVO> commit(PromotionBuildInfo.Builder builder) {
        // Commit and get result
        Promotion promotion = new Promotion(builder.build());
        Result<Integer> commitResult = DataServiceFunction.addToDataBase(promotion.toPO(), dataService::insert);

        // Commit successfully
        if (commitResult.hasValue()) {
            logger.add(OPType.ADD, "创建促销策略" + commitResult.getValue());
            promotion.setId(commitResult.getValue());
            return new Result<>(commitResult.getResultMessage(), promotion.toVO());
        }

        // Failed to commit
        return new Result<>(commitResult.getResultMessage(), null);
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
        // Check null
        if (customerLevel == null || commodityIDList == null) {
            throw new IllegalArgumentException("CustomerGrade and commodityList mustn't be null");
        }

        Date currentDate = new Date();
        Predicate<PromotionPO> filter =
                promotionPO -> promotionPO.getEndDate().after(currentDate)
                        && promotionPO.getStartDate().before(currentDate)
                        && (promotionPO.getLevel().compareTo(customerLevel) <= 0
                        || containsItemOfList(commodityIDList, promotionPO)
                        || promotionPO.getTotal() < total);

        return DataServiceSupplier.getAllAndFilter(dataService::getPromotionList, po -> new Promotion(po).toVO(), filter);

    }

    List<PromotionVO> getPromotionList() {
        return DataServiceSupplier.getAll(dataService::getPromotionList, po -> new Promotion(po).toVO());
    }


    /**
     * Check the goods of po whether have the same item of target commodity list
     * @param commodityIDList ids of commodities
     * @param po              po to be matched
     * @return <code>true</code> if matches any, <code>false</code> if po's list is null or matches none
     */
    private boolean containsItemOfList(List<String> commodityIDList, PromotionPO po) {
        return po.getGoods() != null
                && po.getGoods().stream().anyMatch(item -> commodityIDList.contains(item.getCommodityId()));

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
