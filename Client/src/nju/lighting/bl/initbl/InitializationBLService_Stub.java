package nju.lighting.bl.initbl;

import nju.lighting.blservice.initblservice.InitializationBLService;
import nju.lighting.vo.InitVO;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.List;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class InitializationBLService_Stub implements InitializationBLService {
    @Override
    public List<InitVO> getInitInfo() {
        return null;
    }

    @Override
    public TwoTuple<ResultMessage, InitVO> initiateAccount() {
        return null;
    }
}
