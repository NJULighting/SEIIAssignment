package nju.lighting.presentation.commodityui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import nju.lighting.bl.commoditybl.CommodityBLService_Stub;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.Nameable;
import shared.ResultMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class MyTreeCell extends TreeCell<Nameable> {
    CommodityBLService commodityBLService = new CommodityBLService_Stub();//new CommodityBLService() {}
    private ContextMenu categoryMenu = new ContextMenu();
    private ContextMenu commodityMenu = new ContextMenu();

    FXMLLoader loader;
    StackPane stackPane;
    MenuItem selectedMenuItem;
    Dialog dialogController;
    TreeItem<Nameable> currentItem;
    HashMap<MenuItem, String> hashMap = new HashMap<>();


    public MyTreeCell(CommodityCategory commodityCategory) {


        this.stackPane = commodityCategory.stackPane;
        MenuItem addCommodity = new MenuItem("添加商品");
        MenuItem addCategory = new MenuItem("添加分类");
        MenuItem refactorCategory = new MenuItem("修改分类名称");
        MenuItem deleteCategory = new MenuItem("删除分类");
        categoryMenu.getItems().addAll(addCommodity, addCategory, refactorCategory, deleteCategory);

        MenuItem refactorCommodity = new MenuItem("修改商品信息");
        MenuItem deleteCommodity = new MenuItem("删除商品");

        commodityMenu.getItems().addAll(refactorCommodity, deleteCommodity);


        hashMap.put(addCommodity, "AddCommodity.fxml");
        hashMap.put(addCategory, "AddCategoryDialog.fxml");
        hashMap.put(deleteCategory, "Delete.fxml");
        hashMap.put(deleteCommodity, "Delete.fxml");

        addCategory.setOnAction(event -> {
            selectedMenuItem = addCategory;
            JFXDialog dialog = createDialog();

            JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));

            button.setOnAction(e -> {
                CommodityCategoryVO upperCategory = ((CommodityCategoryVO) getTreeItem().getValue());

                AddCategoryDialog addCategoryController = (AddCategoryDialog) dialogController;

                CommodityCategoryVO categoryVO = new CommodityCategoryVO(upperCategory, addCategoryController.getText());
                ResultMessage resultMessage = commodityBLService.addCategory(categoryVO);
                if (resultMessage == ResultMessage.SUCCESS) {
                    getTreeItem().getChildren().add(new TreeItem<>(categoryVO));
                    dialog.close();
                } else {
                    DialogHelper.dialog(resultMessage, stackPane);
                }

            });

        });

        refactorCategory.setOnAction(event -> {
            selectedMenuItem = addCategory;
            JFXDialog dialog = createDialog();
            JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));
            AddCategoryDialog addCategoryDialog = (AddCategoryDialog) dialogController;
            addCategoryDialog.init(getItem().getName());

            button.setOnAction(e -> {
                CommodityCategoryVO category = (CommodityCategoryVO) getItem();
                category.setName(addCategoryDialog.getText());
                ResultMessage resultMessage = commodityBLService.changeCategoryName(category);
                if (resultMessage.equals(ResultMessage.SUCCESS)) {
                    dialog.close();
                    setText(category.getName());
                } else
                    DialogHelper.dialog(resultMessage, stackPane);


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
                if (addCommodityController.verify()) {
                    CommodityItemVO commodityItemVO = addCommodityController.getCommodityItem();
                    ResultMessage resultMessage = commodityBLService
                            .addCommodity(commodityItemVO, ((CommodityCategoryVO) getTreeItem().getValue()));
                    if (resultMessage.equals(ResultMessage.SUCCESS)) {
                        getTreeItem().getChildren().add(new TreeItem<>(commodityItemVO));
                        dialog.close();
                    } else
                        DialogHelper.dialog(resultMessage, stackPane);
                }


            });


        });

        refactorCommodity.setOnAction(event -> {
            selectedMenuItem = addCommodity;
            JFXDialog dialog = createDialog();
            JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));

            AddCommodity addCommodityController = ((AddCommodity) dialogController);
            addCommodityController.init((CommodityItemVO) getTreeItem().getValue());

            button.setOnAction(e -> {
                if (addCommodityController.verify()) {
                    CommodityItemVO commodityItemVO = addCommodityController.getCommodityItem();
                    commodityItemVO.setId(((CommodityItemVO)getItem()).getId());

                    ResultMessage resultMessage = commodityBLService
                            .modifyCommodity(commodityItemVO);
                    if (resultMessage.equals(ResultMessage.SUCCESS)) {
                        getTreeItem().setValue(commodityItemVO);

                        commodityCategory.showSelectedCommodity(commodityItemVO);

                        dialog.close();

                    } else
                        DialogHelper.dialog(resultMessage, stackPane);
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
        boolean isCommodity = true;
        if (getItem().getClass().equals(CommodityCategoryVO.class))
            isCommodity = false;
        if (isCommodity)
            deleteController.setText("你确定要删除商品\n " + getItem().getName() + "?");
        else
            deleteController.setText("你确定要删除商品分类\n" + getItem().getName() + "?");

        JFXButton button = ((JFXButton) ((JFXDialogLayout) dialog.getContent()).getActions().get(0));
        boolean finalIsCommodity = isCommodity;
        button.setOnAction(e -> {
                    ResultMessage resultMessage;
                    if (finalIsCommodity)
                        resultMessage = commodityBLService.deleteCommodity(((CommodityItemVO) getItem()).getId());
                    else
                        resultMessage = commodityBLService
                                .deleteCategory(((CommodityCategoryVO) getItem()).getId());

                    if (resultMessage.equals(ResultMessage.SUCCESS)) {
                        getTreeItem().getParent().getChildren().remove(getTreeItem());
                        dialog.close();
                    } else
                        DialogHelper.dialog(resultMessage, stackPane);
                }
        );
    }

    //树状图拖拽的功能
//    void setDrag(){
//        setOnDragDetected(e->{
//            currentItem=getTreeItem();
//            System.out.println("detected"+getItem().getName());
//            Dragboard dragboard=startDragAndDrop(TransferMode.ANY);
//            ClipboardContent content= new ClipboardContent();
//
//          //  content.putImage(new Image("/images/close.png"));
//            content.putString(getItem().getName());
//            dragboard.setContent(content);
//            dragboard.setDragView(new Image("/images/close.png"));
//            e.consume();
//        });
//
//        setOnDragEntered(e -> {
//            System.out.println(" Entered ");
//            getTreeView().getSelectionModel().select(getIndex());
//            e.consume();
//        });
//
//        setOnDragDone(e -> {
//
//            System.out.println(" Done at "+getItem().getName());
//            e.consume();
//        });
//        setOnDragDropped(e -> {
//            System.out.println(" Dropped at " +getItem().getName());
//            e.setDropCompleted(true);
//            e.consume();
//        });
//        setOnDragExited(e -> {
//            System.out.println(" Exited ");
//
//            getTreeView().getSelectionModel().clearSelection();
//            e.consume();
//        });
//        setOnDragOver(event -> {
//
//            System.out.println(getItem().getName());
//            event.consume();
//        });
//    }


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
        dialogController.setTreeItem(getTreeItem());
        dialogController.setCommodityBLService(commodityBLService);
        dialogController.setStackPane(stackPane);

        dialog.setContent(layout);

        dialog.show();
        return dialog;
    }

    @Override
    protected void updateItem(Nameable item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            setText(item.getName());
            if (item.getClass().equals(CommodityCategoryVO.class)) {

                setContextMenu(categoryMenu);

                if (getTreeItem().getParent() == null) {

                    getContextMenu().getItems().get(2).setDisable(true);
                    getContextMenu().getItems().get(3).setDisable(true);

                }
            } else
                setContextMenu(commodityMenu);
        }
    }

}
