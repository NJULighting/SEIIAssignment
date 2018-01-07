package nju.lighting.presentation.documentui.stockdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.doc.stockdoc.StockDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2018/1/5.
 * Description
 *
 * @author 陈俊宇
 */
public class StockDoc implements Initializable {

    @FXML
    Label customer, repository, comments, total, creator, time;

    @FXML
    HBox tableContainer;

    private StockDocVO stockDocVO;

    public StockDoc() {
        stockDocVO = (StockDocVO) Doc.getDoc();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer.setText(CustomerHelper.getCustomer(Integer.parseInt(stockDocVO.getCustomerId())).getName());
        comments.setText(stockDocVO.getRemarks());
        total.setText(stockDocVO.getTotalAmount()+"");
        time.setText(DateHelper.approximateTime(stockDocVO.getTime()));
        creator.setText(UserHelper.getUserString(stockDocVO.getCreatorId()));

        FXMLLoader loader=new FXMLLoader(getClass().getResource("../CommodityList.fxml"));
        try {

            tableContainer.getChildren().add(loader.load());
            ((CommodityList)loader.getController()).getGiftObservableList().addAll(
                    stockDocVO.getItems().stream()
                    .map(x-> new CommodityItem(x))
                    .collect(Collectors.toList())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
