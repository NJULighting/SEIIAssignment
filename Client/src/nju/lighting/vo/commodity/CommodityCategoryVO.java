package nju.lighting.vo.commodity;

import shared.ICommodityTreeNode;

import java.util.ArrayList;
import java.util.List;

public class CommodityCategoryVO {

    private CommodityCategoryVO upperCategory;
    private List<CommodityCategoryVO> children;
    private int id;
    private String name;
    private boolean isLeaf;

    public CommodityCategoryVO(CommodityCategoryVO upperCategory, int id, String name, boolean isLeaf) {
        this.upperCategory = upperCategory;
        this.id = id;
        this.name = name;
        this.isLeaf = isLeaf;
    }

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

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setChildren(List<CommodityCategoryVO> children) {
        this.children = children;
    }
}
