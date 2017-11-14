package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.CommodityTreeVO;
import shared.CommodityTreeNode;

import java.util.ArrayList;

/**
 * 商品全部信息组成的商品树
 */
public class CommodityTree {


    private ArrayList<CommodityTreeNode> roots;

    public CommodityTree(ArrayList<CommodityTreeNode> roots) {
        this.roots = roots;
    }

    public ArrayList<CommodityTreeNode> getRoots() {
        return roots;
    }

    public void setRoots(ArrayList<CommodityTreeNode> roots) {
        this.roots = roots;
    }

    public CommodityTreeVO toVO() {
        return null;
    }

    public BasicCommodityTree toBasicCommodityTree() {
        return null;
    }

}
