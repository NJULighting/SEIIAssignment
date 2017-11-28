package nju.lighting.dataservice.promotiondataservice;

import nju.lighting.po.promotion.PromotionPO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface PromotionDataService extends Remote {

    List<PromotionPO> getPromotionList() throws RemoteException;

    ResultMessage insert(PromotionPO po) throws RemoteException;

    ResultMessage update(PromotionPO po) throws RemoteException;

    ResultMessage delete(int id) throws RemoteException;

    PromotionPO getPromotionById(int id) throws RemoteException;

}
