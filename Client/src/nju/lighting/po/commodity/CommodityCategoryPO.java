package nju.lighting.po.commodity;

import java.io.Serializable;

public class CommodityCategoryPO implements Serializable {

    private static final long serialVersionUID = 6841205188085709051L;


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
