package nju.lighting.vo.commodity;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.po.commodity.CommodityItemPO;
import shared.ICommodityTreeNode;

import java.util.ArrayList;
import java.util.List;

public class CommodityCategoryVO {

    private CommodityCategoryVO upperCategory;
    private List<CommodityCategoryVO> children;
    private int id;
    private String name;
    private boolean isLeaf;
    private String path;

    public CommodityCategoryVO(CommodityCategoryVO upperCategory, int id, String name, boolean isLeaf) {
        this.upperCategory = upperCategory;
        this.id = id;
        this.name = name;
        this.isLeaf = isLeaf;
    }

    public CommodityCategoryVO(CommodityCategoryVO upperCategory, String name) {
        this.upperCategory = upperCategory;
        this.name = name;
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

    public List<CommodityCategoryVO> getChildren() {
        return children;
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

    public String getPath() {
        if (path == null) {
            path = getParentPath() + id;
        }
        return path;
    }

    public String getParentPath() {
        StringBuilder parentPath = new StringBuilder();

        // Use a pointer and a loop to get the parent path
        CommodityCategoryVO curr = this;
        while (curr.upperCategory != null && curr.upperCategory.id != -1) {
            parentPath.insert(0, curr.upperCategory.getId() + CommodityBLService.SEPARATOR);
            curr = curr.upperCategory;
        }
        return parentPath.toString();
    }
}
