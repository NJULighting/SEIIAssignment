package nju.lighting.blservice.promotionblservice;

import com.sun.org.apache.regexp.internal.RE;
import nju.lighting.vo.PromotionVO;
import nju.lighting.vo.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface PromotionBLService {

    ArrayList<PromotionVO> getBenefitsPlan(int customerLevel, ArrayList<String> CommodityList, double total) throws RemoteException; //得到满足条件的销售策略

    ArrayList<PromotionVO> getPromotionList () throws RemoteException;

    ResultMessage newAndSave (PromotionVO vo) throws RemoteException;

    ResultMessage modifyAndSave(PromotionVO vo) throws RemoteException;

    ResultMessage delete(PromotionVO vo) throws RemoteException;
}
