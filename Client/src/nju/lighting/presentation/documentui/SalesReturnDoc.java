package nju.lighting.presentation.documentui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/24.
 * Description
 *
 * @author 陈俊宇
 */
public class SalesReturnDoc implements Initializable{
    @FXML
    Pane container;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("SalesDoc.fxml"));

        try {
            container.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }


        SalesDocController controller = loader.getController();
        controller.setReturn();
    }
}
