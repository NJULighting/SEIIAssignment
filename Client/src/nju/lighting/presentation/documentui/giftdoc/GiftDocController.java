package nju.lighting.presentation.documentui.giftdoc;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nju.lighting.bl.customerbl.CustomerBLService_Stub;
import nju.lighting.bl.userbl.UserBLServie_Stub;
import nju.lighting.bl.userbl.UserController;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.factory.CustomerBLServiceFactory;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GiftDocController implements Initializable {

    CustomerBLService customerBLService = CustomerBLServiceFactory.getCustomerBLService();
    //UserBLService userBLService = UserBLServiceFactory.getUserBLService();
    GiftDocVO giftDocVO;

    @FXML
    AnchorPane tablePane;

    @FXML
    private Label customer,id,date,maker,account;

    public GiftDocController(){
        giftDocVO=(GiftDocVO) Doc.doc;
    }

    //用于计算滑块的位置
    private double prePosition = 100,endPosition,mainHeight = 560,tableInitHeight = 292,paneInitHeight = 560;

    private double tableHeight;
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

        CommodityList listController = new CommodityList();

        customer.setText(customerBLService.findCustomerByID(giftDocVO.getCustomerID()).getName());
        id.setText(giftDocVO.getDocId());
        //date.setText(giftDocVO.getTime().toString());
        //maker.setText(userBLService.getUser(giftDocVO.getCreatorId()).getUsername());
        account.setText(String.valueOf(giftDocVO.getTotal()));

        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../CommodityList.fxml"));
            tablePane.getChildren().add(loader.load());
            listController=loader.getController();
            listController.getGiftObservableList().addAll(
                    giftDocVO.getGifts().stream()
                            .map(x-> new CommodityItem(x))
                            .collect(Collectors.toList())
            );

            tableHeight = listController.giftTableView.getPrefHeight();
            listController.setSilder(slider);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //表格高度*确认*之后，调整滑动面板高度以适应总高度
        if(tableHeight>tableInitHeight){
            scrollpane.setPrefHeight(tableHeight);
            mainHeight = mainHeight+tableHeight-tableInitHeight;
            endPosition = mainHeight - paneInitHeight;
        }

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

            }
        });

    }
}
