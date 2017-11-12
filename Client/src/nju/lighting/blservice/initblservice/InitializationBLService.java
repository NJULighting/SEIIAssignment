package nju.lighting.blservice.initblservice;

import nju.lighting.vo.InitVO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface InitializationBLService {
    InitVO getInitInfo() ;

    ResultMessage commit(InitVO vo) ;

    ArrayList<InitVO> getHistoryInfo() ;
}
