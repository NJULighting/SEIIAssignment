package nju.lighting.presentation.commodityui;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import nju.lighting.blservice.commodityblservice.CommodityBLService;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public abstract class Dialog {

    TreeItem treeItem;
    JFXDialog dialog;
    CommodityBLService commodityBLService;
    StackPane stackPane;

    public void setTreeItem(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void setCommodityBLService(CommodityBLService commodityBLService) {
        this.commodityBLService = commodityBLService;
    }



}
