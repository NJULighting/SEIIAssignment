package nju.lighting.presentation.documentui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/11/27.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftListController implements Initializable {
    private static List<GiftItemVO> giftsVO;
    private double total = 0;

    @FXML
    public TableView giftTableView;

    @FXML
    public Label totalLabel;

    ObservableList<CommodityItem> giftObservableList = FXCollections.observableArrayList();

    @FXML
    public TableColumn<CommodityItem, String> commodityName;

    @FXML
    public TableColumn<CommodityItem, Integer> count;

    @FXML
    public TableColumn<CommodityItem, Double> price;

    @FXML
    public TableColumn<CommodityItem, Double> subtotal;


    public static void setGiftItemListVO(List<GiftItemVO> giftItemListVO) {
        giftsVO = giftItemListVO;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (giftsVO!=null){
            total = giftsVO.stream()
                    .mapToDouble(GiftItemVO::getSubtotal)
                    .sum();


            giftObservableList.addAll(giftsVO.stream()
                    .map(x -> new CommodityItem(x))
                    .collect(Collectors.toList())
            );
        }


            commodityName.setCellValueFactory(cellData ->
                    cellData.getValue().nameProperty());
            count.setCellValueFactory(cellData ->
                    cellData.getValue().countProperty().asObject());
            subtotal.setCellValueFactory(cellData ->
                    cellData.getValue().subtotalProperty().asObject());
            price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());




            giftTableView.setItems(giftObservableList);

            TableViewHelper.commonSet(giftTableView);


            totalLabel.setText(total + "");
        }




    public ObservableList getGiftObservableList() {
        return giftObservableList;
    }

    public void setFlexibleHeight(){
        TableViewHelper.setHeight(giftTableView,1.25);
    }
}

