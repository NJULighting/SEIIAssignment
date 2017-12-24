package nju.lighting.bl.commoditybl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品全部信息组成的商品树
 */
class CommodityCategoriesTree {
    private static final String ROOT_NAME = "商品分类";
    private CommodityCategory root;

    private CommodityCategoriesTree() {
        root = new CommodityCategory(null, -1, ROOT_NAME, false);
    }

    public CommodityCategoriesTreeVO toVO() {
        return new CommodityCategoriesTreeVO(root.toVO(null));
    }

    /**
     * Find corresponding category and check whether it's a leaf.
     * If the category doesn't exists or it's not a leaf, it will return false.
     * Otherwise, it will return true.
     * @param path path of the category
     * @return <tt>true</tt> if it's not null and is a leaf, <tt>false</tt> other wise
     */
    boolean isLeaf(String path) {
        CommodityCategory target = get(path);
        return target != null && target.isLeaf;
    }

    boolean contains(String path) {
        return get(path) != null;
    }

    CommodityCategory get(String path) {
        if (path.isEmpty())
            return root;

        String[] categories = path.split(CommodityBLService.SEPARATOR);
        CommodityCategory curr = root;
        for (String category : categories) {
            int categoryID = Integer.parseInt(category);
            curr = curr.findChild(categoryID);
            if (curr == null)
                break;
        }

        return curr;
    }


    static class TreeBuilder {
        private CommodityCategoriesTree commodityCategoriesTree;
        private List<CommodityCategoryPO> categoryPOList;

        TreeBuilder(List<CommodityCategoryPO> categoryPOList) {
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
                    .filter(categoryPO -> categoryPO.getUpperCategoryId() == node.categoryID)
                    .map(categoryPO -> new CommodityCategory(node, categoryPO.getId(), categoryPO.getName(), false))
                    .collect(Collectors.toList());

            // Check and set
            if (children.isEmpty()) {
                node.isLeaf = true;
                return;
            }
            node.children = children;

            // Recursive step to build tree
            children.forEach(this::buildTree);
        }
    }

    static class CommodityCategory {
        // Members of categories
        private final CommodityCategory parent;
        private final int categoryID;
        private List<CommodityCategory> children;
        private String categoryName;
        private boolean isLeaf;

        CommodityCategory(CommodityCategory parent, int categoryID, String categoryName, boolean isLeaf) {
            this.parent = parent;
            this.categoryID = categoryID;
            this.categoryName = categoryName;
            this.isLeaf = isLeaf;
        }

        CommodityCategoryVO toVO(CommodityCategoryVO parentVO) {
            CommodityCategoryVO curr = new CommodityCategoryVO(parentVO, categoryID, categoryName, isLeaf);
            if (isLeaf)
                return curr;

            List<CommodityCategoryVO> voChildren = children.stream()
                    .map(commodityCategory -> commodityCategory.toVO(curr))
                    .collect(Collectors.toList());
            curr.setChildren(voChildren);

            return curr;
        }

        CommodityCategoryPO toPO() {
            return new CommodityCategoryPO(categoryID, categoryName, parent.categoryID);
        }

        void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        /**
         * Find child category of the id you passed.
         * @param categoryID id of the category
         * @return <tt>CommodityCategory</tt> if find the child, <tt>null</tt> otherwise
         */
        CommodityCategory findChild(int categoryID) {
            return children.stream()
                    .filter(child -> child.categoryID == categoryID)
                    .findAny().orElse(null);
        }
    }
}
