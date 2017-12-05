package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import nju.lighting.bl.promotionbl.PromotionBLService_Stub;
import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.presentation.utils.DateFormat;
import nju.lighting.vo.promotion.PromotionVO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/4.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionManageController implements Initializable {
    ArrayList<PromotionVO> promotions;
    PromotionBLService promotionBLService = new PromotionBLService_Stub();
    int itemsPerPage = 10;
    double PREF_WIDTH = 300, PREF_HEIGHT = 30;
    static PromotionVO selectedPromotion;

    @FXML
    Pagination pagination;




//    JFXListView createPage(int index){
//        JFXListView listView=new JFXListView();
//        Label name,type,time;
//        HBox buttonBox;
//        JFXButton openBtn,invalidBtn;
//        HBox listCell;
//        int left=promotions.size()-index*itemsPerPage;
//
//        int max=(left>itemsPerPage)?itemsPerPage:left;
//
//        for(int i=0;i<max;i++){
//
//            PromotionVO currentPromotion=promotions.get(index*itemsPerPage+i);
//
//            name=new Label(currentPromotion.getName());
//            //name.setPrefSize(PREF_WIDTH,PREF_HEIGHT);
//            type=new Label(currentPromotion.getType().toString());
//            time=new Label(currentPromotion.getStartDate().toString()+" - "+
//            currentPromotion.getEndDate());
//            openBtn=new JFXButton("打开");
//            invalidBtn=new JFXButton("失效");
//            buttonBox=new HBox(openBtn,invalidBtn);
//            buttonBox.setVisible(false);
//
//            listCell=new HBox();
//            listCell=new HBox(name,type,time,buttonBox);
//
//            listView.getItems().add(listCell);
//
//        }
//
//        listView.getStylesheets().add(getClass().getResource("../label.css").toExternalForm());
//        //listView.setCellFactory(Label);
//        return listView;
//    }
//
//    int findHovered(JFXListView listView){
//        for (int i=0;i<listView.getItems().size();i++){
//            if (listView.)
//        }
//    }

    ListView createPage(int index) {
        ListView<PromotionVO> promotionVOJFXListView = new JFXListView<>();
        ObservableList<PromotionVO> data = FXCollections.observableArrayList();


        int left = promotions.size() - index * itemsPerPage;
        int max = (left > itemsPerPage) ? itemsPerPage : left;

        for (int i = 0; i < max; i++) {
            data.add(promotions.get(index * itemsPerPage + i));
        }

        promotionVOJFXListView.setItems(data);
        promotionVOJFXListView.setCellFactory((ListView<PromotionVO> l) -> new myListCell());
        System.out.println(promotionVOJFXListView.getStylesheets());
        promotionVOJFXListView.getStylesheets().add(0,getClass().getResource("../label.css").toExternalForm());
        System.out.println(promotionVOJFXListView.getStylesheets());
        return promotionVOJFXListView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        promotions = promotionBLService.getPromotionList();
        pagination.getStylesheets().add(getClass().getResource("../pagination.css").toExternalForm());
        pagination.setPageCount((promotions.size() / itemsPerPage) + 1);
        // pagination=new Pagination((promotions.size()/itemsPerPage)+1);
        pagination.setPageFactory((Integer index) -> createPage(index));


    }


    static class myListCell extends ListCell<PromotionVO> {
        Label name,type,time;
        HBox buttonBox;
        JFXButton openBtn,invalidBtn;


        @Override
        public void updateItem(PromotionVO item, boolean empty) {
            super.updateItem(item,empty);
            if (item != null) {
                name=new Label(item.getName());
                type=new Label(item.getType().toString());
                time=new Label(DateFormat.toString(item.getStartDate())+" - "+
                        DateFormat.toString(item.getEndDate()));
                openBtn=new JFXButton("打开");
                invalidBtn=new JFXButton("失效");
                buttonBox=new HBox(openBtn,invalidBtn);
                buttonBox.setVisible(false);
                buttonBox.setSpacing(25);
                buttonBox.setPadding(new Insets(0,0,0,150));

                openBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        PromotionManageController.selectedPromotion=item;
                        System.out.println(PromotionManageController.selectedPromotion.getType());
                        System.out.println(PromotionManageController.selectedPromotion);
                        try {
                            new Promotion(item);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                setGraphic(new HBox(name,type,time,buttonBox));
                setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        buttonBox.setVisible(true);
                    }
                });

                setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        buttonBox.setVisible(false);
                    }
                });
            }


        }




    }
}
