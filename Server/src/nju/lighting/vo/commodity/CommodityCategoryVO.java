package nju.lighting.vo.commodity;

import shared.CommodityTreeNode;

import java.util.ArrayList;

public class CommodityCategoryVO implements CommodityTreeNode {

    private CommodityCategoryVO upperCategory;

    private ArrayList<CommodityTreeNode> children = new ArrayList<>();

    private int id;

    private String name;

    public CommodityCategoryVO getUpperCategory() {
        return upperCategory;
    }

    public void setUpperCategory(CommodityCategoryVO upperCategory) {
        this.upperCategory = upperCategory;
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

    public ArrayList<CommodityTreeNode> getChildren() {
        return children;
    }

    public void addChild(CommodityTreeNode child) {
        children.add(child);
    }

    public CommodityCategoryVO(CommodityCategoryVO upperCategory, int id, String name) {
        this.upperCategory = upperCategory;
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean isCommodity() {
        return false;
    }
}
