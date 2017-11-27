package nju.lighting.data.initdata;

import nju.lighting.dataservice.initdataservice.InitDataService;
import nju.lighting.po.init.InitPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/27.
 * Description:
 *
 * @author iznauy
 */
public class InitData implements InitDataService {

    @Override
    public ResultMessage createInit(String userId, Date date) throws RemoteException {
        return null;
    }

    @Override
    public List<InitPO> getAllInit() throws RemoteException {
        return null;
    }
}
