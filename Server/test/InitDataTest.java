
import nju.lighting.data.initdata.InitData;
import nju.lighting.po.init.InitPO;
import org.junit.Test;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/29.
 * Description:
 *
 * @author iznauy
 */
public class InitDataTest {

    private InitData initData;

    public InitDataTest() throws RemoteException {
        initData = new InitData();
    }

    @Test
    public void createInit() throws Exception {
        ResultMessage resultMessage = initData.createInit("161250220", new Date());
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void getAllInit() throws Exception {
        List<InitPO> initPOList = initData.getAllInit();
        for (InitPO initPO: initPOList) {
            System.out.println(initPO);
        }
    }

}