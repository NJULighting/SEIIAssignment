package nju.lighting.bl.initbl;

import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.initdataservice.InitDataService;
import nju.lighting.po.init.InitPO;
import nju.lighting.vo.InitVO;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/6.
 * Description:
 * @author Liao
 */
enum InitHelper {
    INSTANCE;

    private InitDataService dataService;

    InitHelper() {
        try {
            dataService = DataFactory.getDataBase(InitDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a list that contains all information about accounts initialization
     * @return list of <code>InitVO</code>, if there's no information it will return an immutable empty list
     */
    List<InitVO> getInitInfo() {
        try {
            List<InitPO> poList = dataService.getAllInit();
            return transformPOs(poList);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    private List<InitVO> transformPOs(List<InitPO> poList) {
        UserInfo userInfo = new UserInfoImpl();
        return poList.stream().map(po -> new InitVO(po.getId(), po.getTime(), po.getUserID(), po.getUrl(), userInfo.getNameByID(po.getUserID())))
                .collect(Collectors.toList());
    }
}
