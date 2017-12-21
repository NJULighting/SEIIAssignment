package nju.lighting.presentation.mainui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import shared.Identity;
import sun.dc.pr.PRError;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/18.
 * Description
 *
 * @author 陈俊宇
 */
public class SystemManager implements Initializable {

    private final String GENERAL = "总经理";
    private final String REPOSITORY = "库存管理人员";
    private final String SALES = "销售员";
    private final String FINANCE = "财务人员";
    private final String SALE_MANAGER = "销售经理";
    @FXML
    HBox detailContainer;

    @FXML
    JFXListView userListView;

    @FXML
    JFXListView<String> categoryListView;

    HashMap<String, Identity> hashMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hashMap.put(GENERAL, Identity.GENERAL);
        hashMap.put(REPOSITORY, Identity.REPOSITORY);
        hashMap.put(FINANCE, Identity.FINANCE);
        hashMap.put(SALES, Identity.SALE);
        hashMap.put(SALE_MANAGER, Identity.SALE_MANAGER);

        categoryListView.getItems().addAll(GENERAL,
                FINANCE,
                REPOSITORY,
                SALES,
                SALE_MANAGER);
        categoryListView.setCellFactory((ListView<String> p) ->
                new categoryListCell());
        categoryListView.setOnMouseClicked(e ->{
            categoryListView.getSelectionModel().getSelectedItems();
        });
        categoryListView.getStylesheets().add(getClass().getResource("../user.css").toExternalForm());



    }


    class categoryListCell extends JFXListCell<String> {
        ContextMenu menu = new ContextMenu();

        categoryListCell() {
            MenuItem addUser = new MenuItem("添加用户");
            menu.getItems().add(addUser);

        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty){
                setText(null);
                setGraphic(null);
            }else {

                setText(null);
                Label label= new Label(item);

                setGraphic(label);
                setContextMenu(menu);
            }

        }
    }
}
