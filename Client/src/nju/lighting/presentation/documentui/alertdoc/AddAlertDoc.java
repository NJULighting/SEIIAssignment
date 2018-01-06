package nju.lighting.presentation.documentui.alertdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.documentui.Modifiable;
import nju.lighting.presentation.documentui.lossandgaindoc.AddLossAndGainDoc;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainItem;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/26.
 * Description
 *
 * @author 陈俊宇
 */
public class AddAlertDoc implements Initializable, Modifiable {
    @FXML
    Pane container;

    AddLossAndGainDoc controller;

    public AlertDocVO getDoc() {
        return new AlertDocVO(Client.getUserVO().getID(), new Date(),
                controller.getObservableList().stream()
                        .map(LossAndGainItem::toAlertDocItemVO)
                        .collect(Collectors.toList()),
                controller.getComments()
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../lossandgaindoc/AddLossAndGainDoc.fxml"));
        try {
            container.getChildren().add(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
        controller = loader.getController();
        controller.setAlert();
    }

    @Override
    public void modify(Upper upper, DocVO docVO, boolean redFlush) {
        AlertDocVO alertDoc=(AlertDocVO)docVO;
        controller.getObservableList().setAll(alertDoc.getItems().stream()
        .map(LossAndGainItem::new)
        .collect(Collectors.toList()));
        controller.setComments(((AlertDocVO) docVO).getComment());

        if (!redFlush){
           controller.getCommitBtn().setOnAction(e-> DocHelper.saveAndApprove(getDoc()));
        }

    }

    @Override
    public Node getMainPane() {
        return controller.getMainPane();
    }
}
