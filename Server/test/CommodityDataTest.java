
import nju.lighting.data.commoditydata.CommodityData;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import org.junit.Test;
import shared.ResultMessage;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CommodityDataTest {

    private CommodityData commodityData = new CommodityData();

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
        CommodityItemPO po = new CommodityItemPO("2-0002", "美国大燃油灯", 2, "燃油",
                100, 100, 1000,
                1000, 1000, "第三批", "003", new Date());
        ResultMessage message = commodityData.add(po);
        assertEquals(message, ResultMessage.SUCCESS);
    }

}
