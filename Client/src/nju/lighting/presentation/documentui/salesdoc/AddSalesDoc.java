package nju.lighting.presentation.documentui.salesdoc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import nju.lighting.bl.documentbl.DocController;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.Modifiable;
import nju.lighting.presentation.factory.PromotionBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.PromotionHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerType;
import shared.ResultMessage;
import shared.TwoTuple;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddSalesDoc implements Initializable, Upper, Modifiable {
    private PromotionBLService promotionBLService =
            PromotionBLServiceFactory.getPromotionBLService();
    @FXML
    private HBox container, promotionBox;
    @FXML
    private VBox verticalVBox;
    @FXML
    private Pane mainPane, commodityContainer;

    @FXML
    private Button choseCustomBtn, commitBtn, promotionBtn;
    @FXML
    private JFXButton commodityBtn;
    @FXML
    private JFXTextField customer, salesman, accountBeforeDis, discount,
            voucher, account, promotionText;

    @FXML
    JFXComboBox repositoryBox;
    @FXML
    private TextArea remarks;
    @FXML
    private Label sub, title;


    private ObservableList<BasicCommodityItemVO> commodityList = FXCollections.observableArrayList();
    private ObservableList<CommodityItem> docItemList;
    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<PromotionVO> promotionProperty = new SimpleObjectProperty<>();
    private CommodityList commodityListController;
    private PromotionVO promotionVO;
    private CustomerType type = CustomerType.SALESPERSON;
    private DocBLService docBLService = new DocController();
    private Upper upper = this;

    public void chooseCommodity() {
        CommodityHelper.chooseCommodity(upper, commodityList);
    }

    public void chooseCustomer() {
        CustomerHelper.setCustomer(upper, customerProperty, CustomerType.SUPPLIER);
    }


    public void choosePromotion() throws IOException {
        PromotionHelper.setPromotion(upper, promotionProperty, promotionBLService.getBenefitsPlan(
                customerProperty.getValue().getGrade(),
                commodityList.stream()
                        .map(BasicCommodityItemVO::getId)
                        .collect(Collectors.toList()),
                commodityListController.getTotal().doubleValue()
        ));

    }


    public void back() {
        setChildren(mainPane, "");
    }

    @Override
    public void setChildren(Node node, String title) {
        container.getChildren().setAll(node);
        sub.setText(title);
    }

    private SalesDocVO getDoc() {
        if (customer.validate() & salesman.validate() & docItemList.size() > 0)
            return new SalesDocVO(new Date(), Client.getUserVO().getID(), customerProperty.getValue().getID(),
                    salesman.getText(), repositoryBox.getValue().toString(), remarks.getText(),
                    Double.parseDouble(discount.getText()),
                    Double.parseDouble(voucher.getText()),
                    docItemList.stream()
                            .map(CommodityItem::toSalesDocItem)
                            .collect(Collectors.toList()));
        else return null;
    }

    @FXML
    private void commit() {
        if (getDoc() != null) {
            TwoTuple<ResultMessage, String> res = docBLService.commitDoc(getDoc());
            DialogHelper.dialog("提交销售单",res.t, MainUI.getStackPane());
        }

    }


    //当重要参数发生变化时清除正在作用的promotion
    private void clearPromotion() {
//        commodityList.removeAll(
//                commodityList.stream()
//                .filter(x-> x.isGift())
//                .collect(Collectors.toList())
//        );
        //  promotionOff.setText(0+"");
        if (promotionText != null)
            promotionText.setText("");
        promotionVO = null;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        repositoryBox.getItems().add("仓库一");
        repositoryBox.setValue(repositoryBox.getItems().get(0));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../CommodityList.fxml"));
        try {
            commodityContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        commodityListController = loader.getController();
        commodityListController.setEditable();
        commodityListController.setMaxSize(480, 700);

        docItemList = commodityListController.getGiftObservableList();

        customer.textProperty().addListener(c -> {
            if (customer.getText() != "")
                promotionBtn.setDisable(false);
        });

        commodityList.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()) {
                    docItemList.addAll(c.getAddedSubList().stream()
                            .map(x -> new CommodityItem(x, 1))
                            .collect(Collectors.toList()));
                }
            }
        });

        customerProperty.addListener(new ChangeListener<CustomerVO>() {
            @Override
            public void changed(ObservableValue<? extends CustomerVO> observable, CustomerVO oldValue, CustomerVO newValue) {
                customer.setText(customerProperty.getValue().getName());
                clearPromotion();
            }
        });

        promotionProperty.addListener(new ChangeListener<PromotionVO>() {
            @Override
            public void changed(ObservableValue<? extends PromotionVO> observable, PromotionVO oldValue, PromotionVO newValue) {
                clearPromotion();
                promotionVO = promotionProperty.getValue();
                // promotionOff.setText(promotionVO.getOff() + "");
                promotionText.setText(promotionVO.getName());


//                if (promotionVO.getType() != PromotionType.Combo&& promotionVO.getGoods()!=null)
//
//                    commodityList.addAll(
//                            promotionVO.getGoods().stream()
//                                    .map(x-> x.getCommodity())
//                                    .collect(Collectors.toList())
//                    );

            }
        });

        //监听，如果价总价变化 account跟着变化
        accountBeforeDis.textProperty().bind(commodityListController.getTotal().asString());

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                account.setText("" + (
                        Double.parseDouble(accountBeforeDis.textProperty().get())
                                - Double.parseDouble(discount.textProperty().get())
                                - Double.parseDouble(voucher.textProperty().get())
                        // - Double.parseDouble(promotionOff.textProperty().get())
                ));
            }
        };

        TextFieldHelper.addDoubleValidator(discount);
        TextFieldHelper.addDoubleValidator(voucher);


        discount.textProperty().addListener(changeListener);
        voucher.textProperty().addListener(changeListener);
        //promotionOff.textProperty().addListener(changeListener);

        accountBeforeDis.textProperty().addListener(c -> {
            clearPromotion();
            account.setText("" + (
                    Double.parseDouble(accountBeforeDis.textProperty().get())
                            - Double.parseDouble(discount.textProperty().get())
                            - Double.parseDouble(voucher.textProperty().get())
                    // - Double.parseDouble(promotionOff.textProperty().get())
            ));
        });

        TextFieldHelper.addRequiredValidator(salesman);
        TextFieldHelper.addRequiredValidator(customer);


    }

    void setReturn() {
        title.setText("制定销售退货单");
        verticalVBox.getChildren().remove(promotionBox);
        commitBtn.setOnAction(e -> {

        });
    }

    void init(Upper upper,List<SalesDocItemVO> itemList, int customerID, String remarks,
              String voucher, String discount) {
        this.upper=upper;
        docItemList.setAll(itemList.stream()
                .map(CommodityItem::new)
                .collect(Collectors.toList()));

        customerProperty.set(CustomerHelper.getCustomer(customerID));

        this.remarks.setText(remarks);

        this.voucher.setText(voucher);

        this.discount.setText(discount);
    }

    @Override
    public void modify(Upper upper, DocVO docVO, boolean redFlush) {
        SalesDocVO salesDocVO = (SalesDocVO) docVO;
        init(upper,salesDocVO.getItems(), salesDocVO.getCustomerId(),
                salesDocVO.getRemarks(), salesDocVO.getVoucher() + "",
                salesDocVO.getDiscount() + "");

    }

    @Override
    public Node getMainPane() {
        return mainPane;
    }
}
