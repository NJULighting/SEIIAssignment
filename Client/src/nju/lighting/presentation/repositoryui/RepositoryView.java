package nju.lighting.presentation.repositoryui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import nju.lighting.blservice.repositoryblservice.RepositoryBLService;
import nju.lighting.presentation.factory.RepositoryBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.RepositoryHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.repository.RepositoryChangeVO;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/21.
 * Description
 * 库存查看
 *
 * @author 陈俊宇
 */
public class RepositoryView implements Initializable {

    List<RepositoryChangeVO> repositoryChangeList;
    RepositoryBLService blService = RepositoryBLServiceFactory.getRepositoryBLService();
    int itemsPerPage = 15;

    @FXML
    Pagination pagination;
    @FXML
    TableView tableView;
    @FXML
    JFXButton refreshBtn;

    @FXML
    JFXDatePicker startDate, endDate;

    @FXML
    TableColumn<RepositoryChangeVO, String> commodity;

    @FXML
    TableColumn<RepositoryChangeVO, String> type;

    @FXML
    TableColumn<RepositoryChangeVO, Integer> count;

    @FXML
    TableColumn<RepositoryChangeVO, Double> amount;

    @FXML
    TableColumn<RepositoryChangeVO, String> date;


    HBox createPage(int index) {

        ObservableList<RepositoryChangeVO> data = FXCollections.observableArrayList();


        int left = repositoryChangeList.size() - index * itemsPerPage;
        int max = (left > itemsPerPage) ? itemsPerPage : left;

        for (int i = 0; i < max; i++) {
            data.add(repositoryChangeList.get(index * itemsPerPage + i));
        }

        tableView.setItems(data);
        TableViewHelper.commonSet(tableView);

        return new HBox();
    }


    void refresh() {
        pagination.setPageCount(repositoryChangeList.size() / itemsPerPage + 1);
        pagination.setCurrentPageIndex(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        repositoryChangeList = blService.getRepositoryChanges(DateHelper.weekAgo(), new Date());

        pagination.setPageFactory((Integer index) -> createPage(index));
        pagination.getStylesheets().add(getClass().getResource("../repository.css").toExternalForm());

        commodity.setCellValueFactory(cellData ->
               new SimpleStringProperty( cellData.getValue().getCommodityName()));
        type.setCellValueFactory(cellData ->
                new SimpleStringProperty(RepositoryHelper.typeToString( cellData.getValue().getType())));
        count.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getCount()).asObject());
        amount.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());
        date.setCellValueFactory(cellData ->
                new SimpleStringProperty(DateHelper.approximateTime(cellData.getValue().getDate())));

        refresh();

        refreshBtn.setOnAction(e -> {
            if (endDate.getValue() != null && startDate.getValue() != null)
                repositoryChangeList = blService.getRepositoryChanges(
                        DateHelper.localDateToDate(startDate.getValue()),
                        DateHelper.localDateToDate(endDate.getValue())
                );
            else
                repositoryChangeList = blService.getRepositoryChanges(DateHelper.weekAgo(), new Date());
            refresh();
        });
    }
}
