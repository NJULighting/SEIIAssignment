package nju.lighting.presentation.documentui;


import com.jfoenix.controls.JFXSlider;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.TableViewHelper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/10.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityList implements Initializable {


    @FXML
    public TableView giftTableView;
    @FXML
    public TableColumn<CommodityItem, String> commodityName;
    @FXML
    public TableColumn<CommodityItem, Integer> count;
    @FXML
    public TableColumn<CommodityItem, String> id;
    @FXML
    public TableColumn<CommodityItem, Double> price;
    @FXML
    public TableColumn<CommodityItem, Double> subtotal;
    @FXML
    public TableColumn<CommodityItem, String> modelNum;
    @FXML
    public TableColumn<CommodityItem, String> comments;
    @FXML
    TableColumn<CommodityItem, Boolean> deleteBtn;
    //public static List<GiftItemVO> giftsVO;
    private SimpleDoubleProperty total = new SimpleDoubleProperty();
    private ObservableList<CommodityItem> giftObservableList;
    private Upper upper;


    public void calculateTotal() {
        total.set(giftObservableList.stream()
                .mapToDouble(x -> ((x).subtotal.getValue()))
                .sum());
        //totalLabel.setText(total+ "");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        giftObservableList = FXCollections.observableArrayList();

        giftObservableList.addListener((ListChangeListener<? super CommodityItem>) c -> {
            if (giftObservableList.size() != 0)
                calculateTotal();
            else {
                total.set(0);
            }
        });


        commodityName.setCellValueFactory(cellData ->
                cellData.getValue().nameProperty());
        count.setCellValueFactory(cellData ->
                cellData.getValue().countProperty().asObject());
        subtotal.setCellValueFactory(cellData ->
                cellData.getValue().subtotalProperty().asObject());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
//        deleteBtn.setCellValueFactory(cellData ->
//                cellData.getValue().giftProperty());
        modelNum.setCellValueFactory(cellData ->
                cellData.getValue().modelNumProperty());
        comments.setCellValueFactory(cellData ->
                cellData.getValue().commentsProperty());
        id.setCellValueFactory(cellData ->
                cellData.getValue().idProperty());


        giftTableView.setItems(giftObservableList);


        TableViewHelper.commonSet(giftTableView);
        // TableViewHelper.setHeight(giftTableView, 1.2);

        calculateTotal();


    }

    public void setEditable() {
        Callback<TableColumn<CommodityItem, Boolean>,
                TableCell<CommodityItem, Boolean>> cellFactory
                = (TableColumn<CommodityItem, Boolean> p) -> new BtnCell();

        deleteBtn.setCellFactory(cellFactory);


        Callback<TableColumn<CommodityItem, Integer>,
                TableCell<CommodityItem, Integer>> cellFactoryForCount
                = (p) -> (new EditingCell<CommodityItem, Integer>("int"));

        Callback<TableColumn<CommodityItem, Double>,
                TableCell<CommodityItem, Double>> cellFactoryForPrice
                = (TableColumn<CommodityItem, Double> p) -> new EditingCell<CommodityItem, Double>("double");

        Callback<TableColumn<CommodityItem, String>,
                TableCell<CommodityItem, String>> cellFactoryForComments
                = (TableColumn<CommodityItem, String> p) -> new EditingCell<CommodityItem, String>("string");

        count.setCellFactory(cellFactoryForCount);
        price.setCellFactory(cellFactoryForPrice);
        comments.setCellFactory(cellFactoryForComments);

        count.setOnEditCommit(
                (TableColumn.CellEditEvent<CommodityItem, Integer> t) -> {
                    CommodityItem selected = t.getTableView().getItems().get(
                            t.getTablePosition().getRow());
                    selected.setCount(t.getNewValue());
                    int newValue = t.getNewValue();
                    if (selected.isHasMax()) {
                        selected.setCount(newValue > selected.getCommodity().getRepCount() ?
                                selected.getCommodity().getRepCount() : newValue);
                        giftTableView.refresh();
                    } else
                        selected.setCount(newValue);

                    calculateTotal();


                });

        price.setOnEditCommit(
                (TableColumn.CellEditEvent<CommodityItem, Double> t) -> {
                    CommodityItem selected = t.getTableView().getItems().get(
                            t.getTablePosition().getRow());

                    selected.setPrice(t.getNewValue());

                    calculateTotal();

                });

        comments.setOnEditCommit((TableColumn.CellEditEvent<CommodityItem, String> t) -> {
            CommodityItem selected = t.getTableView().getItems().get(
                    t.getTablePosition().getRow());
            selected.setComments(t.getNewValue());

        });
    }

    public void setGift() {
        giftTableView.getColumns().removeAll(comments, deleteBtn);
        price.setEditable(false);
        id.setPrefWidth(100);
        commodityName.setPrefWidth(230);
        modelNum.setPrefWidth(70);
        price.setPrefWidth(100);
        subtotal.setPrefWidth(100);
        count.setPrefWidth(100);

    }

    public void setGiftAndEditable() {
        setEditable();
        setGift();
        giftTableView.getColumns().add(deleteBtn);
        commodityName.setPrefWidth(commodityName.getPrefWidth() + 40);
        modelNum.setPrefWidth(modelNum.getPrefWidth() + 30);
        TableViewHelper.setHeight(giftTableView, 0.9);

    }

    /**
     * 设置商品列表的最大高度以及宽度
     *
     * @param maxHeight 最大高度
     * @param maxWidth  最大宽度
     */
    public void setMaxSize(double maxHeight, double maxWidth) {
        giftTableView.setMaxSize(maxWidth, maxHeight);
    }

    public ObservableList<CommodityItem> getGiftObservableList() {
        return giftObservableList;
    }

    public void setSilder(JFXSlider silder) {
        TableViewHelper.setSliderMarch(silder, giftTableView);
    }

    public SimpleDoubleProperty getTotal() {
        return total;
    }
}
