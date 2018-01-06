package nju.lighting.presentation.documentui.costdoc;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.documentui.Modifiable;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.AccountHelper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.account.AccountVO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import nju.lighting.vo.doc.costdoc.CostDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AddCostDocController implements Initializable,Modifiable {



    @FXML
    Button addItemBtn,addAccountBtn,commitBtn;

    @FXML
    JFXTextField accountText,balanceText,totalText;

    @FXML
    HBox tableContainer;

    @FXML
    StackPane stackPane;

    @FXML
    Pane mainPane;

    private SimpleObjectProperty<AccountVO> accountProperty=new SimpleObjectProperty<>();

    private ObservableList<CostDocItemVO> observableList;

    private SimpleDoubleProperty totalProperty=new SimpleDoubleProperty();

    private DocBLService docBLService= DocBLServiceFactory.getDocBLService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addAccountBtn.setOnAction(e-> AccountHelper.addAccount(accountProperty));

        FXMLLoader tableLoader=new FXMLLoader(getClass().getResource("CostItemList.fxml"));

        try {
            tableContainer.getChildren().add(tableLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        CostItemList tableController=tableLoader.getController();
        tableController.setEditable();

        observableList=tableController.getObservableList();

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

            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("AddItem.fxml"));
                Node node =loader.load();
                AddItem controller=loader.getController();
                DialogHelper.addDialog(node,stackPane,controller.AddItem(observableList));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        commitBtn.setOnAction(e-> {
           docBLService.commitDoc(getDoc());
        });
    }

    CostDocVO getDoc(){
        return new CostDocVO(new Date(), Client.getUserVO().getID(),
                accountProperty.getValue(), observableList);
    }

    @Override
    public void modify(Upper upper, DocVO docVO, boolean redFlush) {
        CostDocVO costDocVO=(CostDocVO)docVO;

        accountProperty.set(costDocVO.getAccount());
        observableList.setAll(costDocVO.getItemList());
        totalProperty.set(costDocVO.getTotal());

        if (!redFlush){
            commitBtn.setOnAction(e-> DocHelper.saveAndApprove(getDoc()));
        }
    }

    @Override
    public Node getMainPane() {
        return mainPane ;
    }
}
