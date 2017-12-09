package nju.lighting.bl.commoditybl;

import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品全部信息组成的商品树
 */
public class CommodityCategoriesTree {
    private static final String ROOT_NAME = "root";
    private CommodityCategory root;

    private CommodityCategoriesTree() {
        root = new CommodityCategory(null, -1, ROOT_NAME, false);
    }

    public CommodityCategoriesTreeVO toVO() {
        CommodityCategoryVO rootVO = new CommodityCategoryVO(null, root.getCategoryID(), root.getCategoryName(), root.isLeaf());
        return new CommodityCategoriesTreeVO(root.toVO(rootVO));
    }

    public static class TreeBuilder {
        private CommodityCategoriesTree commodityCategoriesTree;
        private List<CommodityCategoryPO> categoryPOList;

        public TreeBuilder(List<CommodityCategoryPO> categoryPOList) {
            this.categoryPOList = categoryPOList;
        }

        public CommodityCategoriesTree build() {
            buildTree();
            return commodityCategoriesTree;
        }

        private void buildTree() {
            commodityCategoriesTree = new CommodityCategoriesTree();
            if (categoryPOList == null)
                return;
            buildTree(commodityCategoriesTree.root);
        }

        private void buildTree(CommodityCategory node) {
            // Find children
            List<CommodityCategory> children = categoryPOList.stream()
                    .filter(categoryPO -> categoryPO.getUpperCategoryId() == node.getCategoryID())
                    .map(categoryPO -> new CommodityCategory(node, categoryPO.getId(), categoryPO.getName(), false))
                    .collect(Collectors.toList());

            // Check and set
            if (children.isEmpty()) {
                node.setLeaf(true);
                return;
            }
            node.setChildren(children);

            // Recursive step to build tree
            children.forEach(this::buildTree);
        }
    }
}
