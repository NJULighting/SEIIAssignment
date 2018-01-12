package nju.lighting.presentation.documentui;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/11/27.
 * Description 赠品列表的控制类
 * @author 陈俊宇
 */
public class GiftListController implements Initializable {
    private static List<GiftItemVO> giftsVO;
    @FXML
    public TableView giftTableView;
    @FXML
    public Label totalLabel;
    @FXML
    public TableColumn<CommodityItem, String> commodityName;
    @FXML
    public TableColumn<CommodityItem, Integer> count;
    @FXML
    public TableColumn<CommodityItem, Double> price;
    @FXML
    public TableColumn<CommodityItem, Double> subtotal;
    ObservableList<CommodityItem> giftObservableList = FXCollections.observableArrayList();
    private SimpleDoubleProperty totalProperty=new SimpleDoubleProperty();

    public static void setGiftItemListVO(List<GiftItemVO> giftItemListVO) {
        giftsVO = giftItemListVO;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        giftObservableList.addListener((ListChangeListener<? super CommodityItem>) c -> {
            if (giftObservableList.size() != 0)
                totalProperty.set(giftObservableList.stream()
                        .mapToDouble(x -> ((x).subtotalProperty().getValue()))
                        .sum());
            else {
                totalProperty.set(0);
            }
        });


        commodityName.setCellValueFactory(cellData ->
                cellData.getValue().nameProperty());
        count.setCellValueFactory(cellData ->
                cellData.getValue().countProperty().asObject());
        subtotal.setCellValueFactory(cellData ->
                cellData.getValue().subtotalProperty().asObject());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());


        giftTableView.setItems(giftObservableList);

        TableViewHelper.commonSet(giftTableView);


        totalLabel.textProperty().bind(totalProperty.asString());
    }


    public ObservableList getGiftObservableList() {
        return giftObservableList;
    }

    public void setFlexibleHeight() {
        TableViewHelper.setHeight(giftTableView, 1.25);
    }
}

