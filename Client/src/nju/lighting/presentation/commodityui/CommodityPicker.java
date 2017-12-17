package nju.lighting.presentation.commodityui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.Nameable;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityPicker implements Initializable {
    List<GiftItemVO> commodities=new ArrayList<>();
    CommodityCategory category;
    Stage stage;
    boolean canceled;


    @FXML
    VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("CommodityCategory.fxml"));
            container.getChildren().add(loader.load());
            category=loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ok(){
        ObservableList<TreeItem<Nameable>> temp=category.categoryTreeView.getSelectionModel().getSelectedItems();
        for (int i=0;i<temp.size();i++){
            commodities.add(new GiftItemVO(((CommodityItemVO) temp.get(i).getValue()).toBasicCommodityItem(),1));
        }
        System.out.println("hide");
        stage.hide();
        canceled=false;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public List<GiftItemVO> getCommodities() {
        return commodities;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
