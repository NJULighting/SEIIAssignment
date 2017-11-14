package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.CommodityCategoryVO;
import shared.CommodityTreeNode;

import java.util.ArrayList;

public class CommodityCategoryItem implements CommodityTreeNode{

    private CommodityCategoryItem upperCategory;

    private ArrayList<CommodityTreeNode> children = new ArrayList<>();

    private int id;

    private String name;

    public CommodityCategoryItem(CommodityCategoryItem upperCategory, ArrayList<CommodityTreeNode> children, int id, String name) {
        this.upperCategory = upperCategory;
        this.children = children;
        this.id = id;
        this.name = name;
    }

    public CommodityCategoryItem getUpperCategory() {
        return upperCategory;
    }

    public void setUpperCategory(CommodityCategoryItem upperCategory) {
        this.upperCategory = upperCategory;
    }

    public ArrayList<CommodityTreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<CommodityTreeNode> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommodityCategoryVO toVO () {
        return null;
    }

    @Override
    public boolean isCommodity() {
        return false;
    }
}
