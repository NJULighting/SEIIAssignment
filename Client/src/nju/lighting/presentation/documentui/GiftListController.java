package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.CommonFather;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
        for (int i = 0; i < giftsVO.size(); i++) {
            total += giftsVO.get(i).getSubtotal();
        }


        if (giftsVO != null) {

            ObservableList giftObservableList = FXCollections.observableArrayList();

            commodityName.setCellValueFactory(cellData ->
                    cellData.getValue().nameProperty());
            count.setCellValueFactory(cellData ->
                    cellData.getValue().countProperty().asObject());
            subtotal.setCellValueFactory(cellData ->
                    cellData.getValue().subtotalProperty().asObject());
            price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

            List<GiftItemVO> giftItemVOS = giftsVO;

            int size = giftItemVOS.size();

            for (int i = 0; i < size; i++) {
                giftObservableList.add(new CommodityItem(giftItemVOS.get(i)));
            }



            System.out.println("size  " + size);
            giftTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            giftTableView.setItems(giftObservableList);

            //设置表格的高度和与数据的多少一致，否则数据多的时候表中就会出现滚动条
            giftTableView.prefHeightProperty().bind(giftTableView.fixedCellSizeProperty().multiply(Bindings.size(giftTableView.getItems()).add(1.01)));

            //禁止表头拖动
            TableViewHelper.disableReorder(giftTableView);

            totalLabel.setText(total + "");
        }


    }
}

