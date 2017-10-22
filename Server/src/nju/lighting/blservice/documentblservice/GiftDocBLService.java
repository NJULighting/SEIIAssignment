package nju.lighting.blservice.documentblservice;

import nju.lighting.po.ResultMessage;
import nju.lighting.vo.GiftDocVO;
import nju.lighting.vo.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface GiftDocBLService {
    ResultMessage createGiftDoc (GiftDocVO vo) throws RemoteException;

    
}

