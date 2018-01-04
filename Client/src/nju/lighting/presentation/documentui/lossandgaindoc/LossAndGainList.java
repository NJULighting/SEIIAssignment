package nju.lighting.presentation.documentui.lossandgaindoc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import nju.lighting.presentation.documentui.EditingCell;
import nju.lighting.presentation.utils.ImageViewHelper;
import nju.lighting.presentation.utils.RepositoryHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import shared.LossAndGainItemType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/25.
 * Description
 * @author 陈俊宇
 */
public class LossAndGainList implements Initializable {
    ObservableList<LossAndGainItem> data;

    @FXML
    TableView tableView;

    @FXML
    TableColumn<LossAndGainItem, String> name;

    @FXML
    TableColumn<LossAndGainItem, String> id;

    @FXML
    TableColumn<LossAndGainItem, Integer> count;

    @FXML
    TableColumn<LossAndGainItem, Integer> repCount;

    @FXML
    TableColumn<LossAndGainItem, LossAndGainItemType> type;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();

        name.setCellValueFactory(p ->
                p.getValue().nameProperty());
        id.setCellValueFactory(p ->
                p.getValue().idProperty());
        count.setCellValueFactory(p ->
                p.getValue().countProperty().asObject());
        repCount.setCellValueFactory(p ->
                p.getValue().repCountProperty().asObject());
        type.setCellValueFactory(p ->
                p.getValue().typeProperty());

        type.setCellFactory(new Callback<TableColumn<LossAndGainItem, LossAndGainItemType>, TableCell<LossAndGainItem, LossAndGainItemType>>() {
            @Override
            public TableCell<LossAndGainItem, LossAndGainItemType> call(TableColumn<LossAndGainItem, LossAndGainItemType> param) {
                return new TableCell<LossAndGainItem, LossAndGainItemType>() {
                    @Override
                    protected void updateItem(LossAndGainItemType item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(RepositoryHelper.lossAndGainTypeToString(getItem()));
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        tableView.setItems(data);
        TableViewHelper.commonSet(tableView);
        TableViewHelper.setHeight(tableView, 0.9);

    }

    public void setEditable() {
        type.setCellFactory(p ->
                new TableCell<LossAndGainItem, LossAndGainItemType>() {
                    boolean isGain() {
                        if (getItem().equals(LossAndGainItemType.GAIN))
                            return true;
                        else return false;
                    }

                    @Override
                    protected void updateItem(LossAndGainItemType item, boolean empty) {
                        JFXToggleButton toggleButton = new JFXToggleButton();
                        toggleButton.setSize(10);
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            toggleButton.setSelected(isGain());
                            if (isGain()) {
                                toggleButton.setText("报溢");
                            } else
                                toggleButton.setText("报损");

                            toggleButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                //如果选中 就是报溢
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    if (newValue) {
                                        getTableView().getItems().get(getIndex()).setType(LossAndGainItemType.GAIN);
                                    } else {
                                        getTableView().getItems().get(getIndex()).setType(LossAndGainItemType.LOSS);
                                    }

                                }

                            });

                            JFXButton button = new JFXButton("");
                            button.setGraphic(ImageViewHelper.delete());

                            button.setOnAction(e -> {
                                getTableView().getItems().remove(getIndex());
                            });

                            HBox buttonBox = new HBox(toggleButton, button);
                            buttonBox.setMinHeight(40);
                            buttonBox.setSpacing(10);
                            buttonBox.setAlignment(Pos.CENTER_LEFT);

                            setText(null);
                            setGraphic(buttonBox);
                        }
                    }
                }
        );

        Callback<TableColumn<LossAndGainItem, Integer>,
                TableCell<LossAndGainItem, Integer>> cellFactoryForCount
                = (p) -> (new EditingCell<LossAndGainItem, Integer>("int"));

        count.setCellFactory(cellFactoryForCount);
        count.setOnEditCommit((TableColumn.CellEditEvent<LossAndGainItem, Integer> t) -> {
            LossAndGainItem selected = t.getTableView().getItems().get(
                    t.getTablePosition().getRow());
            selected.setCount(t.getNewValue());

        });
    }

    public void setAlert() {
        count.setText("警戒数量");
        tableView.getColumns().remove(type);
    }


    public ObservableList<LossAndGainItem> getData() {
        return data;
    }
}
