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
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.presentation.utils.CustomerHelper;
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
 *
 * @author 陈俊宇
 */
public class AddStockDoc implements Initializable,Upper {
    @FXML
    HBox container;
    @FXML
    VBox verticalVBox;
    @FXML
    Pane mainPane, commodityContainer;
    @FXML
    private Button chooseCustomerBtn, chooseCommodityBtn;
    @FXML
    private JFXTextField customer, salesman, user, account;
    @FXML
    private TextArea remarks;
    @FXML
    private Label sub, title;

    private CustomerType type = CustomerType.SUPPLIER;
    private ObservableList<CommodityItem> docItemList = FXCollections.observableArrayList();
    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();



    public void back() {
        setChildren(mainPane, "");
    }

    @Override
    public void setChildren(Node node, String title) {
        container.getChildren().setAll(node);
        sub.setText(title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../CommodityList.fxml"));
        try {
            commodityContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommodityList controller = loader.getController();
        controller.setEditable();
        controller.setMaxSize(480, 700);

        docItemList = controller.getGiftObservableList();
        ObservableList<BasicCommodityItemVO> commodityList=FXCollections.observableArrayList();
        chooseCommodityBtn.setOnAction(e-> CommodityHelper.chooseCommodity(this,commodityList));

        commodityList.addListener((ListChangeListener<? super BasicCommodityItemVO>) c->{
            while (c.next()){
                docItemList.addAll(c.getAddedSubList().stream()
                .map(x-> new CommodityItem(x,1))
                .collect(Collectors.toList()));
            }
        });


        chooseCustomerBtn.setOnAction(e-> CustomerHelper.setCustomer(this,customerProperty));
        customerProperty.addListener(c-> customer.setText(customerProperty.getValue().getName()));


        user.setText(Client.getUserVO().getUsername());

        account.textProperty().bind(controller.getTotal().asString());
    }

    void setReturn() {
        title.setText("制定进货退货单");
    }


}
