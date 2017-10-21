package nju.lighting.blservice.promotionblservice;

import com.sun.org.apache.regexp.internal.RE;
import nju.lighting.vo.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface PromotionBLService {
    ArrayList<PromotionVO> getPromotionList () throws RemoteException;

    boolean newAndSave (PromotionVO vo) throws RemoteException;

    boolean modifyAndSave(PromotionVO vo) throws RemoteException;

    boolean delete(PromotionVO vo) throws RemoteException;
}
