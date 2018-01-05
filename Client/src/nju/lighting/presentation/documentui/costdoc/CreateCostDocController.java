package nju.lighting.presentation.documentui.costdoc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.documentui.BtnCell;
import nju.lighting.presentation.utils.AccountHelper;
import nju.lighting.presentation.utils.CostDocHelper;
import nju.lighting.vo.account.AccountVO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import shared.Result;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateCostDocController implements Initializable {


    @FXML
    TableView<CostDocItemVO> tableView;

    @FXML
    TableColumn<CostDocItemVO,String> item,comments;

    @FXML
    TableColumn<CostDocItemVO,Double> amount;

    @FXML
    TableColumn deleteBtn;

    @FXML
    Button addItemBtn,addAccountBtn,commitBtn;

    @FXML
    JFXTextField accountText,balanceText,totalText;

    @FXML
    StackPane stackPane;

    private SimpleObjectProperty<AccountVO> accountProperty=new SimpleObjectProperty<>();

    private ObservableList<CostDocItemVO> observableList= FXCollections.observableArrayList();

    private SimpleDoubleProperty totalProperty=new SimpleDoubleProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addAccountBtn.setOnAction(e-> AccountHelper.addAccount(stackPane,accountProperty));

        tableView.setItems(observableList);
        item.setCellValueFactory(c->
        new SimpleStringProperty(CostDocHelper.typeToString(c.getValue().getType())));

        comments.setCellValueFactory(c->
        new SimpleStringProperty(c.getValue().getComment()));

        amount.setCellValueFactory(c->
        new SimpleDoubleProperty(c.getValue().getAmount()).asObject());

        deleteBtn.setCellFactory(p->
        new BtnCell<>());

        accountProperty.addListener(c->{
            balanceText.setText(accountProperty.getValue().getAmount()+"");
            accountText.setText(accountProperty.getValue().getName());
        });

        observableList.addListener((ListChangeListener<? super CostDocItemVO>) c->{
            totalProperty.set(observableList.stream()
            .mapToDouble(x-> x.getAmount())
            .sum());
        });

        totalText.textProperty().bind(totalProperty.asString());


        addItemBtn.setOnAction(e->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddItem.fxml"));
            try {
                Node node =loader.load();
                AddItem controller=loader.getController();
                DialogHelper.addDialog(node,stackPane,controller.AddItem(observableList));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        commitBtn.setOnAction(e-> {
           //提交现金费用单
        });
    }

}
