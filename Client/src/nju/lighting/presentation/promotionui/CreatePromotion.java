package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/11.
 * Description
 *
 * @author 陈俊宇
 */
public class CreatePromotion implements Initializable {

    private final String COMBO = "组合商品降价";
    private final String PRICE_ORIENTED = "针对不同总价";
    private final String CUSTOMER_ORIENTED = "针对不同级别用户";

    HashMap<String, String> hashMap = new HashMap<>();

    @FXML
    JFXComboBox type;

    @FXML
    VBox container;

    void initTypeComboBox() {
        type.getItems().add(new Label(COMBO));
        type.getItems().add(new Label(PRICE_ORIENTED));
        type.getItems().add(new Label(CUSTOMER_ORIENTED));

        type.setConverter(new StringConverter<Label>() {

            @Override

            public String toString(Label object) {

                return object == null ? "" : object.getText();

            }


            @Override

            public Label fromString(String string) {

                return new Label(string);

            }

        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hashMap.put(COMBO,"createCombo.fxml");


        initTypeComboBox();

        type.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource( hashMap.get(((Label) type.getValue()).getText())));
                        if (container.getChildren().size()>4)
                            container.getChildren().remove(4);
                        container.getChildren().add(loader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
