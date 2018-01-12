package nju.lighting.presentation.documentui.costdoc;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nju.lighting.presentation.documentui.DeleteBtnCell;
import nju.lighting.presentation.utils.CostDocHelper;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2018/1/5.
 * Description
 *
 * @author 陈俊宇
 */
public class CostItemList  implements Initializable{
    @FXML
    TableView<CostDocItemVO> tableView;

    @FXML
    TableColumn<CostDocItemVO,String> item,comments;

    @FXML
    TableColumn<CostDocItemVO,Double> amount;

    @FXML
    TableColumn deleteBtn;


    private ObservableList<CostDocItemVO> observableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setItems(observableList);
        item.setCellValueFactory(c->
                new SimpleStringProperty(CostDocHelper.typeToString(c.getValue().getType())));

        comments.setCellValueFactory(c->
                new SimpleStringProperty(c.getValue().getComment()));

        amount.setCellValueFactory(c->
                new SimpleDoubleProperty(c.getValue().getAmount()).asObject());

    }

    public void setEditable(){

        deleteBtn.setCellFactory(p->
                new DeleteBtnCell<>());
    }

    public ObservableList<CostDocItemVO> getObservableList() {
        return observableList;
    }
}
