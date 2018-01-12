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
import nju.lighting.vo.doc.stockdoc.StockReturnDocVO;

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
public class StockReturnDoc implements Initializable {


    @FXML
    Label customer, repository, comments, total, creator, time;

    @FXML
    HBox tableContainer;

    private StockReturnDocVO stockReturnDocVO;

    public StockReturnDoc() {
        stockReturnDocVO = (StockReturnDocVO) Doc.getDoc();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer.setText(CustomerHelper.getCustomer(Integer.parseInt(stockReturnDocVO.getCustomerId())).getName());
        comments.setText(stockReturnDocVO.getRemarks());
        total.setText(stockReturnDocVO.getTotalAmount()+"");
        time.setText(DateHelper.approximateTime(stockReturnDocVO.getTime()));
        creator.setText(UserHelper.getUserString(stockReturnDocVO.getCreatorId()));
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("nju/lighting/presentation/documentui/CommodityList.fxml"));
        try {

            tableContainer.getChildren().add(loader.load());
            ((CommodityList) loader.getController()).getGiftObservableList().addAll(
                    stockReturnDocVO.getItems().stream()
                            .map(x -> new CommodityItem(x))
                            .collect(Collectors.toList())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

