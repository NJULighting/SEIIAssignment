package nju.lighting.presentation.documentui;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nju.lighting.presentation.mainui.CommonFather;
import nju.lighting.vo.doc.giftdoc.GiftItemListVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

        if (giftItemListVO!=null){

            ObservableList giftObservableList = FXCollections.observableArrayList();

            commodityName.setCellValueFactory(cellData ->
                    cellData.getValue().commodityNameProperty());
            count.setCellValueFactory(cellData ->
                    cellData.getValue().countProperty());
            subtotal.setCellValueFactory(cellData ->
                    cellData.getValue().subtotalProperty());
            List<GiftItemVO> giftItemVOS=giftItemListVO.getGiftItemVOs();

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

}
