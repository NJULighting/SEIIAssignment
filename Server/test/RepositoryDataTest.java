import nju.lighting.data.repositorydata.RepositoryData;
import nju.lighting.po.repository.RepositoryChangePO;
import nju.lighting.po.repository.RepositoryTableItemPO;
import nju.lighting.po.repository.RepositoryTablePO;
import org.junit.Test;
import shared.RepositoryChangeType;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/26.
 * Description: Repository模块测试代码
 * @author iznauy
 * 测试通过情况：2/3 getRepositoryChanges事实上没有获取到库存变化信息
 * 最后测试时间：2017/11/26
 */
public class RepositoryDataTest {

    private RepositoryData repositoryData = new RepositoryData();

    public RepositoryDataTest() throws RemoteException {
    }

    @Test
    public void getRepositoryChanges() throws Exception {
        List<RepositoryChangePO> changePOS = repositoryData.getRepositoryChanges(new Date(0, 1, 1), new Date(122, 1, 1));
        for (RepositoryChangePO changePO: changePOS) {
            System.out.println(changePO);
        }
    }

    @Test
    public void getRepositoryTable() throws Exception {
        RepositoryTablePO tablePO = repositoryData.getRepositoryTable();
        ArrayList<RepositoryTableItemPO> itemPOS = tablePO.getRepositoryTableItemPOS();
        for (RepositoryTableItemPO itemPO: itemPOS) {
            System.out.println(itemPO);
        }
    }

    @Test
    public void changeRepository() throws Exception {
        RepositoryChangePO changePO = new RepositoryChangePO("1-1", RepositoryChangeType.LOSS, 10, 10, new Date());
        ResultMessage resultMessage = repositoryData.changeRepository(changePO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }
}
