package nju.lighting.vo.commodity;

/**
 * Created on 2017/12/9.
 * Description:
 * @author Liao
 */
public class CommodityCategoriesTreeVO {
    private CommodityCategoryVO root;

    public CommodityCategoriesTreeVO(CommodityCategoryVO root) {
        this.root = root;
    }

    public CommodityCategoryVO getRoot() {
        return root;
    }
}
