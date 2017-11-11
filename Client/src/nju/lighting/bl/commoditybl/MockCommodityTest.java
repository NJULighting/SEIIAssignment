package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.*;
import org.junit.Test;
import shared.CommodityTreeNode;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MockCommodityTest {

    @Test
    public void getCommodityTree() throws Exception {
        CommodityInfo mockCommodity = new MockCommodity();
        CommodityTreeVO commodityTreeVO = mockCommodity.getCommodityTree();
        CommodityTreeNode root = commodityTreeVO.getRoot();
        assertEquals(root.isCommodity(), false);
        ArrayList<CommodityTreeNode> nodes = ((CommodityCategoryVO) root).getChildren();
        for (CommodityTreeNode node : nodes) {
            assertEquals(node.isCommodity(), false);
            for (CommodityTreeNode subNode : ((CommodityCategoryVO) node).getChildren()) {
                assertEquals(node.isCommodity(), true);
            }
        }
    }

    @Test
    public void getBasicCommodityTree() throws Exception {
        CommodityInfo mockCommodity = new MockCommodity();
        BasicCommodityTreeVO commodityTreeVO = mockCommodity.getBasicCommodityTree();
        CommodityTreeNode root = commodityTreeVO.getRoot();
        assertEquals(root.isCommodity(), false);
        ArrayList<CommodityTreeNode> nodes = ((CommodityCategoryVO) root).getChildren();
        for (CommodityTreeNode node : nodes) {
            assertEquals(node.isCommodity(), false);
            for (CommodityTreeNode subNode : ((CommodityCategoryVO) node).getChildren()) {
                assertEquals(node.isCommodity(), true);
            }
        }
    }

    @Test
    public void getBasicCommodityItems() throws Exception {
        CommodityInfo mockCommodity = new MockCommodity();
        ArrayList<String> ids = new ArrayList<>();
        ids.add("01");
        ids.add("02");
        ArrayList<CommodityItemVO> items = mockCommodity.getCommodityItems(ids);
        assertEquals(items.get(0).getId(), "01");
        assertEquals(items.get(1).getId(), "02");
    }

    @Test
    public void getCommodityItems() throws Exception {
        CommodityInfo mockCommodity = new MockCommodity();
        ArrayList<String> ids = new ArrayList<>();
        ids.add("01");
        ids.add("02");
        ArrayList<BasicCommodityItemVO> items = mockCommodity.getBasicCommodityItems(ids);
        assertEquals(items.get(0).getId(), "01");
        assertEquals(items.get(1).getId(), "02");
    }

}