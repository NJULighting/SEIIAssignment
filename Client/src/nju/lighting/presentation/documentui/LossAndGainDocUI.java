package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainItem;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainList;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.LossAndGainItemType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/25.
 * Description
 *
 * @author 陈俊宇
 */
public class LossAndGainDocUI implements Initializable {

    LossAndGainDocMain main;
    ObservableList<BasicCommodityItemVO> commodities= FXCollections.observableArrayList();
    ObservableList<LossAndGainItem> docItemList;

    FXMLLoader loader;

    @FXML
    HBox tableContainer;
    @FXML
    JFXTextField creatorText;

    LossAndGainList listController;

    @FXML
    Button chooseCommodityBtn;



    public void setMain(LossAndGainDocMain main) {
        this.main = main;
    }

    @FXML
    void removeAll(){
        docItemList.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseCommodityBtn.setOnAction(event -> {
            CommodityHelper.chooseCommodity(main,commodities);

        });

        creatorText.setText(Client.getUserVO().getUsername());
        loader=new FXMLLoader(getClass().getResource("lossandgaindoc/LossAndGainList.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listController=loader.getController();
        listController.setEditable();
        docItemList=listController.getData();

        commodities.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()){
                    if (c.wasAdded()){
                        docItemList.addAll(
                                c.getAddedSubList().stream()
                                        .map(x-> new LossAndGainItem(x,1, LossAndGainItemType.LOSS))
                                        .collect(Collectors.toList())
                        );
                    }
                }
            }
        });


    }

    void setAlert(){
            listController.setAlert();
    }


    public ObservableList<BasicCommodityItemVO> getCommodities() {
        return commodities;
    }


}
