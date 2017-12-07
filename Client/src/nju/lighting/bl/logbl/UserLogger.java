package nju.lighting.bl.logbl;

import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.log.LogPO;
import shared.OPType;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class UserLogger implements Logger {
    // TODO: 2017/12/4 Refactor this class
    private LogDataService dataService;

    public UserLogger() {
        try {
            dataService = DataFactory.getDataBase(LogDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(OPType type, String message) {
        try {
            String finalMessage = processMessage(type, message);
            UserInfo userInfo = new UserInfoImpl();
            LogPO log = new LogPO(new Date(), finalMessage, 0, userInfo.getIDOfSignedUser());
            dataService.insert(log);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(OPType type, Object object) {
        try {
            String finalMessage = processMessage(type, object.toString());
            UserInfo userInfo = new UserInfoImpl();
            dataService.insert(new LogPO(new Date(), finalMessage, 0, userInfo.getIDOfSignedUser()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private String processMessage(OPType type, String message) {
        return type + " : " + message;
    }
}
