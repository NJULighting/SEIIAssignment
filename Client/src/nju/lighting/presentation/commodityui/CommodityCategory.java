package nju.lighting.presentation.commodityui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import nju.lighting.bl.commoditybl.CommodityBLService_Stub;
import nju.lighting.bl.commoditybl.CommodityController;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.vo.commodity.*;

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
    CommodityBLService blService = new CommodityBLService_Stub();
    private CommodityCategoriesTreeVO categoriesTreeVO;
    private StackPane stackPane;
    SimpleStringProperty keyWord=new SimpleStringProperty();
    @FXML
    TreeTableView<CommodityCategoryItem> commodityTreeTableView;

    @FXML
    TreeTableColumn<CommodityCategoryItem, String> name;

    @FXML
    TreeTableColumn<CommodityCategoryItem, String> id;

    @FXML
    TreeTableColumn<CommodityCategoryItem, String> modelNumber;

    @FXML
    TreeTableColumn<CommodityCategoryItem, Integer> repCount;

    @FXML
    TreeTableColumn<CommodityCategoryItem, Double> inPrice;

    @FXML
    TreeTableColumn<CommodityCategoryItem, Double> sellPrice;

    @FXML
    TreeTableColumn<CommodityCategoryItem, Double> recentInPrice;

    @FXML
    TreeTableColumn<CommodityCategoryItem, Double> recentSellPrice;

    @FXML
    TextField searchText;


    TreeItem root;


    void search() {

        if (searchText.getText().length()==0){
            refresh();
        }

        else {
            root.getChildren().clear();
            root.setValue(new CommodityCategoryItem("搜索结果"));
            root.getChildren().addAll(blService.searchCommodity(searchText.getText()).stream()
                    .map(x -> new TreeItem(new CommodityCategoryItem(x)))
                    .collect(Collectors.toList()));
        }

    }

    @FXML
    void refresh() {
        root.setValue(new CommodityCategoryItem(categoriesTreeVO.getRoot()));
        root.setExpanded(true);
        root.getChildren().clear();
        buildTree(root, categoriesTreeVO.getRoot());

    }

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
                List<CommodityItemVO> commodities = blService.findCommodityByCategory(commodityCategory.getId());

                for (CommodityItemVO commodity : commodities) {
                    TreeItem leaf = new TreeItem(new CommodityCategoryItem(commodity));
                    item.getChildren().add(leaf);

                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyWord.bind(searchText.textProperty());
        searchText.setOnAction(e -> search());
        categoriesTreeVO = blService.getCommodityCategoriesTreeVO();
        root = new TreeItem();
        refresh();
        commodityTreeTableView.setRoot(root);


        name.setCellValueFactory(cellData ->
                cellData.getValue().getValue().nameProperty());

        id.setCellValueFactory(cellData ->
                cellData.getValue().getValue().idProperty());
        modelNumber.setCellValueFactory(cellData ->
                cellData.getValue().getValue().modelNumberProperty());
        repCount.setCellValueFactory(cellData ->
                cellData.getValue().getValue().repCountProperty().asObject());
        inPrice.setCellValueFactory(cellData ->
                cellData.getValue().getValue().inPriceProperty().asObject());
        sellPrice.setCellValueFactory(cellData ->
                cellData.getValue().getValue().sellPriceProperty().asObject());
        recentInPrice.setCellValueFactory(cellData ->
                cellData.getValue().getValue().recentInPriceProperty().asObject());
        recentSellPrice.setCellValueFactory(cellData ->
                cellData.getValue().getValue().recentSellPriceProperty().asObject());


        id.setCellFactory(p -> new CommodityDetailTreeCell(keyWord));
        modelNumber.setCellFactory(p -> new CommodityDetailTreeCell(keyWord));
        repCount.setCellFactory(p -> new CommodityDetailTreeCell(keyWord));
        inPrice.setCellFactory(p -> new CommodityDetailTreeCell(keyWord));
        sellPrice.setCellFactory(p -> new CommodityDetailTreeCell(keyWord));
        recentInPrice.setCellFactory(p -> new CommodityDetailTreeCell(keyWord));
        recentSellPrice.setCellFactory(p -> new CommodityDetailTreeCell(keyWord));

        commodityTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public StackPane getStackPane() {
        return stackPane;
    }

    void setEditable() {
        name.setCellFactory(new Callback<TreeTableColumn<CommodityCategoryItem, String>, TreeTableCell<CommodityCategoryItem, String>>() {
            @Override
            public TreeTableCell<CommodityCategoryItem, String> call(TreeTableColumn<CommodityCategoryItem, String> param) {
                return new MyTreeCell(stackPane,keyWord,blService);
            }
        });

        commodityTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public List<BasicCommodityItemVO> getSelectedCommodities() {
        return commodityTreeTableView.getSelectionModel().getSelectedItems().stream()
                .filter(x -> x.getValue().getItem().getClass().equals(CommodityItemVO.class))
                .map(x -> ((CommodityItemVO) x.getValue().getItem()).toBasicCommodityItem())
                .collect(Collectors.toList());
    }
}
