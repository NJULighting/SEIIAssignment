package nju.lighting.presentation.documentui.alertdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainItem;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainList;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AlertDocController implements Initializable {

    @FXML
    HBox tableContainer;

    private AlertDocVO alertDoc;

    public AlertDocController(){alertDoc=(AlertDocVO) Doc.getDoc();}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../lossandgaindoc/LossAndGainList.fxml"));
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
