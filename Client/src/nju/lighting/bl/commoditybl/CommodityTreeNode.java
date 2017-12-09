package nju.lighting.bl.commoditybl;

import java.util.List;

/**
 * Created on 2017/12/8.
 * Description:
 * @author Liao
 */
public class CommodityTreeNode<T> {
    private List<CommodityTreeNode<T>> children;
    private T element;
    private boolean isLeaf;

    public CommodityTreeNode(T element, boolean isLeaf) {
        this.element = element;
        this.isLeaf = isLeaf;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    T getElement() {
        return element;
    }

    public List<CommodityTreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<CommodityTreeNode<T>> children) {
        this.children = children;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
