package nju.lighting.vo.commodity;

import shared.CommodityTreeNode;

import java.util.ArrayList;

public class BasicCommodityTreeVO {

    private ArrayList<CommodityTreeNode> roots;

    public BasicCommodityTreeVO(ArrayList<CommodityTreeNode> roots) {
        this.roots = roots;
    }

    public ArrayList<CommodityTreeNode> getRoots() {
        return roots;
    }

    public void setRoots(ArrayList<CommodityTreeNode> roots) {
        this.roots = roots;
    }
}

