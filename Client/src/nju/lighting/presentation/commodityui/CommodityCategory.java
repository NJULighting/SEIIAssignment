package nju.lighting.presentation.commodityui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.CommodityBLServiceFactory;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/25.
 * Description 商品管理的主要部分 即界面中间的树表视图的控制类
 * 树表视图中左侧 的树节点使用CategoryCell, 右面的表视图使用
 * @author 陈俊宇
 */
public class CommodityCategory implements Initializable {
    private CommodityBLService blService = CommodityBLServiceFactory.getCommodityBLService();
    private SimpleStringProperty keyWord = new SimpleStringProperty();
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
    private CommodityCategoriesTreeVO categoriesTreeVO;
    private StackPane stackPane;
    private TreeItem root;

    @FXML
    private void search() {

        if (searchText.getText().length() == 0) {
            refresh();
        } else {
            root.getChildren().clear();
            root.setValue(new CommodityCategoryItem("搜索结果"));
            root.getChildren().addAll(blService.searchCommodity(searchText.getText()).stream()
                    .map(x -> new TreeItem(new CommodityCategoryItem(x)))
                    .collect(Collectors.toList()));
        }

    }

    @FXML
    void refresh() {
        root.setValue(new CommodityCategoryItem(blService.getCommodityCategoriesTreeVO().getRoot()));
        root.setExpanded(true);
        root.getChildren().clear();
        buildTree(root, categoriesTreeVO.getRoot());

    }

    private void buildTree(TreeItem root, CommodityCategoryVO commodityCategoryVO) {
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
        searchText.textProperty().addListener(c -> {
            if (searchText.getText().length() == 0)
                refresh();
        });


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

        name.setCellFactory(c -> {
            return new TreeTableCell<CommodityCategoryItem, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item);
                        if (getTreeTableRow().getTreeItem() != null
                                && !getTreeTableRow().getTreeItem().getValue().getItem().getClass().equals(CommodityCategoryVO.class)) {
                            MenuItem predicate = new MenuItem("预测趋势");
                            setContextMenu(new ContextMenu(predicate));
                            predicate.setOnAction(e -> {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Predicate.fxml"));
                                try {
                                    DialogHelper.addDialog(loader.load(), MainUI.getStackPane());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                Predicate controller = loader.getController();
                                controller.init(((CommodityItemVO) getTreeTableRow().getTreeItem().getValue().getItem()));
                            });
                        }
                    }
                }
            };
        });
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

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    void setEditable() {
        name.setCellFactory(new Callback<TreeTableColumn<CommodityCategoryItem, String>, TreeTableCell<CommodityCategoryItem, String>>() {
            @Override
            public TreeTableCell<CommodityCategoryItem, String> call(TreeTableColumn<CommodityCategoryItem, String> param) {
                return new CategoryTreeCell(stackPane, keyWord, blService);
            }
        });

        commodityTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    List<BasicCommodityItemVO> getSelectedCommodities() {
        return commodityTreeTableView.getSelectionModel().getSelectedItems().stream()
                .filter(x -> x.getValue().getItem().getClass().equals(CommodityItemVO.class))
                .map(x -> ((CommodityItemVO) x.getValue().getItem()).toBasicCommodityItem())
                .collect(Collectors.toList());
    }

    void setSingle() {
        commodityTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
