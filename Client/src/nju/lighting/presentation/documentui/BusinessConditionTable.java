package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nju.lighting.presentation.mainui.Client;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created on 2017/12/29.
 * Description
 *
 * @author 陈俊宇
 */
public class BusinessConditionTable  implements Initializable{


    @FXML
    JFXButton exportBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exportBtn.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择保存路径");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files", "*.xls"));
            fileChooser.showSaveDialog(new Stage());
        });
    }
}
