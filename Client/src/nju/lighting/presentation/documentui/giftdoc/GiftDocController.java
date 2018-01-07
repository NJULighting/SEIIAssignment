package nju.lighting.presentation.documentui.giftdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.factory.CustomerBLServiceFactory;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.ScrollPaneHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GiftDocController implements Initializable {

    private CustomerBLService customerBLService = CustomerBLServiceFactory.getCustomerBLService();
    private UserBLService userBLService = UserBLServiceFactory.getUserBLService();
    private GiftDocVO giftDocVO;

    @FXML
    AnchorPane tablePane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label customer, id, maker, account, time;

    @FXML
    private ScrollPane scrollpane;


    public GiftDocController() {
        giftDocVO = (GiftDocVO) Doc.getDoc();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //double prePosition = slider.getLayoutY();

        CommodityList listController;

        time.setText(DateHelper.approximateTime(giftDocVO.getTime()));

        customer.setText(customerBLService.findCustomerByID(giftDocVO.getCustomerID()).getName());
        id.setText(giftDocVO.getDocId());

        maker.setText(UserHelper.getUserString(giftDocVO.getCreatorId()));


        account.setText(String.valueOf(giftDocVO.getTotal()));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../CommodityList.fxml"));
            tablePane.getChildren().add(loader.load());
            listController = loader.getController();
            listController.setGift();
            listController.getGiftObservableList().addAll(
                    giftDocVO.getGifts().stream()
                            .map(x -> new CommodityItem(x))
                            .collect(Collectors.toList())
            );

            //调整滑动面板高度以适应总高度
            ScrollPaneHelper.marchHeight(listController.giftTableView, scrollpane, anchorPane);
            /*
            //滑块控制表格的左右移动
            TableViewHelper.setSliderMarch(slider,listController.giftTableView);
            */

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        //滑块不离开界面
        ScrollPaneHelper.sliderFload(slider,scrollpaneMain,anchorPane,prePosition,557);
        */


    }
}
