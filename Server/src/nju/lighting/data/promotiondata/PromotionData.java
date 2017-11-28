package nju.lighting.data.promotiondata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.po.promotion.PromotionPO;
import nju.lighting.po.promotion.PromotionPackageItemPO;
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

    private CommonOperation<PromotionPackageItemPO> itemPOCommonOperation;

    public PromotionData() {
        commonOperation = new CommonOperation<>(PromotionPO.class.getName());
        itemPOCommonOperation = new CommonOperation<>(PromotionPackageItemPO.class.getName());
    }

    @Override
    public List<PromotionPO> getPromotionList() throws RemoteException {
        List<PromotionPO> pos = commonOperation.getAll();
        for(int i = 0; i < pos.size(); i++) {
            PromotionPO po = pos.get(i);
            int id = po.getId();
            List<PromotionPackageItemPO> itemPOS = itemPOCommonOperation.getListBySingleField("promotionId", id);
            po.setGoods(itemPOS);
            pos.set(i, po);
        }
        return pos;
    }

    @Override
    public ResultMessage insert(PromotionPO po) throws RemoteException {
        ResultMessage resultMessage =  commonOperation.add(po);
        if (resultMessage == ResultMessage.FAILURE)
            return ResultMessage.FAILURE;
        return itemPOCommonOperation.addList(po.getGoods());
    }

    @Override
    public ResultMessage update(PromotionPO po) throws RemoteException {
        return commonOperation.update(po);
    }

    @Override
    public ResultMessage delete(int id) throws RemoteException {
        ResultMessage resultMessage = commonOperation.deleteBySingleField("id", id);
        if (resultMessage == ResultMessage.FAILURE)
            return ResultMessage.SUCCESS;
        return itemPOCommonOperation.deleteBySingleField("promotionId", id);
    }

    @Override
    public PromotionPO getPromotionById(int id) throws RemoteException {
        PromotionPO promotionPO =  commonOperation.getBySingleField("id", id);
        if (promotionPO == null)
            return null;
        List<PromotionPackageItemPO> itemPOS = itemPOCommonOperation.getListBySingleField("promotionId", id);
        promotionPO.setGoods(itemPOS);
        return promotionPO;
    }
}
