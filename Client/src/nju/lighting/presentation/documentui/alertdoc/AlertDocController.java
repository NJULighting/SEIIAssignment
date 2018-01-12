package nju.lighting.presentation.documentui.alertdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainItem;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainList;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AlertDocController implements Initializable {

    @FXML
    HBox tableContainer;

    @FXML
    Label creatorLabel,timeLabel,idLabel;

    private AlertDocVO alertDoc;

    public AlertDocController(){alertDoc=(AlertDocVO) Doc.getDoc();}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idLabel.setText(alertDoc.getDocId());
        timeLabel.setText(DateHelper.approximateTime(alertDoc.getTime()));
        creatorLabel.setText(UserHelper.getUserString(alertDoc.getCreatorId()));
        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("nju/lighting/presentation/documentui/lossandgaindoc/AddLossAndGainDoc.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LossAndGainList listController=loader.getController();
        listController.getData().setAll(alertDoc.getItems().stream()
        .map(x-> new LossAndGainItem(x))
        .collect(Collectors.toList()));
        listController.setAlert();
    }
}
