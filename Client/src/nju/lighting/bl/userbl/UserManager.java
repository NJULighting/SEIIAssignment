package nju.lighting.bl.userbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.MockLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import nju.lighting.vo.UserVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public enum UserManager {
    INSTANCE;

    private UserDataService userDataService;
    private Logger logger;

    UserManager() {
        try {
            userDataService = DataFactory.getDataBase(UserDataService.class);
            logger = new MockLogger(); // TODO: 2017/11/30 Change logger here
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all users
     * @return User's list
     */
    public ArrayList<UserVO> getUserList() {
        try {
            List<UserPO> userPOs = userDataService.getAll();
            ArrayList<UserVO> userVOS = new ArrayList<>();
            userPOs.forEach(userPO -> userVOS.add(new User(userPO).toVO()));
            return userVOS;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addUser(UserVO user, String password) {
        return null;
    }
}
