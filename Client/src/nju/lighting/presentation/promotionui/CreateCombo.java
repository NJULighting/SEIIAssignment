package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/11.
 * Description
 *
 * @author 陈俊宇
 */
public class CreateCombo implements Initializable {

    List<GiftItemVO> commodityList;

    TableView comboTable;


    @FXML
    VBox comboBox;

    @FXML
    Label emptyLabel;


    void refresh(){
        if (commodityList==null||commodityList.size()==0){
            emptyLabel.setVisible(true);
            comboTable.setVisible(false);
        }else {
            emptyLabel.setVisible(false);
            comboTable.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
