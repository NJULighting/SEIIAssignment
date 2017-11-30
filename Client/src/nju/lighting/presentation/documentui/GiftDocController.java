package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import nju.lighting.bl.documentbl.giftdoc.GiftDoc;
import nju.lighting.bl.documentbl.giftdoc.GiftDocItem;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/27.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftDocController implements Initializable {
    public static GiftDocVO giftDoc;


    @FXML
    public TableView giftList;

    @FXML
    public  Label total;

    @FXML
    public TableColumn<GiftItemVO,String> commodityName;

    @FXML
    public TableColumn<GiftItemVO,String> count;

    @FXML
    public TableColumn<GiftItemVO,String > price;

    @FXML
    public TableColumn<GiftItemVO,String> subtotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        commodityName.setCellValueFactory(cellData ->
               cellData.getValue().commodityNameProperty());
        count.setCellValueFactory(cellData->
                cellData.getValue().countProperty());
        subtotal.setCellValueFactory(cellData->
        cellData.getValue().subtotalProperty());
        int size=giftDoc.getGifts().size();
        price.setCellValueFactory(cellData->cellData.getValue().priceProperty());
        ObservableList gifts = FXCollections.observableArrayList();

            for (int i=0;i<size;i++){
                    gifts.add(giftDoc.getGifts().get(i));
            }

            System.out.println(size);
        giftList.setItems(gifts);
        giftList.setPrefHeight((1.7+size)*25);

        total.setText(giftDoc.getTotal()+"");

    }
}
