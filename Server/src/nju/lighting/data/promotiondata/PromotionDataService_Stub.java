package nju.lighting.data.promotiondata;

import nju.lighting.dataservice.promotiondataservice.PromotionDataService;

import nju.lighting.po.promotion.PromotionPO;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionDataService_Stub implements PromotionDataService {

    @Override
    public List<PromotionPO> getPromotionList() throws RemoteException {
        return null;
    }

    @Override
    public TwoTuple<ResultMessage, Integer> insert(PromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(PromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public PromotionPO getPromotionById(int id) throws RemoteException {
        return null;
    }
}
