package nju.lighting.presentation.documentui.lossandgaindoc;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.factory.CustomerBLServiceFactory;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.utils.ScrollPaneHelper;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
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
    private Label id,date,maker;

    @FXML
    private Label commentsLabel;

    @FXML
    private ScrollPane scrollpaneMain;


    public LossAndGainDocController(){
        lossAndGainDocVO=(LossAndGainDocVO) Doc.doc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setText(lossAndGainDocVO.getDocId());
        if(lossAndGainDocVO.getTime()!=null){
            date.setText(lossAndGainDocVO.getTime().toString());
        }
        if(userBLService.getUser(lossAndGainDocVO.getCreatorId())!=null){
            maker.setText(userBLService.getUser(lossAndGainDocVO.getCreatorId()).getUsername());
        }


        LossAndGainList listController;

        FXMLLoader loader=new FXMLLoader(getClass().getResource("LossAndGainList.fxml"));

        commentsLabel.setText(lossAndGainDocVO.getComment());
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listController=loader.getController();
        listController.getData().setAll(lossAndGainDocVO.getItems().stream()
        .map(x-> new LossAndGainItem(x))
        .collect(Collectors.toList()));

        ScrollPaneHelper.marchHeight(listController.tableView,scrollpaneMain,anchorPane);

    }
}
