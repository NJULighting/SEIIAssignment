package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
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
import nju.lighting.presentation.factory.PromotionBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.PromotionHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerType;
import shared.DocType;
import shared.ResultMessage;
import shared.TwoTuple;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SalesDocController implements Initializable, Upper {
    PromotionBLService promotionBLService = PromotionBLServiceFactory.getPromotionBLService();
    @FXML
    HBox container, promotionBox;
    @FXML
    VBox verticalVBox;
    @FXML
    Pane mainPane, commodityContainer;
    private ObservableList<BasicCommodityItemVO> commodityList = FXCollections.observableArrayList();
    private ObservableList<CommodityItem> docItemList;
    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<PromotionVO> promotionProperty = new SimpleObjectProperty<>();
    private CommodityList commodityListController;
    private PromotionVO promotionVO;
    private CustomerType type = CustomerType.SALESPERSON;
    private CustomerVO customerVO;
    private String salesDocID;
    private double accountBeforeDisNum = 0;
    private double discountNum = 0;
    private double voucherNum = 0;
    private double accountNum = 0;
    private DocBLService docBLService = new DocController();
    @FXML
    private Button choseCustomBtn, finishBtn, promotionBtn;
    @FXML
    private JFXButton commodityBtn;
    @FXML
    private JFXTextField customer, salesman, userman, repository, accountBeforeDis, discount,
            voucher, account, promotionText;
    @FXML
    private TextArea remarks;
    @FXML
    private Label failMessage, failDisCount, failVoucher, failCustomer, sub, title;

    public void chooseCommodity() {
        CommodityHelper.chooseCommodity(this, commodityList);
    }

    public void chooseCustomer() {
        CustomerHelper.setCustomer(this, customerProperty, CustomerType.SUPPLIER);
    }


    public void choosePromotion() throws IOException {
        PromotionHelper.setPromotion(this, promotionProperty, promotionBLService.getBenefitsPlan(
                customerProperty.getValue().getGrade(),
                commodityList.stream()
                        .map(x -> x.getId())
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


    //完成并提交
    public void finish() {

        if (customerVO == null) {
            failCustomer.setVisible(true); //未选择客户
        } else {

            DocVO salesDocVO = new SalesDocVO(new Date(), Client.getUserVO().getID(), null, DocType.SALES
                    , customerVO.getID(), customerVO.getSalesman(), repository.getText(), remarks.getText(),
                    Double.parseDouble(accountBeforeDis.getText()), Double.parseDouble(discount.getText()),
                    Double.parseDouble(voucher.getText()), Double.parseDouble(account.getText()));

            TwoTuple<ResultMessage, String> result = docBLService.commitDoc(salesDocVO);
            switch (result.t) {
                case SUCCESS:
                    salesDocID = result.r;
                    successtoCommit();
                    break;
                case FAILURE:
                    failtoCommit();
                    break;
                default:
                    failtoCommit();
                    break;
            }
        }

    }

    public void successtoCommit() {

    }

    public void failtoCommit() {
        failMessage.setVisible(true);
    }

    //当重要参数发生变化时清除正在作用的promotion
    void clearPromotion() {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommodityList.fxml"));
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


    }

    void setReturn() {
        title.setText("制定销售退货单");
        verticalVBox.getChildren().remove(promotionBox);
    }

    public ObservableList<BasicCommodityItemVO> getCommodityList() {
        return commodityList;
    }

    public ObservableList<CommodityItem> getDocItemList() {
        return docItemList;
    }

    public SimpleObjectProperty<CustomerVO>
    getCustomerProperty() {
        return customerProperty;
    }

    public SimpleObjectProperty<CustomerVO> customerPropertyProperty() {
        return customerProperty;
    }

    public CommodityList getCommodityListController() {
        return commodityListController;
    }

    public CustomerType getType() {
        return type;
    }
}
