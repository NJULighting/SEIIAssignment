package nju.lighting.presentation.homeui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocumentFilter;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 2018/1/1.
 * Description
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



    void refresh() {
        observableList.setAll(blService.findDocuments(builder.build()));
    }

    @FXML
    void clicked(){
        HistoryDocVO historyDocVO=listView.getSelectionModel()
                .getSelectedItems().get(0);
        DocHelper.showDoc(historyDocVO.getDocVO(),docContainer);
        comments.setText(getComments(historyDocVO.getComment()));
    }

    private String getComments(String comments){
       if (comments==null||comments.length()==0)
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
                        if (empty){
                            setText(null);
                            setGraphic(null);
                        }else {
                            Label type=new Label(getItem().getDocVO().getType().toString());
                            type.setPrefSize(80,30);
                            type.setFont(Font.font(14));
                            Label state=new Label(DocHelper.stateToString(getItem().getState()));
                            state.setPrefHeight(30);
                            state.setFont(Font.font(14));
                            HBox itemBox=new HBox(type,state);
                            itemBox.setSpacing(50);

                            setGraphic(itemBox);
                            setText(null);
                        }
                    }
                });

    }
}
