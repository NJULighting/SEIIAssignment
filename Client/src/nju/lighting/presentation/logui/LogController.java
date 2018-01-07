package nju.lighting.presentation.logui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import nju.lighting.blservice.logblservice.LogBLService;
import nju.lighting.presentation.factory.LogBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.LogVO;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/30.
 * Description
 *
 * @author 陈俊宇
 */
public class LogController implements Initializable {

    /*
    logVOArrayList中存储10页的内容，用CurrentListLevel标记当前logVOArrayList所含是第几个十页的，如一到十页level就为0
    主要用来与选定page进行比较，如过处于同一level就直接从logVoArrayList中拿，否则从数据层中拿到相应十页的内容
    并赋值给logVoArrayList，更新currentListLevel的值
     */
    int currentListLevel = 0;
    private int itemsPerPage = 15;
    private LogBLService logBLService = LogBLServiceFactory.getLogBLService();
    ObservableList<LogVO> logList = FXCollections.observableArrayList();

    @FXML
    Pagination pagination;

    @FXML
    JFXDatePicker startDatePicker, endDatePicker;

    @FXML
    JFXButton okBtn;

    @FXML
    JFXListView listView;


    HBox createPage(int pageIndex) {

        int left = logList.size() - pageIndex * itemsPerPage;
        int max = (left > itemsPerPage) ? itemsPerPage : left;
//        int level = pageIndex / 10;

        HBox listCell;
        listView.getItems().clear();
        Label time, behavior, creator, identity;
        for (int i = 0; i < max; i++) {
            LogVO currentLog = logList.get(pageIndex * itemsPerPage + i);
            time = new Label(DateHelper.accurateTime(currentLog.getTime()));
            time.setPrefWidth(150);
            behavior = new Label(currentLog.getBehavior());
            behavior.setPrefWidth(550);
            creator = new Label(UserHelper.getUser(currentLog.getCreatorId()).toString());
            creator.setPrefWidth(100);
            identity = new Label(currentLog.getIdentity().toString());
            identity.setPrefWidth(150);
            listCell = new HBox(behavior, time, creator, identity);
            listCell.setSpacing(10);
            listView.getItems().add(listCell);
        }

        return new HBox();
    }

    void refresh() {
        Date endDate = DateHelper.localDateToDate(endDatePicker.getValue());
        endDate.setDate(endDate.getDate() + 1);
        logList.setAll(logBLService.getLogListByTime(
                DateHelper.localDateToDate(startDatePicker.getValue()),
                endDate));
    }

    void initPagination() {
        refresh();
        pagination.setPageCount(logList.size() / itemsPerPage + 1);
        pagination.setPageFactory((Integer index) -> createPage(index));
        pagination.setCurrentPageIndex(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateHelper.setDefaultTime(startDatePicker, DateHelper.weekAgo());
        DateHelper.setDefaultTime(endDatePicker, DateHelper.dateToLocalDate(new Date()));

        initPagination();


        okBtn.setOnAction(e -> initPagination());
    }
}
