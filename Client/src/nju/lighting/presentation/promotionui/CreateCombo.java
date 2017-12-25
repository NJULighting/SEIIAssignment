package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import nju.lighting.presentation.documentui.GiftListEditable;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.presentation.utils.ImageViewHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/11.
 * Description
 *
 * @author 陈俊宇
 */
public class CreateCombo implements Initializable {

    List<GiftItemVO> commodityList;

    TableView comboTable;

    JFXNodesList nodeList;

    CreatePromotion createPromotion;

    AnchorPane commodityPicker;

    GiftListEditable giftsController;

    double height;
    @FXML
    JFXButton btn;
    ImageView open = ImageViewHelper.circleOpen();
    ImageView close = ImageViewHelper.circleClose();

    @FXML
    VBox comboBox, container;

    @FXML
    Label emptyLabel;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nodeList = new JFXNodesList();

        btn.setGraphic(open);
        btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btn.getStylesheets().add(getClass().getResource("../circleButton.css").toExternalForm());

        container.setSpacing(50);

        comboBox.setPadding(new Insets(0, 0, 50, 0));

        try {
            FXMLLoader giftsLoader = new FXMLLoader(getClass().getResource("../documentui/giftListEditable.fxml"));
            //GiftListEditable.giftsVO=null;
            comboBox.getChildren().add(giftsLoader.load());
            giftsController=giftsLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CommodityHelper.addCommodity(giftsController);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setCreatePromotion(CreatePromotion createPromotion) {
        this.createPromotion = createPromotion;
    }
}

