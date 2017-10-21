package nju.lighting.dataservice.promotiondataservice;

import nju.lighting.po.PromotionPO;
import nju.lighting.po.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface PromotionDataService {
    ArrayList<PromotionPO> getPromotionList();

    ResultMessage insert(PromotionPO po);

    ResultMessage update(PromotionPO po);

    ResultMessage delete(PromotionPO po);

}
