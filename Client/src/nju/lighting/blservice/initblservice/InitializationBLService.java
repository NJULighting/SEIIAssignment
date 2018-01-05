package nju.lighting.blservice.initblservice;

import nju.lighting.vo.InitVO;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.List;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface InitializationBLService {
    /**
     * Get a list that contains all information about accounts initialization
     * @return list of <code>InitVO</code>, if there's no information it will return an immutable empty list
     */
    List<InitVO> getInitInfo();

    /**
     * Execute initialization
     * @return <code>SUCCESS</code> if build successfully<br>
     * <code>FAILURE</code> if there's an exception when create the information file or the network fails
     */
    TwoTuple<ResultMessage, InitVO> initiateAccount();
}
