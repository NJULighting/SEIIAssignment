package nju.lighting.presentation.logui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import nju.lighting.bl.logbl.LogBLService_Stub;
import nju.lighting.blservice.logblservice.LogBLService;
import nju.lighting.presentation.factory.LogBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.LogVO;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/30.
 * Description
 *
 * @author 陈俊宇
 */
public class LogController implements Initializable {
    Date startDate;
    Date endDate;

    List<LogVO> logVOArrayList;

    /*
    logVOArrayList中存储10页的内容，用CurrentListLevel标记当前logVOArrayList所含是第几个十页的，如一到十页level就为0
    主要用来与选定page进行比较，如过处于同一level就直接从logVoArrayList中拿，否则从数据层中拿到相应十页的内容
    并赋值给logVoArrayList，更新currentListLevel的值
     */
    int currentListLevel = 0;
    int itemsPerPage = 15;
    LogBLService logBLService = LogBLServiceFactory.getLogBLService();

    @FXML
    Pagination pagination;

    @FXML
    JFXDatePicker startDatePicker,endDatePicker;

    @FXML
    JFXButton okBtn;


    JFXListView createPage(int pageIndex) {
        ObservableList logList = FXCollections.observableArrayList();
        int left = logVOArrayList.size() - pageIndex * itemsPerPage;
        int max = (left > itemsPerPage) ? itemsPerPage : left;
//        int level = pageIndex / 10;

        HBox listCell;
        Label time, behavior, creator, identity;
        JFXListView listView = new JFXListView();

        for (int i = 0; i < max; i++) {
            LogVO currentLog = logVOArrayList.get(i);
            time = new Label(DateHelper.accurateTime(currentLog.getTime()));
            behavior = new Label(currentLog.getBehavior());
            creator = new Label(currentLog.getCreator());
            identity = new Label(currentLog.getIdentity().toString());
            listCell=new HBox(behavior,time,creator,identity);
            listCell.setSpacing(50);
            listView.getItems().add(listCell);
        }

        return listView;
    }


    void initPagination() {
        logVOArrayList = logBLService.getLogListByTime(startDate, endDate);
        pagination.setPageCount(logVOArrayList.size() / itemsPerPage + 1);
        pagination.setPageFactory((Integer index) -> createPage(index));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startDate = new Date();

        //endDate = DateHelper.weekAgo();
        initPagination();


        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (startDatePicker.getValue()!=null&&endDatePicker.getValue()!=null){
                    startDate=(DateHelper.localDateToDate(startDatePicker.getValue()));
                    endDate=DateHelper.localDateToDate(endDatePicker.getValue());

                }
            }
        });
    }

}
