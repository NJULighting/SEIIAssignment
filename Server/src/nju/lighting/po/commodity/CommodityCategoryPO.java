package nju.lighting.po.commodity;

import javax.persistence.*;

@Entity
@Table(name = "COMMODITY_CATEGORY")
public class CommodityCategoryPO {

    private int id;

    private String name;

    private int upperCategoryId = -1;

    public CommodityCategoryPO() {

    }

    public CommodityCategoryPO(int id, String name, int upperCategoryId) {
        this.id = id;
        this.name = name;
        this.upperCategoryId = upperCategoryId;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "UPPER_CATEGORY_ID")
    public int getUpperCategoryId() {
        return upperCategoryId;
    }

    public void setUpperCategoryId(int upperCategoryId) {
        this.upperCategoryId = upperCategoryId;
    }
}
