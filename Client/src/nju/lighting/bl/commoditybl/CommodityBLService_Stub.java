package nju.lighting.bl.commoditybl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;

import shared.ICommodityTreeNode;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommodityBLService_Stub implements CommodityBLService {

    private ArrayList<CommodityItem> items = new ArrayList<>();

    private CommodityCategoriesTree tree = new CommodityCategoriesTree(null);

    public CommodityBLService_Stub() {


        int cc1 = new CommodityCategoryItem(null, null,1, "天才");
        int cc2 = new CommodityCategoryItem(null, null,2, "小天才");
        int cc3 = new CommodityCategoryItem(null, null,3, "大天才");
        Integer cc4 = new CommodityCategoryItem(null, null,4, "超级天才");

        CommodityItem com1 = new CommodityItem("01", "天才灯具", cc1, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com2 = new CommodityItem("02", "天才灯炮", cc1, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com3 = new CommodityItem("03", "小天才灯具", cc2, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com4 = new CommodityItem("04", "小天才灯炮", cc2, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com5 = new CommodityItem("05", "大天才灯具", cc3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com6 = new CommodityItem("06", "大天才灯具", cc3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        items.add(com1);
        items.add(com2);
        items.add(com3);
        items.add(com4);
        items.add(com5);
        items.add(com6);

        ArrayList<ICommodityTreeNode> cca1 = new ArrayList<>();
        cca1.add(com1);
        cca1.add(com2);
        ArrayList<ICommodityTreeNode> cca2 = new ArrayList<>();
        cca2.add(com3);
        cca2.add(com4);
        ArrayList<ICommodityTreeNode> cca3 = new ArrayList<>();
        cca3.add(com5);
        cca3.add(com6);
        cc1.setChildren(cca1);
        cc2.setChildren(cca2);
        cc3.setChildren(cca3);

        ArrayList<Integer> node = new ArrayList<>();
        node.add(cc1);
        node.add(cc2);
        node.add(cc3);
        node.add(cc4);

        tree.setRoots(node);
    }

    @Override
    public CommodityCategoriesTreeVO getCommodityCategoriesTreeVO() {
        return tree.toVO();
    }

    @Override
    public ResultMessage addCommodity(CommodityItemVO newCommodity) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<CommodityItemVO> findCommodityVOByName(String commodityName)  {
        ArrayList<CommodityItemVO> itemVOS = new ArrayList<>();
        for (CommodityItem item: items) {
            if (item.getName().equals(commodityName)) {
                itemVOS.add(item.toVO());
            }
        }
        return itemVOS;
    }

    @Override
    public CommodityItemVO findCommodityVOById(String id) {
        for (CommodityItem item: items) {
            if (item.getId().equals(id)) {
                return item.toVO();
            }
        }
        return null;
    }

    @Override
    public ResultMessage deleteCommodity(String id) {
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityItemVO commodity) {
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage addCategory(CommodityCategoryVO newCategory){
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage deleteCategory(int id) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyCategory(CommodityCategoryVO categoryItemVO) {
        return ResultMessage.SUCCESS;
    }
}
