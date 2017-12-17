package nju.lighting.presentation.documentui;


import javafx.beans.binding.Bindings;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/10.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftListEditable implements Initializable {


    public static List<GiftItemVO> giftsVO;
    private double total = 0;
    ObservableList giftObservableList;

    @FXML
    public TableView giftTableView;

    @FXML
    public Label totalLabel;

    @FXML
    public TableColumn<CommodityItem, String> commodityName;

    @FXML
    TableColumn<CommodityItem, Boolean> deleteBtn;

    @FXML
    public TableColumn<CommodityItem, Integer> count;

    @FXML
    public TableColumn<CommodityItem, Double> price;

    @FXML
    public TableColumn<CommodityItem, Double> subtotal;


    public void refresh() {

        giftObservableList.setAll(giftsVO.stream()
                .map(x -> new CommodityItem(x))
                .collect(Collectors.toList()));
    }

    void calculateTotal() {
        totalLabel.setText(giftObservableList.stream()
                .mapToDouble(x -> (((CommodityItem) x).subtotal.getValue()))
                .sum() + "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        if (giftsVO == null)
            giftsVO = new ArrayList<>();

        giftObservableList = FXCollections.observableArrayList();

        giftObservableList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                if (giftObservableList.size() != 0)
                    calculateTotal();
                else
                    totalLabel.setText("0");
            }
        });


        commodityName.setCellValueFactory(cellData ->
                cellData.getValue().nameProperty());
        count.setCellValueFactory(cellData ->
                cellData.getValue().countProperty().asObject());
        subtotal.setCellValueFactory(cellData ->
                cellData.getValue().subtotalProperty().asObject());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        deleteBtn.setCellValueFactory(cellData ->
                cellData.getValue().boolProperty());


        refresh();

        // 设置表格中的按钮
        Callback<TableColumn<CommodityItem, Boolean>,
                TableCell<CommodityItem, Boolean>> cellFactory
                = (TableColumn<CommodityItem, Boolean> p) -> new BtnCell();

        deleteBtn.setCellFactory(cellFactory);


        Callback<TableColumn<CommodityItem, Integer>,
                TableCell<CommodityItem, Integer>> cellFactoryForCount
                = (TableColumn<CommodityItem, Integer> p) -> new EditingCell();


        count.setCellFactory(cellFactoryForCount);

        count.setOnEditCommit(
                (TableColumn.CellEditEvent<CommodityItem, Integer> t) -> {
                    int index = t.getTablePosition().getRow();

                    CommodityItem selected = t.getTableView().getItems().get(
                            index);
                    selected.setCount(t.getNewValue());
                    selected.setSubtotal(selected.getPrice() * selected.getCount());
                    System.out.println();
                    calculateTotal();

                });

        giftTableView.setItems(giftObservableList);


        TableViewHelper.commonSet(giftTableView);


        calculateTotal();


    }


}
