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

    CustomerBLService customerBLService = CustomerBLServiceFactory.getCustomerBLService();
    UserBLService userBLService = UserBLServiceFactory.getUserBLService();
    LossAndGainDocVO lossAndGainDocVO;

    @FXML
    AnchorPane tablePane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label customer,id,date,maker,account;

    @FXML
    private JFXSlider slider;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private ScrollPane scrollpaneMain;


    public LossAndGainDocController(){
        lossAndGainDocVO=(LossAndGainDocVO) Doc.doc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double prePosition = slider.getLayoutY();

        LossAndGainList listController;
/*
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../CommodityList.fxml"));
            tablePane.getChildren().add(loader.load());
            listController=loader.getController();
            listController.getData().addAll(
                    lossAndGainDocVO.getItems().stream()
                            .map(x-> new CommodityItem(x))
                            .collect(Collectors.toList())
            );

            //调整滑动面板高度以适应总高度
            ScrollPaneHelper.marchHeight(listController.giftTableView,scrollpane,anchorPane);
            //滑块控制表格的左右移动
            listController.setSilder(slider);

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //滑块不离开界面
        ScrollPaneHelper.sliderFload(slider,scrollpaneMain,anchorPane,prePosition,557);


    }
}
