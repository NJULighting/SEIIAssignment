package nju.lighting.bl.commoditybl;

import javafx.scene.image.Image;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import shared.CommodityBuildInfo;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.Result;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class CommodityBLService_Stub implements CommodityBLService {

    private List<CommodityItem> items = new ArrayList<>();

    private CommodityCategoriesTree tree;

    public CommodityBLService_Stub() {

        List<CommodityCategoryPO> poList = new ArrayList<>();
        poList.add(new CommodityCategoryPO(1, "Frog", -1));
        poList.add(new CommodityCategoryPO(2, "Excited", 1));
        poList.add(new CommodityCategoryPO(3, "Naive", 2));
        poList.add(new CommodityCategoryPO(4, "Too Young", 1));
        poList.add(new CommodityCategoryPO(5, "Too Simple", 1));
        CommodityCategoriesTree.TreeBuilder builder = new CommodityCategoriesTree.TreeBuilder(poList);
        tree = builder.build();

        CommodityItem com1 = new CommodityItem("01", "01", 3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com2 = new CommodityItem("02", "天才灯炮", 3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com3 = new CommodityItem("03", "小天才灯具", 3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com4 = new CommodityItem("04", "小天才灯炮", 3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com5 = new CommodityItem("05", "大天才灯具", 5, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com6 = new CommodityItem("06", "大天才灯具", 5, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        items.add(com1);
        items.add(com2);
        items.add(com3);
        items.add(com4);
        items.add(com5);
        items.add(com6);
        items.add(com6);
        items.add(com6);
        items.add(com6);
        items.add(com6);
        items.add(com6);
        items.add(com6);
        items.add(com6);
        items.add(com6);
        items.add(com6);


    }

    @Override
    public CommodityCategoriesTreeVO getCommodityCategoriesTreeVO() {
        return tree.toVO();
    }

    @Override
    public Result<CommodityItemVO> addCommodity(CommodityBuildInfo.CommodityBuilder builder) {
        return new Result<CommodityItemVO>(
                ResultMessage.SUCCESS, builder.build().toVO("11")
        );
    }

    @Override
    public List<CommodityItemVO> findCommodityByCategory(int categoryID) {
        return items.stream()
                .filter(commodityItem -> commodityItem.getCategory() == categoryID)
                .map(CommodityItem::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommodityItemVO> findCommodityVOByName(String commodityName) {
        return null;
    }


    @Override
    public List<CommodityItemVO> searchCommodity(String keyword) {
        return findCommodityVOByName(keyword);
    }

    @Override
    public ResultMessage deleteCommodity(String id) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityItemVO commodity) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public Result<CommodityCategoryVO> addCategory(CommodityCategoryVO newCategory) {

        String name = newCategory.getName();

//        if (name.equals("1"))
//            return ResultMessage.DUPLICATE;
//        else if (name.equals("2"))
//            return ResultMessage.FAILURE;
//        else return ResultMessage.SUCCESS;
        newCategory.setId(1);
        return new Result<>(ResultMessage.SUCCESS, newCategory);
    }

    @Override
    public ResultMessage deleteCategory(int id) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage changeCategoryName(CommodityCategoryVO categoryVO) {
        return ResultMessage.SUCCESS;
    }

}
