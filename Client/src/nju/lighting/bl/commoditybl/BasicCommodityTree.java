package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityTreeVO;
import shared.CommodityTreeNode;

import java.util.ArrayList;

/**
 * 商品基本信息组成的商品树
 */
public class BasicCommodityTree {

    private ArrayList<CommodityTreeNode> roots;

    public BasicCommodityTree(ArrayList<CommodityTreeNode> roots) {
        this.roots = roots;
    }

    public ArrayList<CommodityTreeNode> getRoots() {
        return roots;
    }

    public void setRoots(ArrayList<CommodityTreeNode> roots) {
        this.roots = roots;
    }

    public BasicCommodityTreeVO toVO() {
        return null;
    }

}
