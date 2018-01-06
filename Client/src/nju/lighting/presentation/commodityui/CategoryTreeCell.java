package nju.lighting.presentation.commodityui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.builder.commodity.CommodityBuildInfo;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.Result;
import shared.ResultMessage;
import shared.TwoTuple;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class CategoryTreeCell extends TreeTableCell<CommodityCategoryItem, String> {
    CommodityBLService commodityBLService;
    FXMLLoader loader;
    StackPane stackPane;
    MenuItem selectedMenuItem;
    Dialog dialogController;
    MenuItem addCommodity = new MenuItem("添加商品");
    MenuItem addCategory = new MenuItem("添加分类");
    MenuItem refactorCategory = new MenuItem("修改分类名称");
    MenuItem deleteCategory = new MenuItem("删除分类");
    MenuItem refactorCommodity = new MenuItem("修改商品信息");
    MenuItem deleteCommodity = new MenuItem("删除商品");
    MenuItem predicate = new MenuItem("趋势预测");
    HashMap<MenuItem, String> hashMap = new HashMap<>();
    StringProperty keyWord;
    private ContextMenu categoryMenu = new ContextMenu();
    private ContextMenu commodityMenu = new ContextMenu();


    public CategoryTreeCell(StackPane stackPane, StringProperty keyWord, CommodityBLService commodityBLService) {
        this.stackPane = stackPane;
        this.keyWord = keyWord;
        this.commodityBLService = commodityBLService;

        categoryMenu.getItems().addAll(addCommodity, addCategory, refactorCategory, deleteCategory);

        commodityMenu.getItems().addAll(refactorCommodity, deleteCommodity, predicate);


        hashMap.put(addCommodity, "AddCommodity.fxml");
        hashMap.put(addCategory, "AddCategoryDialog.fxml");
        hashMap.put(deleteCategory, "Delete.fxml");
        hashMap.put(deleteCommodity, "Delete.fxml");

        predicate.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Predicate.fxml"));
            try {
                DialogHelper.addDialog((Node) loader.load(), stackPane);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Predicate controller = loader.getController();
            controller.init(((CommodityItemVO) getTreeTableItem().getValue().getItem()));
        });
        addCategory.setOnAction(event -> {
            selectedMenuItem = addCategory;
            JFXDialog dialog = createDialog();

            JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));

            button.setOnAction(e -> {
                TreeItem<CommodityCategoryItem> upper = getTreeTableItem();
                CommodityCategoryVO upperCategory = ((CommodityCategoryVO) upper.getValue().getItem());

                AddCategoryDialog addCategoryController = (AddCategoryDialog) dialogController;

                CommodityCategoryVO categoryVO = new CommodityCategoryVO(upperCategory, addCategoryController.getText());
                TwoTuple<ResultMessage, Integer> res = commodityBLService.addCategory(categoryVO);
                ResultMessage resultMessage = res.t;
                if (resultMessage == ResultMessage.SUCCESS) {
                    categoryVO.setId(res.r);
                    upper.getChildren().add(new TreeItem<CommodityCategoryItem>(new CommodityCategoryItem(categoryVO)));
                    dialog.close();
                } else {
                    DialogHelper.dialog("添加商品分类",resultMessage, stackPane);
                }

            });

        });

        refactorCategory.setOnAction(event -> {

            selectedMenuItem = addCategory;
            JFXDialog dialog = createDialog();
            JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));
            AddCategoryDialog addCategoryDialog = (AddCategoryDialog) dialogController;
            addCategoryDialog.init(getItem());

            button.setOnAction(e -> {
                TreeItem<CommodityCategoryItem> treeItem = getTreeTableItem();
                CommodityCategoryVO category = (CommodityCategoryVO) treeItem.getValue().getItem();
                category.setName(addCategoryDialog.getText());
                ResultMessage resultMessage = commodityBLService.changeCategoryName(category);
                if (resultMessage.equals(ResultMessage.SUCCESS)) {
                    dialog.close();
                    setText(category.getName());
                } else
                    DialogHelper.dialog("修改商品名称",resultMessage, stackPane);


            });


        });

        deleteCategory.setOnAction(deleteEventHandle(deleteCategory));

        deleteCommodity.setOnAction(deleteEventHandle(deleteCommodity));

        addCommodity.setOnAction(event -> {
            selectedMenuItem = addCommodity;
            JFXDialog dialog = createDialog();
            JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));

            AddCommodity addCommodityController = ((AddCommodity) dialogController);

            button.setOnAction(e -> {
                CommodityBuildInfo.CommodityBuilder builder = addCommodityController
                        .getCommodityItem(((CommodityCategoryVO) getTreeTableItem().getValue().getItem()));

                if (builder != null) {
                    Result<CommodityItemVO> result = commodityBLService.addCommodity(builder);
                    if (result.hasValue()) {
                        getTreeTableItem().getChildren().add(
                                new TreeItem<>(new CommodityCategoryItem(result.getValue())));
                        dialog.close();
                    } else
                        DialogHelper.dialog("添加商品",result.getResultMessage(), stackPane);
                }
            });
        });

        refactorCommodity.setOnAction(event -> {
            selectedMenuItem = addCommodity;
            JFXDialog dialog = createDialog();
            JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));
            CommodityItemVO initialization = (CommodityItemVO) getTreeTableItem().getValue().getItem();
            AddCommodity addCommodityController = ((AddCommodity) dialogController);
            addCommodityController.init(initialization);

            button.setOnAction(e -> {
                if (addCommodityController.verify()) {
                    CommodityItemVO commodityItemVO = addCommodityController.getCommodityItem();
                    commodityItemVO.setId(initialization.getId());
                    commodityItemVO.setDateOfProduction(initialization.getDateOfProduction());

                    ResultMessage resultMessage = commodityBLService
                            .modifyCommodity(commodityItemVO);
                    if (resultMessage.equals(ResultMessage.SUCCESS)) {
                        getTreeTableItem().setValue(new CommodityCategoryItem(commodityItemVO));

                        //commodityCategory.showSelectedCommodity(commodityItemVO);

                        dialog.close();

                    } else
                        DialogHelper.dialog("修改商品信息",resultMessage, stackPane);
                }


            });


        });


    }

    EventHandler deleteEventHandle(MenuItem deleteCategory) {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                selectedMenuItem = deleteCategory;
                JFXDialog dialog = createDialog();
                Delete deleteController = (Delete) dialogController;

                delete(deleteController, dialog);
            }
        };
    }

    void delete(Delete deleteController, JFXDialog dialog) {
        CommodityCategoryItem categoryItem = getTreeTableItem().getValue();
        boolean isCommodity = true;
        if (categoryItem.getItem().getClass().equals(CommodityCategoryVO.class))
            isCommodity = false;
        if (isCommodity)
            deleteController.setText("你确定要删除商品\n " + categoryItem.getItem().getName() + "?");
        else
            deleteController.setText("你确定要删除商品分类\n" + categoryItem.getItem().getName() + "?");

        JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));
        boolean finalIsCommodity = isCommodity;
        button.setOnAction(e -> {
                    ResultMessage resultMessage;
                    if (finalIsCommodity)
                        resultMessage = commodityBLService.deleteCommodity((categoryItem).getId());
                    else
                        resultMessage = commodityBLService
                                .deleteCategory(((CommodityCategoryVO) categoryItem.getItem()).getId());

                    if (resultMessage.equals(ResultMessage.SUCCESS)) {
                        getTreeTableItem().getParent().getChildren().remove(getTreeTableItem());
                        dialog.close();
                    } else
                        DialogHelper.dialog("删除",resultMessage, stackPane);
                }
        );
    }

    JFXDialog createDialog() {

        JFXDialog dialog = new JFXDialog(null, null, JFXDialog.DialogTransition.CENTER);
        dialog.setDialogContainer(stackPane);
        JFXDialogLayout layout = new JFXDialogLayout();

        JFXButton button = new JFXButton("确认");
        JFXButton cancel = new JFXButton("取消");

        cancel.setOnAction(e -> {
            dialog.close();
        });

        layout.setActions(button, cancel);

        loader = new FXMLLoader(getClass().getResource(hashMap.get(selectedMenuItem)));
        try {
            layout.setBody((AnchorPane) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogController = loader.getController();
        dialogController.setDialog(dialog);
        dialogController.setTreeItem(getTreeTableItem());
        dialogController.setStackPane(stackPane);

        dialog.setContent(layout);

        dialog.show();
        return dialog;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            String key = keyWord.getValue();
            String itemStr = item.toString();
            if (key != null && itemStr.contains(key)) {
                setGraphic(TableViewHelper.getHighlightBox(itemStr, key));
                setText(null);
            } else {
                setGraphic(null);
                setText(itemStr);
            }

            if (getTreeTableItem().getValue().getItem().getClass().equals(CommodityCategoryVO.class)) {

                setContextMenu(categoryMenu);
                TreeItem<CommodityCategoryItem> treeItem = getTreeTableItem();



                //如果该分类不是叶节点，添加商品就不可用
                if (treeItem.getChildren().size() != 0 &&
                        getTreeTableItem().getChildren().get(0).getValue().getItem().getClass().equals(CommodityCategoryVO.class)) {
                    addCommodity.setDisable(true);
                } else addCommodity.setDisable(false);
                if (treeItem.getChildren().size() != 0 &&
                        getTreeTableItem().getChildren().get(0).getValue().getItem().getClass().equals(CommodityItemVO.class)) {
                    addCategory.setDisable(true);
                } else
                    addCategory.setDisable(false);
                //如果是根节点 或者 不为叶节点，删除分类不可用
                if (treeItem.getParent() == null||treeItem.getChildren().size()!=0) {
                    deleteCategory.setDisable(true);
                } else
                    deleteCategory.setDisable(false);
            } else
                setContextMenu(commodityMenu);
        }


    }

    TreeItem<CommodityCategoryItem> getTreeTableItem() {
        return getTreeTableView().getTreeItem(getIndex());
    }

}
