package nju.lighting.presentation.mainui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

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


    public VBox root;
    public Pane up;
    public Scene scene;
    public Pane[] bottoms;
    Button[] buttons;
    String[] urls;
    final Button[]  currentBtn= new Button[1];
    final Double MISS_OPACITY=0.45;

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

    @FXML
    private Button closeBtn;

    @FXML
    private Button miniBtn;
    private double XOffset;
    private double YOffSet;

    //关闭程序
    @FXML
    void close() {
        Platform.exit();
        System.out.println("close");
    }

    //窗口最小化
    @FXML
    void mini() {
        MainUIController.primaryStage.setIconified(true);
    }

    //界面跳转
    @FXML
    void jumpTo(int index) {
        Pane bottom;
        String url = urls[index];
        bottom = bottoms[index];

        //设置下半部分界面
        if (bottom == null) {
            try {
                bottom = FXMLLoader.load(getClass().getResource(url));
            } catch (IOException e) {
                e.printStackTrace();
            }

            bottoms[index] = bottom;
        }


        if (root.getChildren().size() > 1) {
            root.getChildren().remove(1);
        }

        root.getChildren().add(bottom);


        //高光
        if(currentBtn[0]!=null){
            currentBtn[0].setOpacity(MISS_OPACITY);
        }
        else {
            buttons[index].setOpacity(1);
        }
        currentBtn[0] = buttons[index];
    }

    @FXML
    public void mouseEnter(MouseEvent event) {
        ((Button) event.getSource()).setOpacity(1);
    }

    @FXML
    public void mouseExit(MouseEvent event) {
        if(currentBtn[0]!=(Button) event.getSource())
        ((Button) event.getSource()).setOpacity(MISS_OPACITY);
    }

    @FXML
    public void getOffSet(MouseEvent event){
        event.consume();
        XOffset=event.getSceneX();
        YOffSet=event.getSceneY();
    }

    @FXML
    public void Drag(MouseEvent event){
        event.consume();
        MainUIController.primaryStage.setX(event.getScreenX() - XOffset);
        MainUIController.primaryStage.setY(event.getScreenY() - YOffSet);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buttons = new Button[]{approvalBtn, promotionManageBtn, BusinessConditionBtn, SalesConditionBtn, logBtn,
                miniBtn, closeBtn};
        urls = new String[]{"../approvalui/Approval.fxml", "../promotionui/Promotion.fxml",
                "../documentui/BusinessConditionTable.fxml", "../documentui/SaleConditionTable.fxml",
                "../logui/log.fxml"};
        final int MAIN_BUTTON_SIZE = 5;
        bottoms = new Pane[5];
        root = new VBox();

        for (int i = 0; i < buttons.length; i++) {

            //界面跳转
            if (i < MAIN_BUTTON_SIZE) {
                int index = i;
                buttons[i].setOpacity(MISS_OPACITY);

                buttons[i].setOnAction(e -> {

                    jumpTo(index);
                    MainUIController.setScene(scene);


                });
            }

            //鼠标经过按钮时高光
            buttons[i].setOnMouseEntered(e -> {
                mouseEnter(e);
            });
            buttons[i].setOnMouseExited(e -> {
                mouseExit(e);
            });

        }


    }
}
