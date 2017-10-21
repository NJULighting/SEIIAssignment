package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.DocPO;
import nju.lighting.po.PromotionPO;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface GiftDocDataService {

    ArrayList<PromotionPO> getBenefitsPlan(int customerLevel, ArrayList<String> commodityList, double total);

}
