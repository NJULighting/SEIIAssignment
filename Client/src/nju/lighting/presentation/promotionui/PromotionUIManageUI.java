package nju.lighting.presentation.promotionui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import nju.lighting.presentation.mainui.Upper;
import shared.PromotionType;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/28.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionUIManageUI implements Initializable, Upper {
    @FXML
    MenuButton createPromotionMenu;

    @FXML
    HBox subLabelBox, container;

    @FXML
    Label title;

    @FXML
    Pane mainPane;

    Node createPromotion;

    HashMap<PromotionType,String> typeToTitle=new HashMap<>();

    public void back() {
        container.getChildren().setAll(createPromotion);
       ObservableList<Node> child= subLabelBox.getChildren();
        subLabelBox.getChildren().remove(1,subLabelBox.getChildren().size());
    }


    public void setChildren(Node node, String title) {
        Label theNew = new Label(title);
        theNew.setFont(Font.font(20));
        subLabelBox.getChildren().add(theNew);
        theNew.setOnMouseClicked(e -> {
            ObservableList<Node> children = subLabelBox.getChildren();
            children.remove(children.indexOf(theNew) + 1, children.size());
            container.getChildren().setAll(node);
        });
        container.getChildren().setAll(node);

    }

//    void loadCreatePromotion(String url){
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
//
//        try {
//            createPromotion = loader.load();
//            setChildren(createPromotion, ">"+urlsToTitle.get(url));
//            ((CreatePromotion) loader.getController()).setUpper(this);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//    }
    void loadCreatePromotion(PromotionType type){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreatePromotionUI.fxml"));

        try {
            createPromotion = loader.load();
            setChildren(createPromotion, ">"+typeToTitle.get(type));
            CreatePromotion controller=loader.getController();
            controller.setUpper(this);
            controller.setType(type);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeToTitle.put(PromotionType.Combo,"制定促销策略（组合包）");
        typeToTitle.put(PromotionType.PriceOriented,"制定促销策略（针对总价）");
        typeToTitle.put(PromotionType.CustomerOriented,"制定促销策略（针对用户）");

        title.setOnMouseClicked(e->{
            subLabelBox.getChildren().clear();
            container.getChildren().setAll(mainPane);
        });

        MenuItem createComboBtn = new MenuItem("组合商品降价");
        MenuItem createPriceBtn = new MenuItem("针对总价");
        MenuItem createCustomerBtn = new MenuItem("针对用户");


        createComboBtn.setOnAction(e -> loadCreatePromotion(PromotionType.Combo));
        createPriceBtn.setOnAction(e-> loadCreatePromotion(PromotionType.PriceOriented));
        createCustomerBtn.setOnAction(e-> loadCreatePromotion(PromotionType.CustomerOriented));


        createPromotionMenu.getItems().setAll(createComboBtn, createCustomerBtn, createPriceBtn);

    }


}
