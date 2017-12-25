package nju.lighting.presentation.commodityui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import nju.lighting.bl.commoditybl.CommodityBLService_Stub;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.vo.commodity.*;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/25.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityCategory implements Initializable {
    CommodityBLService commodityBLService=new CommodityBLService_Stub();
    private  CommodityCategoriesTreeVO categoriesTreeVO;
    private StackPane stackPane;
    @FXML
    TreeTableView<CommodityCategoryItem> commodityTreeTableView;

    @FXML
    TreeTableColumn<CommodityCategoryItem,String> name;

    @FXML
    TreeTableColumn<CommodityCategoryItem,String> id;

    @FXML
    TreeTableColumn<CommodityCategoryItem,String> modelNumber;

    @FXML
    TreeTableColumn<CommodityCategoryItem,Integer> repCount;

    @FXML
    TreeTableColumn<CommodityCategoryItem,Double> inPrice;

    @FXML
    TreeTableColumn<CommodityCategoryItem,Double> sellPrice;

    @FXML
    TreeTableColumn<CommodityCategoryItem,Double> recentInPrice;

    @FXML
    TreeTableColumn<CommodityCategoryItem,Double> recentSellPrice;

    TreeItem root;

    void buildTree(TreeItem root, CommodityCategoryVO commodityCategoryVO) {
        List<CommodityCategoryVO> children = commodityCategoryVO.getChildren();

        for (CommodityCategoryVO commodityCategory : children) {

            TreeItem item = new TreeItem(new CommodityCategoryItem(commodityCategory));


            item.setExpanded(true);

            root.getChildren().add(item);
            //如果分类 不为 叶节点，则递归建树
            if (!commodityCategory.isLeaf()) {
                buildTree(item, commodityCategory);
            }

            //如果分类为叶节点则在该叶节点下增加他所有的商品
            else {
                List<CommodityItemVO> commodities = commodityBLService.findCommodityByCategory(commodityCategory.getId());

                for (CommodityItemVO commodity : commodities) {
                    TreeItem leaf = new TreeItem(new  CommodityCategoryItem(commodity));
                    item.getChildren().add(leaf);

                }
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriesTreeVO = commodityBLService.getCommodityCategoriesTreeVO();
        root = new TreeItem(new CommodityCategoryItem(categoriesTreeVO.getRoot()));
        root.setExpanded(true);
        buildTree(root, categoriesTreeVO.getRoot());

        commodityTreeTableView.setRoot(root);

        name.setCellValueFactory(cellData ->
        cellData.getValue().getValue().nameProperty());
        id.setCellValueFactory(cellData ->
        cellData.getValue().getValue().idProperty());
        modelNumber.setCellValueFactory(cellData->
        cellData.getValue().getValue().modelNumberProperty());
        repCount.setCellValueFactory(cellData ->
        cellData.getValue().getValue().repCountProperty().asObject());
        inPrice.setCellValueFactory(cellData->
        cellData.getValue().getValue().inPriceProperty().asObject());
        sellPrice.setCellValueFactory(cellData->
        cellData.getValue().getValue().sellPriceProperty().asObject());
        recentInPrice.setCellValueFactory(cellData->
                cellData.getValue().getValue().recentInPriceProperty().asObject());
        recentSellPrice.setCellValueFactory(cellData->
                cellData.getValue().getValue().recentSellPriceProperty().asObject());


        id.setCellFactory(p-> new CommodityDetailTreeCell());
        modelNumber.setCellFactory(p-> new CommodityDetailTreeCell());
        repCount.setCellFactory(p-> new CommodityDetailTreeCell());
        inPrice.setCellFactory(p-> new CommodityDetailTreeCell());
        sellPrice.setCellFactory(p-> new CommodityDetailTreeCell());
        recentInPrice.setCellFactory(p-> new CommodityDetailTreeCell());
        recentSellPrice.setCellFactory(p-> new CommodityDetailTreeCell());

        commodityTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public StackPane getStackPane() {
        return stackPane;
    }

    void setEditable(){
        name.setCellFactory(new Callback<TreeTableColumn<CommodityCategoryItem, String>, TreeTableCell<CommodityCategoryItem, String>>() {
            @Override
            public TreeTableCell<CommodityCategoryItem, String> call(TreeTableColumn<CommodityCategoryItem, String> param) {
                return new MyTreeCell(stackPane);
            }
        });

        commodityTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public List<BasicCommodityItemVO> getSelectedCommodities(){
        return commodityTreeTableView.getSelectionModel().getSelectedItems().stream()
                .filter(x-> x.getValue().getItem().getClass().equals(CommodityItemVO.class))
                .map(x ->((CommodityItemVO)x.getValue().getItem()).toBasicCommodityItem())
                .collect(Collectors.toList());
    }
}
