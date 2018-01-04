package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainItem;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainList;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocVO;
import shared.LossAndGainItemType;
import shared.ResultMessage;
import shared.TwoTuple;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/25.
 * Description
 * @author 陈俊宇
 */
public class LossAndGainDocUI implements Initializable {

    LossAndGainDocMain main;
    ObservableList<BasicCommodityItemVO> commodities = FXCollections.observableArrayList();
    ObservableList<LossAndGainItem> docItemList;

    DocBLService blService = DocBLServiceFactory.getDocBLService();

    FXMLLoader loader;

    @FXML
    HBox tableContainer;
    @FXML
    JFXTextField creatorText;

    LossAndGainList listController;

    @FXML
    Button chooseCommodityBtn;

    @FXML
    JFXButton commitBtn;

    @FXML

    TextArea comments;


    public void setMain(LossAndGainDocMain main) {
        this.main = main;
    }

    @FXML
    void removeAll() {
        docItemList.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commitBtn.setOnAction(e -> {
            TwoTuple<ResultMessage, String> res = blService.commitDoc(new LossAndGainDocVO(new Date(),
                    Client.getUserVO().getID(),
                    docItemList.stream()
                            .map(x -> x.toLossAndGainDocItemVO())
                            .collect(Collectors.toList()),
                    comments.getText()));

            DialogHelper.dialog(res.t, main.getStackPane());
        });

        chooseCommodityBtn.setOnAction(event -> {
            CommodityHelper.chooseCommodity(main, commodities);

        });

        creatorText.setText(Client.getUserVO().getUsername());
        loader = new FXMLLoader(getClass().getResource("lossandgaindoc/LossAndGainList.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listController = loader.getController();
        listController.setEditable();
        docItemList = listController.getData();

        commodities.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()) {
                    if (c.wasAdded()) {
                        docItemList.addAll(
                                c.getAddedSubList().stream()
                                        .map(x -> new LossAndGainItem(x, 1, LossAndGainItemType.LOSS))
                                        .collect(Collectors.toList())
                        );
                    }
                }
            }
        });


    }

    void setAlert() {
        listController.setAlert();
    }


    public ObservableList<BasicCommodityItemVO> getCommodities() {
        return commodities;
    }


}
