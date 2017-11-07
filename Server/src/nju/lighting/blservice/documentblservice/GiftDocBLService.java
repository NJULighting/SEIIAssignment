package nju.lighting.blservice.documentblservice;

import shared.ResultMessage;
import nju.lighting.vo.GiftDocVO;

import java.rmi.RemoteException;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface GiftDocBLService {
    ResultMessage createGiftDoc (GiftDocVO vo) throws RemoteException;

    
}

