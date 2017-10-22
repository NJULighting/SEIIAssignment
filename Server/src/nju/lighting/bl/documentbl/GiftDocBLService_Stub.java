package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.GiftDocBLService;
import nju.lighting.po.CustomerGrade;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.CommodityVO;
import nju.lighting.vo.GiftDocVO;
import nju.lighting.vo.GiftItemVO;
import nju.lighting.vo.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftDocBLService_Stub implements GiftDocBLService {
    @Override
    public ResultMessage createGiftDoc(GiftDocVO vo) throws RemoteException {
        return ResultMessage.SUCCESS;
    }


}
