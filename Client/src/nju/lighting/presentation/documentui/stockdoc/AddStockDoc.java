package nju.lighting.presentation.documentui.stockdoc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.Modifiable;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;
import nju.lighting.vo.doc.stockdoc.StockDocVO;
import nju.lighting.vo.doc.stockdoc.StockReturnDocVO;
import shared.CustomerType;
import shared.Result;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/24.
 * Description
 *
 * @author 陈俊宇
 */
public class AddStockDoc implements Initializable, Upper, Modifiable {
    @FXML
    HBox container;
    @FXML
    VBox verticalVBox;
    @FXML
    Pane mainPane, commodityContainer;
    @FXML
    private Button chooseCustomerBtn, chooseCommodityBtn;
    @FXML
    private JFXTextField customer, amount;
    @FXML
    private TextArea remarks;
    @FXML
    private Label sub, title;

    @FXML
    JFXComboBox<UserVO> salesmanBox;

    @FXML
    JFXComboBox<String> repositoryBox;

    @FXML
    JFXButton commitBtn;

    private Upper upper = this;
    private ObservableList<CommodityItem> docItemList = FXCollections.observableArrayList();
    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();
    private DocBLService blService= DocBLServiceFactory.getDocBLService();


    public void back() {
        setChildren(mainPane, "");
    }

    @Override
    public void setChildren(Node node, String title) {
        container.getChildren().setAll(node);
        sub.setText(title);
    }

    private StockDocVO getDoc() {
        if (customerProperty.getValue() != null && docItemList.size() != 0)
            return new StockDocVO(new Date(), Client.getUserVO().getID(),
                    customerProperty.getValue().getID() + "",
                    repositoryBox.getValue(),
                    remarks.getText(),
                    docItemList.stream()
                            .map(CommodityItem::toStockDocItem)
                            .collect(Collectors.toList())
            );
        else return null;
    }

    private StockReturnDocVO getReturnDoc(){
        if (customerProperty.getValue() != null && docItemList.size() != 0)
            return new StockReturnDocVO(new Date(), Client.getUserVO().getID(),
                    customerProperty.getValue().getID() + "",
                    repositoryBox.getValue(),
                    remarks.getText(),
                    docItemList.stream()
                            .map(CommodityItem::toStockDocItem)
                            .collect(Collectors.toList())
            );
        else return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UserHelper.intiUserBox(salesmanBox);
        repositoryBox.getItems().add("默认仓库");
        repositoryBox.setValue(repositoryBox.getItems().get(0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../CommodityList.fxml"));

        try {
            commodityContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommodityList controller = loader.getController();
        controller.setEditable();
        controller.setMaxSize(480, 700);

        docItemList = controller.getGiftObservableList();
        ObservableList<BasicCommodityItemVO> commodityList = FXCollections.observableArrayList();
        chooseCommodityBtn.setOnAction(e -> CommodityHelper.chooseCommodity(upper, commodityList));

        commodityList.addListener((ListChangeListener<? super BasicCommodityItemVO>) c -> {
            while (c.next()) {
                docItemList.addAll(c.getAddedSubList().stream()
                        .map(x -> new CommodityItem(x, 1))
                        .collect(Collectors.toList()));
            }
        });


        chooseCustomerBtn.setOnAction(e -> CustomerHelper.setCustomer(upper, customerProperty, CustomerType.SUPPLIER));
        customerProperty.addListener(c ->{
            customer.setText(customerProperty.getValue().getName());
            salesmanBox.setValue(UserHelper.getSalesman(salesmanBox,customerProperty.getValue().getSalesman()));
        });


        amount.textProperty().bind(controller.getTotal().asString());

        commitBtn.setOnAction(e -> {
            if (getDoc()!=null){
                Result<DocVO> result =blService.commitDoc(getDoc());
                DialogHelper.dialog("提交进货单",result.getResultMessage(), MainUI.getStackPane());
            }

        });
    }

    void setReturn() {
        title.setText("制定进货退货单");
        commitBtn.setOnAction(e -> {
            if (getReturnDoc()!=null){
                Result<DocVO> result =blService.commitDoc(getReturnDoc());
                DialogHelper.dialog("提交进货退货单",result.getResultMessage(), MainUI.getStackPane());
            }
        });
    }

    void init(Upper upper, List<StockDocItemVO> itemList, String customerID, String remarks) {
        this.upper = upper;
        docItemList.setAll(itemList.stream()
                .map(CommodityItem::new)
                .collect(Collectors.toList()));

        customerProperty.set(CustomerHelper.getCustomer(Integer.parseInt(customerID)));

        this.remarks.setText(remarks);

    }

    @Override
    public void modify(Upper upper, DocVO docVO, boolean redFlush) {
        StockDocVO stockDoc = (StockDocVO) docVO;

        init(upper, (stockDoc).getItems(), stockDoc.getCustomerId(), stockDoc.getRemarks());
    }

    @Override
    public Node getMainPane() {
        return mainPane;
    }
}
