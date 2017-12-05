package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import nju.lighting.bl.documentbl.giftdoc.GiftDoc;
import nju.lighting.bl.documentbl.giftdoc.GiftDocItem;
import nju.lighting.presentation.mainui.CommonFather;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
import nju.lighting.vo.doc.giftdoc.GiftItemListVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Created on 2017/11/27.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftListController extends CommonFather  {
    private  static GiftItemListVO giftItemListVO;

    @FXML
    public TableView giftTableView;

    @FXML
    public Label total;

    @FXML
    public TableColumn<GiftItemVO, String> commodityName;

    @FXML
    public TableColumn<GiftItemVO, String> count;

    @FXML
    public TableColumn<GiftItemVO, String> price;

    @FXML
    public TableColumn<GiftItemVO, String> subtotal;


    public static void setGiftItemListVO(GiftItemListVO giftItemListVO) {
        GiftListController.giftItemListVO = giftItemListVO;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList giftObservableList = FXCollections.observableArrayList();

        commodityName.setCellValueFactory(cellData ->
                cellData.getValue().commodityNameProperty());
        count.setCellValueFactory(cellData ->
                cellData.getValue().countProperty());
        subtotal.setCellValueFactory(cellData ->
                cellData.getValue().subtotalProperty());
        ArrayList<GiftItemVO> giftItemVOS=giftItemListVO.getGiftItemVOs();

        int size = giftItemVOS.size();
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        for (int i = 0; i < size; i++) {
            giftObservableList.add(giftItemVOS.get(i));
        }

        System.out.println("size  " +size);
        giftTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        giftTableView.setItems(giftObservableList);

        //设置表格的高度和与数据的多少一致，否则数据多的时候表中就会出现滚动条
        giftTableView.prefHeightProperty().bind(giftTableView.fixedCellSizeProperty().multiply(Bindings.size(giftTableView.getItems()).add(1.01)));
        disableReorder(giftTableView);

        total.setText(giftItemListVO.getTotal() + "");
    }

}
