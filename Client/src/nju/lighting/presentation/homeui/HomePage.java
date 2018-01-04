package nju.lighting.presentation.homeui;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.factory.DocBLServiceFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2018/1/1.
 * Description
 *
 * @author 陈俊宇
 */
public class HomePage  implements Initializable{
    @FXML
    JFXListView listView;

    DocBLService blService= DocBLServiceFactory.getDocBLService();
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
