package nju.lighting.presentation.customerui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.CustomerVO;
import shared.CustomerType;

import java.io.IOException;


/**
 * Created on 2017/12/23.
 * Description
 * @author 陈俊宇
 */
public class CustomerPicker {


    @FXML
    Pane pane;

    private CustomerVO customer;


    public void init(Upper upper, SimpleObjectProperty<CustomerVO> customer, CustomerType type) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerSearchList.fxml"));
        try {
            pane.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomerSearchListController controller = loader.getController();

        controller.setReadOnly(upper, customer, type);
        controller.search();

    }

    public CustomerVO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVO customer) {
        this.customer = customer;
    }

}
