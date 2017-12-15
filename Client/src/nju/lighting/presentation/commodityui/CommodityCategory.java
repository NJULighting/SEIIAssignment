package nju.lighting.presentation.commodityui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import nju.lighting.bl.commoditybl.CommodityBLService_Stub;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.Nameable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/14.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityCategory implements Initializable {
    private static CommodityCategoriesTreeVO categoriesTreeVO;
    static boolean Editable;
    private double leftPadding=50;
    private CommodityBLService commodityBLService;

    public static void setCategoriesTreeVO(CommodityCategoriesTreeVO categoriesTreeVO) {
        CommodityCategory.categoriesTreeVO = categoriesTreeVO;
    }

    @FXML
    TreeView<Nameable> categoryTreeView;
    @FXML
    HBox container;

    TreeItem root;

    void showSelectedCommodity(CommodityItemVO commodity) throws IOException {
        CommodityReadOnly.setCommodity(commodity);
        container.getChildren().clear();
        System.out.println("clicked");
        HBox commodityVBox = FXMLLoader.load(getClass().getResource("CommodityReadOnly.fxml"));
        container.getChildren().add(commodityVBox);
        commodityVBox.setMargin(commodityVBox, new Insets(50, 0, 0, leftPadding));
    }

    public static void setEditable(boolean editable) {
        Editable = editable;
    }


    @FXML
    void leftClicked(MouseEvent event) throws IOException {

        if (event.getButton().equals(MouseButton.PRIMARY)) {
            TreeItem selected = categoryTreeView.getSelectionModel().getSelectedItem();

            if (selected.isLeaf() && selected.getValue().getClass().equals(CommodityItemVO.class)) {
                CommodityItemVO commodity = ((CommodityItemVO) selected.getValue());
                showSelectedCommodity(commodity);
            }
        }

    }

    void buildTree(TreeItem root, CommodityCategoryVO commodityCategoryVO) {
        List<CommodityCategoryVO> children = commodityCategoryVO.getChildren();

        for (CommodityCategoryVO commodityCategory : children) {

            TreeItem item = new TreeItem(commodityCategory);


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
                    TreeItem leaf = new TreeItem(commodity);
                    item.getChildren().add(leaf);

                }
            }
        }
    }

    void initEditable(){
        categoryTreeView.setMinHeight(680);
        leftPadding=300;
        System.out.println("category editable");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commodityBLService = new CommodityBLService_Stub();
        categoriesTreeVO = commodityBLService.getCommodityCategoriesTreeVO();
        root = new TreeItem(categoriesTreeVO.getRoot());
        root.setExpanded(true);
        buildTree(root, categoriesTreeVO.getRoot());

        categoryTreeView.setRoot(root);

        categoryTreeView.setCellFactory(new Callback<TreeView<Nameable>, TreeCell<Nameable>>() {
            @Override
            public TreeCell<Nameable> call(TreeView<Nameable> param) {
                return new TreeCell<Nameable>() {
                    @Override
                    protected void updateItem(Nameable item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }

                };
            }
        });

        if (Editable) {
            initEditable();
        }


    }


}
