package nju.lighting.data.promotiondata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.po.promotion.PromotionPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/11/27.
 * Description: Promotion的数据层模块
 *
 * @author iznauy
 */
public class PromotionData implements PromotionDataService {

    private CommonOperation<PromotionPO> commonOperation;

    public PromotionData() {
        commonOperation = new CommonOperation<>(PromotionPO.class.getName());
    }

    @Override
    public List<PromotionPO> getPromotionList() throws RemoteException {
        return commonOperation.getAll();
    }

    @Override
    public ResultMessage insert(PromotionPO po) throws RemoteException {
        return commonOperation.add(po);
    }

    @Override
    public ResultMessage update(PromotionPO po) throws RemoteException {
        return commonOperation.update(po);
    }

    @Override
    public ResultMessage delete(int id) throws RemoteException {
        return commonOperation.deleteBySingleField("id", id);
    }

    @Override
    public PromotionPO getPromotionById(int id) throws RemoteException {
        return commonOperation.getBySingleField("id", id);
    }
}
