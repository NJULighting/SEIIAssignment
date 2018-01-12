package nju.lighting.data.promotiondata;

import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.po.promotion.PromotionPO;
import shared.Result;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description
 *
 * @author 陈俊宇
 */

@Deprecated
public class PromotionDataService_Stub implements PromotionDataService {

    @Override
    public List<PromotionPO> getPromotionList() throws RemoteException {
        return new ArrayList<>();
    }

    @Override
    public Result<Integer> insert(PromotionPO po) throws RemoteException {
        return po == null ? null : new Result<>(ResultMessage.FAILURE, 233);
    }

    @Override
    public ResultMessage update(PromotionPO po) throws RemoteException {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public PromotionPO getPromotionById(int id) throws RemoteException {
        return null;
    }
}
