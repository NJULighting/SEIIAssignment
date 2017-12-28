package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Callback;
import nju.lighting.bl.promotionbl.PromotionBLService_Stub;
import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.PromotionHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.promotion.PromotionVO;
import shared.PromotionType;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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

    @FXML
    TableView<PromotionVO> tableView;

    @FXML
    TableColumn<PromotionVO, String> promotionName;

    @FXML
    TableColumn<PromotionVO, String> type;
    @FXML
    TableColumn<PromotionVO, String> creator;
    @FXML
    TableColumn<PromotionVO, String> startDate;
    @FXML
    TableColumn<PromotionVO, Date> endDate;

    @FXML
    TableColumn openBtn;

    ObservableList<PromotionVO> promotionList = FXCollections.observableArrayList();
    PromotionBLService blService = new PromotionBLService_Stub();

    Node createPromotion;

    HashMap<PromotionType, String> typeToTitle = new HashMap<>();

    public void back() {
        container.getChildren().setAll(createPromotion);
        ObservableList<Node> child = subLabelBox.getChildren();
        subLabelBox.getChildren().remove(1, subLabelBox.getChildren().size());
    }

    public void backToMain(){
        container.getChildren().setAll(mainPane);
        subLabelBox.getChildren().clear();
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

    void loadCreatePromotion(PromotionType type) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreatePromotionUI.fxml"));

        try {
            createPromotion = loader.load();
            setChildren(createPromotion, ">" + typeToTitle.get(type));
            CreatePromotion controller = loader.getController();
            controller.setUpper(this);
            controller.setType(type);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void refresh() {
        promotionList.setAll(blService.getPromotionList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        refresh();

        promotionName.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getName()));
        type.setCellValueFactory(c ->
                new SimpleStringProperty(PromotionHelper.typeToString(c.getValue().getType())));
        creator.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getCreator().getUsername()));
        startDate.setCellValueFactory(c ->
                new SimpleStringProperty(DateHelper.approximateTime(c.getValue().getStartDate())));
        endDate.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getEndDate()));

        endDate.setCellFactory(new Callback<TableColumn<PromotionVO, Date>, TableCell<PromotionVO, Date>>() {
            @Override
            public TableCell<PromotionVO, Date> call(TableColumn<PromotionVO, Date> param) {
                return new TableCell<PromotionVO, Date>(){
                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty){
                            setText(null);
                            setGraphic(null);
                        }else {
                            setText(DateHelper.approximateTime(getItem()));
                            if (getItem().before(new Date()))
                                setTextFill(Color.RED);
                            else
                                setTextFill(Color.BLACK);
                        }
                    }
                };
            }
        });
        tableView.setItems(promotionList);

        openBtn.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new TableCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {


                            JFXButton open = new JFXButton("打开");
                            JFXButton invalidate = new JFXButton("失效");

                            HBox buttonBox = new HBox(open, invalidate);
                            buttonBox.setVisible(false);
                            buttonBox.setSpacing(30);
                            buttonBox.setAlignment(Pos.CENTER_LEFT);
                            setGraphic(buttonBox);
                            setText(null);


                            getTableRow().setOnMouseExited(e -> buttonBox.setVisible(false));
                            getTableRow().setOnMouseEntered(e -> buttonBox.setVisible(true));


                            invalidate.setOnAction(e->{
                                PromotionVO promotion=(PromotionVO) getTableView().getItems().get(getIndex());
                                promotion.setEndDate(new Date());
                                blService.modify(promotion);
                                getTableView().getItems().set(getIndex(),promotion);
                            });
                            //open.setOnAction();
                        }
                    }
                };
            }
        });

        typeToTitle.put(PromotionType.Combo, "制定促销策略（组合包）");
        typeToTitle.put(PromotionType.PriceOriented, "制定促销策略（针对总价）");
        typeToTitle.put(PromotionType.CustomerOriented, "制定促销策略（针对用户）");

        title.setOnMouseClicked(e -> {
            backToMain();
        });

        MenuItem createComboBtn = new MenuItem("组合商品降价");
        MenuItem createPriceBtn = new MenuItem("针对总价");
        MenuItem createCustomerBtn = new MenuItem("针对用户");


        createComboBtn.setOnAction(e -> loadCreatePromotion(PromotionType.Combo));
        createPriceBtn.setOnAction(e -> loadCreatePromotion(PromotionType.PriceOriented));
        createCustomerBtn.setOnAction(e -> loadCreatePromotion(PromotionType.CustomerOriented));


        createPromotionMenu.getItems().setAll(createComboBtn, createCustomerBtn, createPriceBtn);
        TableViewHelper.commonSet(tableView);
        TableViewHelper.setHeight(tableView);

    }


}
