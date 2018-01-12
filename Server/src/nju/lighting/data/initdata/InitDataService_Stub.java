package nju.lighting.data.initdata;

import nju.lighting.dataservice.initdataservice.InitDataService;
import nju.lighting.po.init.InitPO;
import shared.Result;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
@Deprecated
public class InitDataService_Stub implements InitDataService {

    @Override
    public Result<InitPO> createInit(String userId, Date date) throws RemoteException {
        return new Result<InitPO>(ResultMessage.SUCCESS, new InitPO(date, userId, "text.txt"));
    }

    @Override
    public List<InitPO> getAllInit() throws RemoteException {
        InitPO initPO = new InitPO(new Date(), "161250220", "text.txt");
        ArrayList<InitPO> initPOS = new ArrayList<>();
        initPOS.add(initPO);
        return initPOS;
    }
}
