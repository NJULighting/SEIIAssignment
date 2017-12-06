package nju.lighting.blservice.initblservice;

import nju.lighting.vo.InitVO;
import shared.ResultMessage;

import java.util.ArrayList;
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

    ResultMessage initiateAccount();
}
