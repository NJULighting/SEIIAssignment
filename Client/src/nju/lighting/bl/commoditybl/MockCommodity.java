package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.repository.RepositoryTableItemVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockCommodity implements CommodityInfo {

    private ArrayList<CommodityItem> items = new ArrayList<>();

    private CommodityCategoriesTree tree = new CommodityCategoriesTree.TreeBuilder(null).build();

    public MockCommodity() {


        int cc1 = 1;
        int cc2 = 2;
        int cc3 = 3;
        int cc4 = 4;

        CommodityItem com1 = new CommodityItem("01", "天才灯具", cc1, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com2 = new CommodityItem("02", "天才灯炮", cc1, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com3 = new CommodityItem("03", "小天才灯具", cc2, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com4 = new CommodityItem("04", "小天才灯炮", cc2, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com5 = new CommodityItem("05", "大天才灯具", cc3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com6 = new CommodityItem("06", "大天才灯具", cc3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        items.add(com1);
        items.add(com2);
        items.add(com3);
        items.add(com4);
        items.add(com5);
        items.add(com6);
    }

    @Override
    public CommodityCategoriesTree getCommodityCategoryTree() {
        return tree;
    }

    @Override
    public BasicCommodityItemVO getBasicCommodityItemVO(String id) {
        return null;
    }

    @Override
    public double getCommodityRecentSellPrice(String id) {
        return 0;
    }

    @Override
    public List<CommodityItem> getCommodityItems(List<String> ids) {
        ArrayList<CommodityItem> comItems = new ArrayList<>();
        for (String id : ids) {
            for (CommodityItem item : items) {
                if (item.getId().equals(id))
                    comItems.add(item);
            }
        }
        return comItems;
    }

    @Override
    public String getCommodityNameByID(String commodityID) {
        return null;
    }

    @Override
    public List<RepositoryTableItemVO> getRepositoryTableItemList() {
        return null;
    }

    @Override
    public boolean addCommodityItem(String id, int count) {
        return false;
    }

    @Override
    public boolean reduceCommodityItem(String id, int count) {
        return false;
    }
}
