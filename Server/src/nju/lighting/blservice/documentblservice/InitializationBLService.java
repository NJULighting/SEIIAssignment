package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.InitVO;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface InitializationBLService {
    InitVO getInitInfo();

    void commit(InitVO vo);

    ArrayList<InitVO> getHistoryInfo();
}
