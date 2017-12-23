package nju.lighting.presentation.commodityui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nju.lighting.bl.commoditybl.BasicCommodityItem;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.Nameable;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    HBox father;
    boolean canceled;
    Node back;
    Upper upper;


    @FXML
    VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("CommodityCategory.fxml"));
            container.getChildren().add(loader.load());
            category=loader.getController();
            category.categoryTreeView.setMinHeight(480);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ok(){
        ObservableList<TreeItem<Nameable>> temp=category.categoryTreeView.getSelectionModel().getSelectedItems();

        commodities=temp.stream().
                filter(x->x.getValue().getClass().equals(CommodityItemVO.class))
                .map(x-> new GiftItemVO(((CommodityItemVO)x.getValue()).toBasicCommodityItem(),1))
                .collect(Collectors.toList());
        canceled=false;
        upper.back();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setFather(HBox father) {
        this.father = father;
    }

    public void setBack(Node back) {
        this.back = back;
    }

    public void setUpper(Upper upper) {
        this.upper = upper;
    }

    public List<GiftItemVO> getCommodities() {
        return commodities;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
