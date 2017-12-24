package nju.lighting.presentation.customerui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.CustomerVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/23.
 * Description
 *
 * @author 陈俊宇
 */
public class CustomerPicker {


    @FXML
    Pane pane;

    CustomerVO customer;

    boolean canceled=true;

    public void init(Upper upper){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerSearchListUI.fxml"));
        try {
            pane.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomerSearchListController controller=loader.getController();
        controller.setReadOnly(this,upper);

    }

    public CustomerVO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVO customer) {
        this.customer = customer;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
