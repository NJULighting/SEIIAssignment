package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nju.lighting.bl.promotionbl.PromotionBLService_Stub;
import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.promotion.PromotionVO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/4.
 * Description
 * @author 陈俊宇
 */
public class PromotionManageController implements Initializable {
    private List<PromotionVO> promotions;
    private PromotionBLService promotionBLService = new PromotionBLService_Stub();
    private int itemsPerPage = 10;
    double PREF_WIDTH = 300, PREF_HEIGHT = 30;
    static PromotionVO selectedPromotion;

    @FXML
    Pagination pagination;

    @FXML
    JFXButton create;

    @FXML
    void create() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("createPromotion.fxml"))));
        stage.show();
    }


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
        promotionVOJFXListView.getStylesheets().add(0, getClass().getResource("../label.css").toExternalForm());
        System.out.println(promotionVOJFXListView.getStylesheets());
        return promotionVOJFXListView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        promotions = promotionBLService.getPromotionList();
        pagination.getStylesheets().add(getClass().getResource("../promotionManage.css").toExternalForm());
        pagination.setPageCount((promotions.size() / itemsPerPage) + 1);
        pagination.setPageFactory((Integer index) -> createPage(index));
    }

    class myListCell extends ListCell<PromotionVO> {
        Label name, type, time;
        HBox buttonBox;
        JFXButton openBtn, invalidBtn;


        @Override
        public void updateItem(PromotionVO item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                name = new Label(item.getName());
                type = new Label(item.getType().toString());
                time = new Label(DateHelper.approximateTime(item.getStartDate()) + " - " +
                        DateHelper.approximateTime(item.getEndDate()));
                openBtn = new JFXButton("打开");
                invalidBtn = new JFXButton("失效");
                buttonBox = new HBox(openBtn, invalidBtn);
                buttonBox.setVisible(false);
                buttonBox.setSpacing(25);
                buttonBox.setPadding(new Insets(0, 0, 0, 15));

                openBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        PromotionManageController.selectedPromotion = item;
                        try {
//                            new Promotion(item).init();
                            Promotion.promotion = item;
                            FXMLLoader.load(getClass().getResource("Promotion.fxml"));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                setGraphic(new HBox(name, type, time, buttonBox));
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
