package nju.lighting.vo.commodity;

import shared.CommodityTreeNode;

public class BasicCommodityTreeVO {

    private CommodityTreeNode root;

    public BasicCommodityTreeVO(CommodityTreeNode root) {
        this.root = root;
    }

    public CommodityTreeNode getRoot() {
        return root;
    }

    public void setRoot(CommodityTreeNode root) {
        this.root = root;
    }
}
