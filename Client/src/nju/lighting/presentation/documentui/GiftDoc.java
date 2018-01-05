package nju.lighting.presentation.documentui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/4.
 * Description
 * @author 陈俊宇
 */
public class GiftDoc implements Initializable {
    @FXML
    VBox container;
    GiftDocVO giftDocVO;
    @FXML
    private Label title;

    public GiftDoc() {
        giftDocVO = (GiftDocVO) Doc.getDoc();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CommodityList listController;

        title.setText(giftDocVO.getDocId());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommodityList.fxml"));
            container.getChildren().add(loader.load());
            listController = loader.getController();
            listController.getGiftObservableList().addAll(
                    giftDocVO.getGifts().stream()
                            .map(x -> new CommodityItem(x))
                            .collect(Collectors.toList())
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
