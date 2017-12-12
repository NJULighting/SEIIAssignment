package nju.lighting.bl.commoditybl;

import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.CommodityCategoryVO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/9.
 * Description:
 * @author Liao
 */
public class CommodityCategory {
    // Members of categories
    private CommodityCategory parent;
    private List<CommodityCategory> children;
    private int categoryID;
    private String categoryName;
    private boolean isLeaf;

    CommodityCategory(CommodityCategory parent, int categoryID, String categoryName, boolean isLeaf) {
        this.parent = parent;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.isLeaf = isLeaf;
    }

    public CommodityCategory getParent() {
        return parent;
    }

    public void setChildren(List<CommodityCategory> children) {
        this.children = children;
    }

    int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    CommodityCategoryVO toVO(CommodityCategoryVO parentVO) {
        CommodityCategoryVO curr = new CommodityCategoryVO(parentVO, categoryID, categoryName, isLeaf);
        List<CommodityCategoryVO> voChildren = children.stream()
                .map(commodityCategory -> commodityCategory.toVO(curr))
                .collect(Collectors.toList());
        curr.setChildren(voChildren);

        return curr;
    }
}
