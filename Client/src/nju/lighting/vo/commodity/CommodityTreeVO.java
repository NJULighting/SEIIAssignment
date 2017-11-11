package nju.lighting.vo.commodity;

import shared.CommodityTreeNode;

public class CommodityTreeVO {

    private CommodityTreeNode root;

    public CommodityTreeVO(CommodityTreeNode root) {
        this.root = root;
    }

    public CommodityTreeNode getRoot() {
        return root;
    }

    public void setRoot(CommodityTreeNode root) {
        this.root = root;
    }
}
