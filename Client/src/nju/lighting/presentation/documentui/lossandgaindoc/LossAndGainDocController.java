package nju.lighting.presentation.documentui.lossandgaindoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.ScrollPaneHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LossAndGainDocController implements Initializable {
    UserBLService userBLService = UserBLServiceFactory.getUserBLService();
    LossAndGainDocVO lossAndGainDocVO;

    @FXML
    HBox tableContainer;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label id, date, maker;

    @FXML
    private Label commentsLabel;

    @FXML
    private ScrollPane scrollpaneMain;


    public LossAndGainDocController() {
        lossAndGainDocVO = (LossAndGainDocVO) Doc.getDoc();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setText(lossAndGainDocVO.getDocId());
        if (lossAndGainDocVO.getTime() != null) {
            date.setText(DateHelper.approximateTime(lossAndGainDocVO.getTime()));
        }
        if (userBLService.getUser(lossAndGainDocVO.getCreatorId()) != null) {
            maker.setText(UserHelper.getUserString(lossAndGainDocVO.getCreatorId()));
        }


        LossAndGainList listController;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LossAndGainList.fxml"));

        commentsLabel.setText(lossAndGainDocVO.getComment());
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listController = loader.getController();
        listController.getData().setAll(lossAndGainDocVO.getItems().stream()
                .map(x -> new LossAndGainItem(x))
                .collect(Collectors.toList()));

        ScrollPaneHelper.marchHeight(listController.tableView, scrollpaneMain, anchorPane);

    }
}
