package nju.lighting.presentation.mainui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/23.
 * Description
 *
 * @author 陈俊宇
 */
public class GeneralManagerController implements Initializable {


    public  VBox root;
    public  Pane up;
    public  Scene scene;
    public  Pane[] bottoms;

    @FXML
    private Button promotionManageBtn;

    @FXML
    private Button BusinessConditionBtn;

    @FXML
    private Button BusinessHistoryBtn;

    @FXML
    private Button SalesConditionBtn;

    @FXML
    private Button logBtn;

    @FXML
    private Button approvalBtn;

    Button[] buttons;
    String[] urls;


    @FXML
    void jumpTo(int index) {
        Pane bottom;
        String url=urls[index];
        bottom=bottoms[index];

        if(bottom==null){
            try {
                bottom = FXMLLoader.load(getClass().getResource(url));
            } catch (IOException e) {
                e.printStackTrace();
            }

            bottoms[index]=bottom;
        }


        if (root.getChildren().size()>1){
            root.getChildren().remove(1);
        }

        root.getChildren().add(bottom);
        MainUIController.setScene(scene);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[] {approvalBtn, promotionManageBtn, BusinessConditionBtn, SalesConditionBtn, logBtn};
        urls =new String[] {"../approvalui/Approval.fxml", "../promotionui/Promotion.fxml",
                "../documentui/BusinessConditionTable.fxml", "../documentui/SaleConditionTable.fxml",
                "../logui/log.fxml"};

        bottoms=new Pane[5];
        root=new VBox();
        for (int i=0;i<5;i++) {
            int index=i;
            buttons[i].setOnAction(e->{
                jumpTo(index);
            });
        }

    }
}
