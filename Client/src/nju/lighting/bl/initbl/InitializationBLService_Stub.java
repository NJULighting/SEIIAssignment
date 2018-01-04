package nju.lighting.bl.initbl;

import nju.lighting.bl.userbl.UserBLServie_Stub;
import nju.lighting.blservice.initblservice.InitializationBLService;
import nju.lighting.vo.InitVO;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class InitializationBLService_Stub implements InitializationBLService {
    @Override
    public List<InitVO> getInitInfo() {
        InitVO vo1 = new InitVO(new Date(), "http://lol.qq.com/", new UserBLServie_Stub().getUser("1"));
        InitVO vo2 = new InitVO(new Date(), "http://jw.nju.edu.cn/", new UserBLServie_Stub().getUser("1"));
        List<InitVO> list = new ArrayList<>();
        list.add(vo1);
        list.add(vo2);
        return list;
    }

    @Override
    public TwoTuple<ResultMessage, InitVO> initiateAccount() {

        return new TwoTuple<>(ResultMessage.SUCCESS, new InitVO(
                new Date(), "http://www.bing.com/", new UserBLServie_Stub().getUser("1")));
    }
}
