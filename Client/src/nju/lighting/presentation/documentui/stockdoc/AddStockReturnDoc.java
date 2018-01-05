package nju.lighting.presentation.documentui.stockdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.documentui.stockdoc.AddStockDoc;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/24.
 * Description
 * @author 陈俊宇
 */
public class AddStockReturnDoc implements Initializable {

    @FXML
    Pane container;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StockDoc.fxml"));

        try {
            container.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }


        AddStockDoc controller = loader.getController();
        controller.setReturn();
    }
}
