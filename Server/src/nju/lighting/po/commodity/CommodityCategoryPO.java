package nju.lighting.po.commodity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COMMODITY_CATEGORY")
public class CommodityCategoryPO implements Serializable {

    private static final long serialVersionUID = 6841205188085709051L;
    private int id;
    private String name;
    private Integer upperCategoryId = -1;

    public CommodityCategoryPO() {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id);
        builder.append(" ");
        builder.append(name);
        builder.append(" ");
        builder.append(upperCategoryId);
        return builder.toString();
    }

    public CommodityCategoryPO(String name, int upperCategoryId) {
        this.name = name;
        this.upperCategoryId = upperCategoryId;
    }

    public CommodityCategoryPO(int id, String name, int upperCategoryId) {
        this.id = id;
        this.name = name;
        this.upperCategoryId = upperCategoryId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    public Integer getUpperCategoryId() {
        return upperCategoryId;
    }

    public void setUpperCategoryId(Integer upperCategoryId) {
        this.upperCategoryId = upperCategoryId;
    }
}
