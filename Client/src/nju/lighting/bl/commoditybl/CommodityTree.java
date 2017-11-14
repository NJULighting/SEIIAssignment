package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.CommodityTreeVO;
import shared.CommodityTreeNode;

/**
 * 商品全部信息组成的商品树
 */
public class CommodityTree {


    private CommodityTreeNode root;

    public CommodityTree(CommodityTreeNode root) {
        this.root = root;
    }

    public CommodityTreeNode getRoot() {
        return root;
    }

    public void setRoot(CommodityTreeNode root) {
        this.root = root;
    }

    public CommodityTreeVO toVO() {
        return null;
    }

}
