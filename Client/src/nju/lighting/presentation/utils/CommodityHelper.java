package nju.lighting.presentation.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import nju.lighting.presentation.commodityui.CommodityPicker;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityHelper {
    public static void addCommodity(CommodityList giftsController) throws IOException {
        FXMLLoader loader = new FXMLLoader(CommodityPicker.class.getResource("commodityPicker.fxml"));
        AnchorPane commodityPicker = loader.load();
        CommodityPicker picker = loader.getController();

//        Stage stage = new Stage();
//        stage.setScene(new Scene(commodityPicker));
//        picker.setStage(stage);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.showAndWait();

        if (!picker.isCanceled()) {


            giftsController.getGiftObservableList().addAll(
                    picker.getCommodities().stream()
                            .map(x -> new CommodityItem(x, 1))
                            .collect(Collectors.toList()));

            giftsController.refresh();
        }
    }
}
