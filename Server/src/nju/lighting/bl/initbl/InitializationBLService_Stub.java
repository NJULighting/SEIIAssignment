package nju.lighting.bl.initbl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.blservice.initblservice.InitializationBLService;
import shared.ResultMessage;
import nju.lighting.vo.CommodityVO;
import nju.lighting.vo.InitVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class InitializationBLService_Stub implements InitializationBLService {
    @Override
    public InitVO getInitInfo() throws RemoteException {
        CommodityBLService commodityBLService = new CommodityBLService_Stub();
        ArrayList<CommodityVO> commodityVOS = commodityBLService.getCommodityList();
        ArrayList<String> commodities = new ArrayList<>();
        ArrayList<String> commotityTypes = new ArrayList<>();
        for (CommodityVO vo : commodityVOS) {
            commodities.add(vo.getName());
            commotityTypes.add(vo.getCommodityType());
        }
        // Customers
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Frog0");
        customers.add("Frog1");
        // Accounts
        ArrayList<String> accounts = new ArrayList<>();
        accounts.add("Account0");
        accounts.add("Account1");
        InitVO initVO = new InitVO(commotityTypes, commodities, customers, accounts);
        return initVO;
    }

    @Override
    public ResultMessage commit(InitVO vo) throws RemoteException{
        return vo == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<InitVO> getHistoryInfo() throws RemoteException {
        ArrayList<InitVO> initVOS = new ArrayList<>();
        initVOS.add(getInitInfo());
        return initVOS;
    }
}
