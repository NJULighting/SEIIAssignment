package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.GiftDocBLService;
import shared.ResultMessage;
import nju.lighting.vo.GiftDocVO;

import java.rmi.RemoteException;

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
