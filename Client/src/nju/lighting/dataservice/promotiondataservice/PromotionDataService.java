package nju.lighting.dataservice.promotiondataservice;

import nju.lighting.po.promotion.PromotionPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface PromotionDataService {

    ArrayList<PromotionPO> getBenefitsPlan(int customerLevel, ArrayList<String> commodityList, double total) throws RemoteException;

    ArrayList<PromotionPO> getPromotionList() throws RemoteException;

    ResultMessage insert(PromotionPO po) throws RemoteException;

    ResultMessage update(PromotionPO po) throws RemoteException;

    ResultMessage delete(PromotionPO po) throws RemoteException;

}
