package nju.lighting.data.commoditydata;

import nju.lighting.po.commodity.CommodityItemPO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/12/8.
 * Description:
 *
 * @author iznauy
 */
public interface CommodityService {

    List<CommodityItemPO> getAllCommodity() throws RemoteException;

}
