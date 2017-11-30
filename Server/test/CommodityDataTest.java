
import nju.lighting.data.commoditydata.CommodityData;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import org.junit.Test;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/26.
 * Description: Commodity模块测试代码
 * @author iznauy
 * 测试通过情况：全部通过
 * 最后测试时间：2017/11/26
 */
public class CommodityDataTest {

    private CommodityData commodityData = new CommodityData();

    public CommodityDataTest() throws RemoteException {
    }

    @Test
    public void getAllCommodities() throws Exception {
        List<CommodityItemPO> commodityItemPOList = commodityData.getAllCommodity();
        for (CommodityItemPO itemPO: commodityItemPOList) {
            System.out.println(itemPO.toString());
        }
    }

    @Test
    public void getAllCommodityCategory() throws Exception {
        List<CommodityCategoryPO> categoryPOS = commodityData.getAllCommodityCategory();
        for (CommodityCategoryPO categoryPO: categoryPOS) {
            System.out.println(categoryPO);
        }
    }

    @Test
    public void findCommodityById() throws Exception {
        CommodityItemPO commodityItemPO = commodityData.findById("3-0001");
        System.out.println(commodityItemPO);
    }

    @Test
    public void findCommodityByName() throws Exception {
        List<CommodityItemPO> commodityItemPOList = commodityData.findByName("质量堪忧的LED5W灯泡");
        assertEquals(1, commodityItemPOList.size());
        for (CommodityItemPO itemPO: commodityItemPOList) {
            System.out.println(itemPO.toString());
        }
    }

    @Test
    public void add() throws Exception {
        CommodityItemPO po = new CommodityItemPO("2-0007", "美国大燃油灯", 2, "燃油",
                100, 100, 1000,
                1000, 1000, "第三批", "003", new Date());
        ResultMessage message = commodityData.add(po);
        assertEquals(ResultMessage.SUCCESS, message);
    }

    @Test
    public void update() throws Exception {
        CommodityItemPO po = new CommodityItemPO("2-0007", "美国燃油灯", 2, "燃油",
                1000, 1000, 1000,
                1000, 1000, "第三批", "003", new Date());
        ResultMessage message = commodityData.update(po);
        assertEquals(ResultMessage.SUCCESS, message);
    }

    @Test
    public void deleteCommodity() throws Exception {
        String id = "2-0007";
        ResultMessage message = commodityData.deleteCommodity(id);
        assertEquals(ResultMessage.SUCCESS, message);
    }

    @Test
    public void addCategory() throws Exception {
        CommodityCategoryPO categoryPO = new CommodityCategoryPO ("意大利炮", -1);
        ResultMessage message = commodityData.add(categoryPO);
        assertEquals(ResultMessage.SUCCESS, message);
    }

    @Test
    public void deleteCategory() throws Exception {
        int id = 3;
        ResultMessage message = commodityData.deleteCategory(id);
        assertEquals(ResultMessage.SUCCESS, message);
    }

}
