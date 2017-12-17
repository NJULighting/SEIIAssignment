package nju.lighting.presentation.commodityui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class myTreeCell extends TreeCell<Nameable> {
    CommodityBLService commodityBLService = new CommodityBLService_Stub();//new CommodityBLService() {}
    private ContextMenu categoryMenu = new ContextMenu();
    private ContextMenu commodityMenu = new ContextMenu();
    FXMLLoader loader;
    StackPane stackPane;
    MenuItem selectedMenuItem;
    Dialog dialogController;
    HashMap<MenuItem, String> hashMap = new HashMap<>();


    public myTreeCell(StackPane stackPane) {
        this.stackPane = stackPane;
        MenuItem addCommodity = new MenuItem("添加商品");
        MenuItem addCategory = new MenuItem("添加分类");
        MenuItem refactorName = new MenuItem("修改分类名称");
        MenuItem delete = new MenuItem("删除");
        categoryMenu.getItems().addAll(addCommodity, addCategory, refactorName, delete);

        MenuItem refactor = new MenuItem("修改商品信息");
        commodityMenu.getItems().addAll(refactor, delete);

        hashMap.put(addCommodity, "AddCommodity.fxml");
        hashMap.put(addCategory, "AddCategoryDialog.fxml");

        addCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedMenuItem = addCategory;
                createDialog();
            }
        });

        addCommodity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedMenuItem=addCommodity;
                JFXDialog dialog=createDialog();
                JFXButton button=  new JFXButton("提交");

                AddCommodity addCommodityController=((AddCommodity) dialogController);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (addCommodityController.verify()){
                            CommodityItemVO commodityItemVO=addCommodityController.getCommodityItem();
                            ResultMessage resultMessage= commodityBLService
                                    .addCommodity(commodityItemVO,((CommodityCategoryVO)getTreeItem().getValue()));
                            if (resultMessage.equals(ResultMessage.SUCCESS)){
                                getTreeItem().getChildren().add(new TreeItem<>(commodityItemVO));
                                dialog.close();
                            }

                            else
                                try {
                                    DialogHelper.dialog(resultMessage,stackPane);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        }

                    }
                });
                ( (JFXDialogLayout)dialog .getContent()).setActions( button);

            }
        });

        refactor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedMenuItem=addCommodity;
                JFXDialog dialog=createDialog();
                JFXButton button=  new JFXButton("提交");

                AddCommodity addCommodityController=((AddCommodity) dialogController);
                addCommodityController.init((CommodityItemVO) getTreeItem().getValue());

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (addCommodityController.verify()){
                            CommodityItemVO commodityItemVO=addCommodityController.getCommodityItem();
                            ResultMessage resultMessage= commodityBLService
                                    .modifyCommodity(commodityItemVO);
                            if (resultMessage.equals(ResultMessage.SUCCESS)){
                                getTreeItem().getChildren().add(new TreeItem<>(commodityItemVO));
                                dialog.close();
                            }

                            else
                                try {
                                    DialogHelper.dialog(resultMessage,stackPane);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        }

                    }
                });
                ((JFXDialogLayout)dialog.getContent()).setActions( button);
            }
        });


    }

    JFXDialog createDialog() {

        JFXDialog dialog = new JFXDialog(null, null, JFXDialog.DialogTransition.CENTER);
        dialog.setDialogContainer(stackPane);
        JFXDialogLayout layout = new JFXDialogLayout();

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
            if (item.getClass().equals(CommodityCategoryVO.class))
                setContextMenu(categoryMenu);
            else
                setContextMenu(commodityMenu);
        }
    }

}
