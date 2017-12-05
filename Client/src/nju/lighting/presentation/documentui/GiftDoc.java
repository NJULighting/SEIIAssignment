package nju.lighting.presentation.documentui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/4.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftDoc implements Initializable {
    @FXML
    private Label title;

    @FXML
    VBox container;


    GiftDocVO giftDocVO;

    public GiftDoc(){
        giftDocVO=(GiftDocVO)Doc.doc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GiftListController.setGiftItemListVO(giftDocVO.getGiftItemListVO());
        title.setText(giftDocVO.getDocId());
        try {
            container.getChildren().add(FXMLLoader.load(getClass().getResource("GiftList.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
