package nju.lighting.vo.commodity;

import nju.lighting.blservice.commodityblservice.CommodityBLService;

import java.util.List;

public class CommodityCategoryVO {

    private final CommodityCategoryVO upperCategory;
    private List<CommodityCategoryVO> children;
    private int id;
    private String name;
    private boolean isLeaf;

    /**
     * This constructor should be used by business logic module, it's used to build the vo tree of categories
     * @param upperCategory parent category
     * @param id if of the category
     * @param name name of the category
     * @param isLeaf whether the category is a leaf node
     */
    public CommodityCategoryVO(CommodityCategoryVO upperCategory, int id, String name, boolean isLeaf) {
        this.upperCategory = upperCategory;
        this.id = id;
        this.name = name;
        this.isLeaf = isLeaf;
    }

    /**
     * Get parent category's path. If current category is already a child of root node,
     * this method will return an empty string.
     * @return path of parent category
     */
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

    /**
     * This constructor should be used by presentation module
     * @param upperCategory parent category of the category you want to add
     * @param name name of the new category
     */
    public CommodityCategoryVO(CommodityCategoryVO upperCategory, String name) {
        this.upperCategory = upperCategory;
        this.name = name;
    }

    /**
     * Get current category's path. The path is generated by recursion,
     * and the category with id -1 denote the root node.
     * @return path of this category
     */
    public String getPath() {
        return getParentPath() + id;
    }

    public CommodityCategoryVO findChild(int id) {
        return children.stream()
                .filter(child -> child.id == id)
                .findAny().orElse(null);
    }

    public CommodityCategoryVO getUpperCategory() {
        return upperCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CommodityCategoryVO> getChildren() {
        return children;
    }

    public void setChildren(List<CommodityCategoryVO> children) {
        this.children = children;
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
}
