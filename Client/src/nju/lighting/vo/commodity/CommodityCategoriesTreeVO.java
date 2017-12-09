package nju.lighting.vo.commodity;

import nju.lighting.bl.commoditybl.CommodityTreeNode;

/**
 * Created on 2017/12/9.
 * Description:
 * @author Liao
 */
public class CommodityCategoriesTreeVO {
    private static final String ROOT = "root";
    private CommodityCategoryVO root;

    public CommodityCategoriesTreeVO(CommodityCategoryVO root) {
        this.root = root;
    }

    public CommodityCategoryVO getRoot() {
        return root;
    }
}
