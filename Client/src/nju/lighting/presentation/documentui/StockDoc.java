package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.commodityui.CommodityPicker;
import nju.lighting.presentation.customerui.CustomerPicker;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.promotionui.BenefitsPlan;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/24.
 * Description
 *
 * @author 陈俊宇
 */
public class StockDoc extends SalesDocController {
    @FXML
    private Button choseCustomBtn, finishBtn;

    @FXML
    private JFXTextField customer, salesman, user,account;

    @FXML
    private TextArea remarks;

    @FXML
    private Label sub,title;

    @FXML
    HBox container;

    @FXML
    VBox verticalVBox;

    @FXML
    Pane mainPane, commodityContainer;

    public StockDoc(){
        type = CustomerType.SUPPLIER;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GiftListEditable.fxml"));
        try {
            commodityContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        commodityController = loader.getController();
        commodityList = commodityController.giftObservableList;

        user.setText(Client.getUserVO().getUsername());

        account.textProperty().bind(commodityController.totalLabel.textProperty());
    }

    void setReturn(){
        title.setText("制定进货退货单");
    }
}
