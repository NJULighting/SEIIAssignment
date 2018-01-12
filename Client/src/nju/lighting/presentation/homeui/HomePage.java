package nju.lighting.presentation.homeui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocType;
import shared.DocumentFilter;
import shared.ResultMessage;

import java.net.URL;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 2018/1/1.
 * Description 除总经理和系统管理员之外人的主页
 *
 * @author 陈俊宇
 */
public class HomePage implements Initializable {
    @FXML
    private JFXListView<HistoryDocVO> listView;

    @FXML
    private Pane docContainer;

    @FXML
    Label comments;

    private DocBLService blService = DocBLServiceFactory.getDocBLService();

    private ObservableList<HistoryDocVO> observableList = FXCollections.observableArrayList();

    private DocumentFilter.Builder builder = new DocumentFilter.Builder();


    @FXML
    void refresh() {
        builder.endDate(new Date());
        observableList.setAll(blService.findDocuments(builder.build()));
        observableList.sort(new Comparator<HistoryDocVO>() {
            @Override
            public int compare(HistoryDocVO o1, HistoryDocVO o2) {
                Date time1 = o1.getDocVO().getTime();
                Date time2 = o2.getDocVO().getTime();
                if (time1.after(time2))
                    return -1;
                else return 1;
            }
        });
    }

    @FXML
    void clicked() {
        HistoryDocVO historyDocVO = listView.getSelectionModel()
                .getSelectedItems().get(0);
        System.out.println("comments" + historyDocVO.getComment());
        DocHelper.showDoc(historyDocVO.getDocVO(), docContainer);
        comments.setText(getComments(historyDocVO.getComment()));
    }

    private String getComments(String comments) {
        if (comments == null || comments.length() == 0)
            return "无";
        else return comments;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        builder.creatorID(Client.getUserVO().getID())
                .startDate(DateHelper.localDateToDate(DateHelper.weekAgo()))
                .endDate(new Date());

        refresh();

        listView.setItems(observableList);


        listView.setCellFactory(p ->
                new JFXListCell<HistoryDocVO>() {
                    @Override
                    protected void updateItem(HistoryDocVO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {

                            MenuItem discard = new MenuItem("丢弃报警单");
                            discard.setOnAction(e -> {
                                EventHandler eventHandler = event -> {
                                    ResultMessage resultMessage = blService.expireAlertDoc((AlertDocVO) item.getDocVO());
                                    DialogHelper.dialog("丢弃", resultMessage, MainUI.getStackPane());
                                    if (resultMessage.success())
                                        refresh();
                                };

                                Label label = new Label("你确定要删除该报警单？");
                                label.setFont(Font.font(18));
                                DialogHelper.addDialog(label, MainUI.getStackPane(), eventHandler);
                            });

                            if (item.getDocVO().getType() == DocType.ALERT) {
                                setContextMenu(new ContextMenu(discard));
                            } else
                                setContextMenu(null);


                            Label type = new Label(getItem().getDocVO().getType().toString());
                            type.setPrefSize(80, 30);
                            type.setFont(Font.font(14));
                            Label state = new Label(getItem().getState().toString());
                            state.setPrefHeight(30);
                            state.setFont(Font.font(14));
                            HBox itemBox = new HBox(type, state);
                            itemBox.setSpacing(50);

                            setGraphic(itemBox);
                            setText(null);
                        }
                    }
                });

    }
}
