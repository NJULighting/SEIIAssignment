package nju.lighting.presentation.documentui.accountiodoc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.documentui.AddDoc;
import nju.lighting.presentation.factory.ApprovalBLServiceFactory;
import nju.lighting.presentation.factory.CustomerBLServiceFactory;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.AccountHelper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.account.AccountVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import shared.DocType;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/26.
 * Description 添加收付款单的控制类
 *
 * @author 陈俊宇
 */
public class AddAccountIODoc extends AddDoc implements Initializable, Upper {

    @FXML
    JFXToggleButton toggleBtn;

    @FXML
    Pane mainPane;

    @FXML
    HBox container, tableContainer;

    @FXML
    Label sub;

    @FXML
    TextField customerText;

    @FXML
    Button addTransferItemBtn, chooseCustomerBtn;

    @FXML
    JFXButton commitBtn;

    @FXML
    StackPane stackPane;

    @FXML
    JFXTextField totalText;

    private SimpleObjectProperty<AccountVO> accountProperty = new SimpleObjectProperty<>();

    private ObservableList<AccountTransferItem> itemList;

    private SimpleDoubleProperty totalProperty=new SimpleDoubleProperty();

    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();

    private DocType type = DocType.ACCOUNT_OUT;



    protected AccountIODocVO getDoc() {
        if (customerProperty.getValue() != null && itemList.size() != 0) {
            return new AccountIODocVO(new Date(), type,
                    customerProperty.getValue().getID() + "",
                    Client.getUserVO().getID(),
                    itemList.stream()
                            .map(AccountTransferItem::toTransferItem)
                            .collect(Collectors.toList()));
        } else return null;
    }


    @FXML
    void chooseCustomer() {
        CustomerHelper.setCustomer(this, customerProperty);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        totalText.textProperty().bind(totalProperty.asString());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountTransferList.fxml"));
            tableContainer.getChildren().add(loader.load());
            AccountTransferList controller = loader.getController();
            controller.setEditable(totalProperty);
            itemList = controller.getObservableList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        accountProperty.addListener(new ChangeListener<AccountVO>() {
            @Override
            public void changed(ObservableValue<? extends AccountVO> observable, AccountVO oldValue, AccountVO newValue) {
                itemList.add(new AccountTransferItem(newValue));
            }
        });

        addTransferItemBtn.setOnAction(e -> {
            AccountHelper.addAccount(accountProperty);
        });

        customerProperty.addListener(new ChangeListener<CustomerVO>() {
            @Override
            public void changed(ObservableValue<? extends CustomerVO> observable, CustomerVO oldValue, CustomerVO newValue) {
                if (newValue != null)
                    customerText.setText(customerProperty.getValue().getName());
            }
        });

        toggleBtn.selectedProperty().addListener(c->{
            if (toggleBtn.isSelected()) {
                {
                    toggleBtn.setText("收款单");
                    type = DocType.ACCOUNT_IN;
                }
            } else {
                toggleBtn.setText("付款单");
                type = DocType.ACCOUNT_OUT;
            }
        });

        commitBtn.setOnAction(e -> DocHelper.commitDoc(getDoc()));

        TextFieldHelper.addDeleteMenuItem(customerText, customerProperty);


    }


    public void back() {
        setChildren(mainPane, "");
    }

    public void setChildren(Node node, String title) {
        container.getChildren().setAll(node);
        sub.setText(title);
    }


    @Override
    public void modify(Upper upper, DocVO docVO, boolean redFlush) {
        AccountIODocVO accountIODocVO = (AccountIODocVO) docVO;

        itemList.setAll(accountIODocVO.getTransferAccountList().stream()
                .map(AccountTransferItem::new)
                .collect(Collectors.toList()));

        toggleBtn.selectedProperty().setValue(docVO.getType() == DocType.ACCOUNT_IN);
        toggleBtn.setDisable(true);

        customerProperty.set(CustomerBLServiceFactory.getCustomerBLService().findCustomerByID(
                Integer.parseInt(accountIODocVO.getCustomer())
        ));
        customerText.setEditable(false);
        chooseCustomerBtn.setDisable(true);

        super.modify(upper, docVO, redFlush);

    }

}
