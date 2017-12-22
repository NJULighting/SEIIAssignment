package nju.lighting.presentation.repositoryui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import nju.lighting.bl.repositorybl.RepositoryBLService_Stub;
import nju.lighting.blservice.repositoryblservice.RepositoryBLService;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.repository.RepositoryTableItemVO;
import nju.lighting.vo.repository.RepositoryTableVO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/21.
 * Description
 *
 * @author 陈俊宇
 */
public class RepositoryCounting implements Initializable {
    RepositoryBLService blService=new RepositoryBLService_Stub();
    List<RepositoryTableItemVO> repositoryTableList=blService.getRepositoryTable().getRepositoryTableItemVOS();


    @FXML
    TableView tableView;

    @FXML
    TableColumn index;
    @FXML
    TableColumn<RepositoryTableItemVO,String> commodity;

    @FXML
    TableColumn<RepositoryTableItemVO,String> modelNumber;

    @FXML
    TableColumn<RepositoryTableItemVO,Integer> repCount;

    @FXML
    TableColumn<RepositoryTableItemVO,Double> recentInPrice;

    @FXML
    TableColumn<RepositoryTableItemVO,String> batch;

    @FXML
    TableColumn<RepositoryTableItemVO,String> batchNumber;

    @FXML
    TableColumn<RepositoryTableItemVO,String> dateOfProduction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        commodity.setCellValueFactory(cellData->
        new SimpleStringProperty(cellData.getValue().getCommodityName()));
        modelNumber.setCellValueFactory(cellData->
        new SimpleStringProperty(cellData.getValue().getModelNumber()));
        repCount.setCellValueFactory(cellData->
        new SimpleIntegerProperty(cellData.getValue().getRepCount()).asObject());
        recentInPrice.setCellValueFactory(cellData->
        new SimpleDoubleProperty(cellData.getValue().getRecentInPrice()).asObject());
        batch.setCellValueFactory(cellData->
        new SimpleStringProperty(cellData.getValue().getBatch()));
        batchNumber.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getBatchNumber()));
        dateOfProduction.setCellValueFactory(cellData->
        new SimpleStringProperty(DateHelper.approximateTime(cellData.getValue().getDateOfProduction())));

        index.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setGraphic(null);
                            setText(null);
                        }else {
                            setText(getTableRow().getIndex()+1+"");
                        }
                    }
                };
            }
        });


        ObservableList observableList= FXCollections.observableArrayList();

        observableList.addAll(repositoryTableList.stream()
        .collect(Collectors.toList()));
        tableView.setItems(observableList);

        TableViewHelper.commonSet(tableView);
        tableView.getStylesheets().add(getClass().getResource("../repository.css").toExternalForm());
    }
}
