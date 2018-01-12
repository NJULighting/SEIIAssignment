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
 * Description 日志查询的控制类
 *
 * @author 陈俊宇
 */
public class LogController implements Initializable {


    private int itemsPerPage = 15;
    private LogBLService logBLService = LogBLServiceFactory.getLogBLService();
    private ObservableList<LogVO> logList = FXCollections.observableArrayList();

    @FXML
    Pagination pagination;

    @FXML
    JFXDatePicker startDatePicker, endDatePicker;

    @FXML
    JFXButton okBtn;

    @FXML
    JFXListView listView;


    private HBox createPage(int pageIndex) {

        int left = logList.size() - pageIndex * itemsPerPage;
        int max = (left > itemsPerPage) ? itemsPerPage : left;

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

    private void refresh() {
        Date endDate = DateHelper.localDateToDate(endDatePicker.getValue());
        endDate.setDate(endDate.getDate() + 1);
        logList.setAll(logBLService.getLogListByTime(
                DateHelper.localDateToDate(startDatePicker.getValue()),
                endDate));
    }

    private void initPagination() {
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
