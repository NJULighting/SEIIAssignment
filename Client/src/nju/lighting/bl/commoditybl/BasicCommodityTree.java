package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityTreeVO;
import shared.CommodityTreeNode;

/**
 * 商品基本信息组成的商品树
 */
public class BasicCommodityTree {

    private CommodityTreeNode root;

    public BasicCommodityTree(CommodityTreeNode root) {
        this.root = root;
    }

    public CommodityTreeNode getRoot() {
        return root;
    }

    public void setRoot(CommodityTreeNode root) {
        this.root = root;
    }

    public BasicCommodityTreeVO toVO() {
        return null;
    }

}
