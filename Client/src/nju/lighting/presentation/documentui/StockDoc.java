package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.CustomerType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

        commodityListController = loader.getController();

        docItemList = commodityListController.giftObservableList;
        commodityList.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()){
                    docItemList.addAll(c.getAddedSubList().stream()
                            .map(x-> new CommodityItem(x,1))
                            .collect(Collectors.toList()));
                }
            }
        });

        user.setText(Client.getUserVO().getUsername());

        account.textProperty().bind(commodityListController.totalLabel.textProperty());
    }

    void setReturn(){
        title.setText("制定进货退货单");
    }
}
