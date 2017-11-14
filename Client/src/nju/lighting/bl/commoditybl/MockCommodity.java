package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.*;
import shared.CommodityTreeNode;

import java.util.ArrayList;
import java.util.Date;

public class MockCommodity implements CommodityInfo {

    private ArrayList<CommodityItem> items = new ArrayList<>();

    private CommodityTree tree = new CommodityTree(null);

    public MockCommodity() {


        CommodityCategoryItem cc1 = new CommodityCategoryItem(null, null,1, "天才");
        CommodityCategoryItem cc2 = new CommodityCategoryItem(null, null,2, "小天才");
        CommodityCategoryItem cc3 = new CommodityCategoryItem(null, null,3, "大天才");
        CommodityCategoryItem cc4 = new CommodityCategoryItem(null, null,4, "超级天才");

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

        ArrayList<CommodityTreeNode> cca1 = new ArrayList<>();
        cca1.add(com1);
        cca1.add(com2);
        ArrayList<CommodityTreeNode> cca2 = new ArrayList<>();
        cca2.add(com3);
        cca2.add(com4);
        ArrayList<CommodityTreeNode> cca3 = new ArrayList<>();
        cca3.add(com5);
        cca3.add(com6);
        cc1.setChildren(cca1);
        cc2.setChildren(cca2);
        cc3.setChildren(cca3);

        ArrayList<CommodityTreeNode> node = new ArrayList<>();
        node.add(cc1);
        node.add(cc2);
        node.add(cc3);
        node.add(cc4);

        tree.setRoots(node);
    }

    @Override
    public CommodityTree getCommodityTree() {
        return tree;
    }

    @Override
    public BasicCommodityTree getBasicCommodityTree() {
        return tree.toBasicCommodityTree();
    }

    @Override
    public ArrayList<BasicCommodityItem> getBasicCommodityItems(ArrayList<String> ids) {
        ArrayList<BasicCommodityItem> comItems = new ArrayList<>();
        for (String id: ids) {
            for (CommodityItem item : items) {
                if (item.getId().equals(id))
                    comItems.add(item.toBasicCommodityItem());
            }
        }
        return comItems;
    }

    @Override
    public ArrayList<CommodityItem> getCommodityItems(ArrayList<String> ids) {
        ArrayList<CommodityItem> comItems = new ArrayList<>();
        for (String id: ids) {
            for (CommodityItem item : items) {
                if (item.getId().equals(id))
                    comItems.add(item);
            }
        }
        return comItems;
    }
}
