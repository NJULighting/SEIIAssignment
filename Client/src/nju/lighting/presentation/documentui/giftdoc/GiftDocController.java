package nju.lighting.presentation.documentui.giftdoc;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class GiftDocController implements Initializable {

    //用于计算滑块的位置
    private double prePosition = 100,endPosition,mainHeight = 560;
    //用于修复开始滑动条不在最上面的bug
    private boolean up = true;

    @FXML
    private JFXSlider slider;

    @FXML
    private TableView giftTableView;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private ScrollPane scrollpaneMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //表格高度*确认*之后，调整滑动面板高度以适应总高度
        double tableHeight = giftTableView.getHeight();
        if(tableHeight>292){
            scrollpane.setPrefHeight(tableHeight);
            mainHeight = mainHeight+tableHeight-292;
            endPosition = mainHeight - 560;
        }

        //滑块控制表格的左右滑动
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov,
                                            Number old_value,Number new_value) -> {
            giftTableView.setLayoutX(-new_value.doubleValue());
        });

        //滑块不会消失在视野中
        scrollpaneMain.vvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.doubleValue()*endPosition>prePosition){
                    slider.setLayoutY(newValue.doubleValue()*endPosition);
                }
                if(up){
                    scrollpaneMain.setVvalue(0);
                    up=false;
                }
                System.out.println(newValue);
            }
        });

    }
}
