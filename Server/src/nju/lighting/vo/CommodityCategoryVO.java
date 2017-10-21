package nju.lighting.vo;

import java.util.ArrayList;

public class CommodityCategoryVO {

    private String categoryName;
    private ArrayList<CommodityCategoryVO> subCategories;
    private ArrayList<CommodityVO> subCommodities;
    private boolean isLowestLeverCategory;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<CommodityCategoryVO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<CommodityCategoryVO> subCategories) {
        this.subCategories = subCategories;
    }

    public ArrayList<CommodityVO> getSubCommodities() {
        return subCommodities;
    }

    public void setSubCommodities(ArrayList<CommodityVO> subCommodities) {
        this.subCommodities = subCommodities;
    }

    public boolean isLowestLeverCategory() {
        return isLowestLeverCategory;
    }

    public void setLowestLeverCategory(boolean lowestLeverCategory) {
        isLowestLeverCategory = lowestLeverCategory;
    }

    public CommodityCategoryVO(String categoryName, ArrayList<CommodityCategoryVO> subCategories, ArrayList<CommodityVO> subCommodities, boolean isLowestLeverCategory) {
        this.categoryName = categoryName;
        this.subCategories = subCategories;
        this.subCommodities = subCommodities;
        this.isLowestLeverCategory = isLowestLeverCategory;
    }
}
