package nju.lighting.bl.initbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.bl.utils.DataServiceBiFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.initdataservice.InitDataService;
import nju.lighting.po.init.InitPO;
import nju.lighting.vo.InitVO;
import shared.OPType;
import shared.ResultMessage;
import shared.TwoTuple;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
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
    private Logger logger;

    InitHelper() {
        try {
            logger = new UserLogger();
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

    /**
     * Execute initialization
     * @return <code>SUCCESS</code> if build successfully<br>
     * <code>FAILURE</code> if there's an exception when create the information file or the network fails
     */
    TwoTuple<ResultMessage, InitVO> createInit() {
        TwoTuple<ResultMessage, InitVO> createResult = new TwoTuple<>();
        UserInfo userInfo = new UserInfoImpl();

        TwoTuple<ResultMessage, InitPO> addResult =
                DataServiceBiFunction.commit(userInfo.getIDOfSignedUser(), new Date(), dataService::createInit);

        if (addResult.t == ResultMessage.SUCCESS) {
            logger.add(OPType.ADD, "完成期初建账");
            InitPO po = addResult.r;
            createResult.r = new InitVO(po.getId(), po.getTime(), po.getUrl(), userInfo.getUserVOByID(po.getUserID()));
        }

        createResult.t = addResult.t;

        return createResult;
    }

    /**
     * Transform list of pos to list of vos
     * @param poList list of po
     * @return list of vo
     */
    private List<InitVO> transformPOs(List<InitPO> poList) {
        UserInfo userInfo = new UserInfoImpl();
        return poList.stream().map(po -> new InitVO(po.getId(), po.getTime(), po.getUrl(), userInfo.getUserVOByID(po.getUserID())))
                .collect(Collectors.toList());
    }
}
