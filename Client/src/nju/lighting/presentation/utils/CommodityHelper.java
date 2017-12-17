package nju.lighting.presentation.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nju.lighting.presentation.commodityui.CommodityPicker;
import nju.lighting.presentation.documentui.GiftListEditable;

import java.io.IOException;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityHelper {
    public static void addCommodity(GiftListEditable giftsController) throws IOException {
        FXMLLoader loader = new FXMLLoader(CommodityPicker.class.getResource("commodityPicker.fxml"));
        AnchorPane commodityPicker = loader.load();
        CommodityPicker picker = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(commodityPicker));
        picker.setStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        if (!picker.isCanceled()) {

            GiftListEditable.giftsVO.addAll(picker.getCommodities());

            giftsController.refresh();
        }
    }
}
