package nju.lighting.po;

import java.util.ArrayList;

public class CommodityCategoryPO {

    private String categoryName;
    private ArrayList<CommodityCategoryPO> subCategories;
    private ArrayList<CommodityPO> subCommodities;
    private boolean isLowestLeverCategory;

    public CommodityCategoryPO(String categoryName, ArrayList<CommodityCategoryPO> subCategories, ArrayList<CommodityPO> subCommodities, boolean isLowestLeverCategory) {
        this.categoryName = categoryName;
        this.subCategories = subCategories;
        this.subCommodities = subCommodities;
        this.isLowestLeverCategory = isLowestLeverCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<CommodityCategoryPO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<CommodityCategoryPO> subCategories) {
        this.subCategories = subCategories;
    }

    public ArrayList<CommodityPO> getSubCommodities() {
        return subCommodities;
    }

    public void setSubCommodities(ArrayList<CommodityPO> subCommodities) {
        this.subCommodities = subCommodities;
    }

    public boolean isLowestLeverCategory() {
        return isLowestLeverCategory;
    }

    public void setLowestLeverCategory(boolean lowestLeverCategory) {
        isLowestLeverCategory = lowestLeverCategory;
    }
}
