package nju.lighting.vo.commodity;

import shared.ICommodityTreeNode;

import java.util.ArrayList;

public class CommodityTreeVO {
    private ArrayList<ICommodityTreeNode> roots;

    public CommodityTreeVO(ArrayList<ICommodityTreeNode> roots) {
        this.roots = roots;
    }

    public ArrayList<ICommodityTreeNode> getRoots() {
        return roots;
    }

    public void setRoots(ArrayList<ICommodityTreeNode> roots) {
        this.roots = roots;
    }
}
