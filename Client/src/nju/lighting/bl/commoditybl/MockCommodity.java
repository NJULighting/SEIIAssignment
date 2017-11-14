package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.*;

import java.util.ArrayList;
import java.util.Date;

public class MockCommodity implements CommodityInfo {

    private CommodityTreeVO commodityTree;

    private BasicCommodityTreeVO basicCommodityTree;

    private ArrayList<CommodityItemVO> commodityItems;

    private ArrayList<CommodityCategoryVO> commodityCategorys;

    public MockCommodity() {

        CommodityCategoryVO cc1 = new CommodityCategoryVO(null, 1, "天才");
        CommodityCategoryVO cc2 = new CommodityCategoryVO(null, 2, "小天才");
        CommodityCategoryVO cc3 = new CommodityCategoryVO(null, 3, "大天才");
        CommodityCategoryVO cc4 = new CommodityCategoryVO(null, 4, "超级天才");
        commodityCategorys = new ArrayList<>();

        CommodityItemVO com1 = new CommodityItemVO("01", "天才灯具", cc1, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemVO com2 = new CommodityItemVO("02", "天才灯炮", cc1, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemVO com3 = new CommodityItemVO("03", "小天才灯具", cc2, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemVO com4 = new CommodityItemVO("04", "小天才灯炮", cc2, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemVO com5 = new CommodityItemVO("05", "大天才灯具", cc3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemVO com6 = new CommodityItemVO("06", "大天才灯具", cc3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        commodityItems = new ArrayList<>();
        commodityItems.add(com1);
        commodityItems.add(com2);
        commodityItems.add(com3);
        commodityItems.add(com4);
        commodityItems.add(com5);
        commodityItems.add(com6);

        commodityCategorys.add(cc1);
        commodityCategorys.add(cc2);
        commodityCategorys.add(cc3);
        commodityCategorys.add(cc4);

        commodityTree = new CommodityTreeVO(new CommodityCategoryVO(null, 0, "Root"));
        CommodityCategoryVO root = (CommodityCategoryVO) commodityTree.getRoot();
        root.addChild(cc1);
        root.addChild(cc2);
        root.addChild(cc3);
        root.addChild(cc4);
        cc1.addChild(com1);
        cc1.addChild(com2);
        cc2.addChild(com3);
        cc2.addChild(com4);
        cc3.addChild(com5);
        cc3.addChild(com6);

        CommodityCategoryVO acc1 = new CommodityCategoryVO(null, 1, "天才");
        CommodityCategoryVO acc2 = new CommodityCategoryVO(null, 2, "小天才");
        CommodityCategoryVO acc3 = new CommodityCategoryVO(null, 3, "大天才");
        CommodityCategoryVO acc4 = new CommodityCategoryVO(null, 4, "超级天才");

        basicCommodityTree = new BasicCommodityTreeVO(new CommodityCategoryVO(null, 0, "Root"));
        root = (CommodityCategoryVO) commodityTree.getRoot();
        root.addChild(acc1);
        root.addChild(acc2);
        root.addChild(acc3);
        root.addChild(acc4);
        acc1.addChild(com1.toBasicCommodityItem());
        acc1.addChild(com2.toBasicCommodityItem());
        acc2.addChild(com3.toBasicCommodityItem());
        acc2.addChild(com4.toBasicCommodityItem());
        acc3.addChild(com5.toBasicCommodityItem());
        acc3.addChild(com6.toBasicCommodityItem());
    }


    @Override
    public CommodityTree getCommodityTree() {
        return null;
    }

    @Override
    public BasicCommodityTree getBasicCommodityTree() {
        return null;
    }

    @Override
    public ArrayList<BasicCommodityItem> getBasicCommodityItems(ArrayList<String> ids) {
        return null;
    }

    @Override
    public ArrayList<CommodityItem> getCommodityItems(ArrayList<String> ids) {
        return null;
    }
}
