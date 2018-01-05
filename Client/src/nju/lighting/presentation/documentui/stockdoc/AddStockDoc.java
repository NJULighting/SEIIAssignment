package nju.lighting.presentation.documentui.stockdoc;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.SalesDocController;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.CustomerType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/24.
 * Description
 * @author 陈俊宇
 */
public class AddStockDoc extends SalesDocController {
    @FXML
    HBox container;
    @FXML
    VBox verticalVBox;
    @FXML
    Pane mainPane, commodityContainer;
    @FXML
    private Button choseCustomBtn, finishBtn;
    @FXML
    private JFXTextField customer, salesman, user, account;
    @FXML
    private TextArea remarks;
    @FXML
    private Label sub, title;

    private CustomerType type=getType();
    private CommodityList controller=getCommodityListController();
    private ObservableList<CommodityItem> docItemList= FXCollections.observableArrayList();
    private SimpleObjectProperty<CustomerVO> customerProperty=getCustomerProperty();
    private ObservableList<BasicCommodityItemVO> commodityList=getCommodityList();


    public AddStockDoc() {
        type = CustomerType.SUPPLIER;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../CommodityList.fxml"));
        try {
            commodityContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

       controller = loader.getController();
        controller.setEditable();
        controller.setMaxSize(480, 700);

        docItemList = controller.getGiftObservableList();
        commodityList.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()) {
                    docItemList.addAll(c.getAddedSubList().stream()
                            .map(x -> new CommodityItem(x, 1))
                            .collect(Collectors.toList()));
                }
            }
        });

        customerProperty.addListener(new ChangeListener<CustomerVO>() {
            @Override
            public void changed(ObservableValue<? extends CustomerVO> observable, CustomerVO oldValue, CustomerVO newValue) {
                customer.setText(customerProperty.getValue().getName());
            }
        });

        user.setText(Client.getUserVO().getUsername());

        account.textProperty().bind(controller.getTotal().asString());
    }

    void setReturn() {
        title.setText("制定进货退货单");
    }
}
