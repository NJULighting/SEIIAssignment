package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    public TableColumn<GiftItemVO,String> commodityName;

    @FXML
    public TableColumn<GiftItemVO,String> count;

    @FXML
    public TableColumn<GiftItemVO,String> subtotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        commodityName.setCellValueFactory(cellData ->
//               cellData.getValue().commodityNameProperty());
//        count.setCellValueFactory(cellData->
//                cellData.getValue().countProperty());
//        subtotal.setCellValueFactory(cellData->
//        cellData.getValue().subtotalProperty());

        ObservableList gifts = FXCollections.observableArrayList();

            for (int i=0;i<giftDoc.getGifts().size();i++){
                    gifts.add(giftDoc.getGifts().get(i));
            }
        giftList.setItems(gifts);



    }
}
