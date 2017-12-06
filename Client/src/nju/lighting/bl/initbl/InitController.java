package nju.lighting.bl.initbl;

import nju.lighting.blservice.initblservice.InitializationBLService;
import nju.lighting.vo.InitVO;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/12/6.
 * Description:
 * @author Liao
 */
public class InitController implements InitializationBLService {
    private InitHelper initHelper = InitHelper.INSTANCE;

    @Override
    public List<InitVO> getInitInfo() {
        return initHelper.getInitInfo();
    }

    @Override
    public ResultMessage initiateAccount() {
        return initHelper.createInit();
    }
}
