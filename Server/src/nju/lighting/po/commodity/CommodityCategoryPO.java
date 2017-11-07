package nju.lighting.po.commodity;

import java.util.ArrayList;

public class CommodityCategoryPO {

    private int id;

    private String name;

    private int upperCategoryId = -1;

    public CommodityCategoryPO(int id, String name, int upperCategoryId) {
        this.id = id;
        this.name = name;
        this.upperCategoryId = upperCategoryId;
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

    public int getUpperCategoryId() {
        return upperCategoryId;
    }

    public void setUpperCategoryId(int upperCategoryId) {
        this.upperCategoryId = upperCategoryId;
    }
}
