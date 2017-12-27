package nju.lighting.presentation.documentui;

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
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.lighting.bl.documentbl.DocController;
import nju.lighting.bl.promotionbl.PromotionBLService_Stub;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.blservice.promotionblservice.PromotionBLService;
import nju.lighting.presentation.commodityui.CommodityPicker;
import nju.lighting.presentation.customerui.CustomerPicker;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.CommodityUpper;
import nju.lighting.presentation.mainui.CustomerUpper;
import nju.lighting.presentation.mainui.PromotionUpper;
import nju.lighting.presentation.promotionui.BenefitsPlan;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.*;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SalesDocController implements Initializable, CommodityUpper,PromotionUpper,CustomerUpper {
    private CustomerVO customerVO;

    private String salesDocID;
    PromotionBLService promotionBLService = new PromotionBLService_Stub();

    private double accountBeforeDisNum = 0;

    private double discountNum = 0;

    private double voucherNum = 0;

    private double accountNum = 0;

    private DocBLService docBLService = new DocController();

    @FXML
    private Button choseCustomBtn, finishBtn;

    @FXML
    private JFXTextField customer, salesman, userman, repository, accountBeforeDis, discount,
            voucher, account,promotionText;

    @FXML
    private TextArea remarks;

    @FXML
    private Label failMessage, failDisCount, failVoucher, failCustomer, sub,title;

    @FXML
    HBox container,promotionBox;

    @FXML
    VBox verticalVBox;

    @FXML
    Pane mainPane, commodityContainer;

    ObservableList<BasicCommodityItemVO> commodityList= FXCollections.observableArrayList();
    ObservableList<CommodityItem> docItemList;
    SimpleObjectProperty<CustomerVO> customerProperty=new SimpleObjectProperty<>();
    SimpleObjectProperty<PromotionVO> promotionProperty=new SimpleObjectProperty<>();

    CommodityPicker commodityPicker;
    CommodityList commodityListController;

    CustomerPicker customerPicker;
    BenefitsPlan promotionPicker;
    PromotionVO promotionVO;
    CustomerType type=CustomerType.SALESPERSON;


    public void chooseCustomer() {
        try {
            container.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../customerui/CustomerPicker.fxml"));
            container.getChildren().add(loader.load());
            customerPicker = loader.getController();
            customerPicker.init(this,type);
            sub.setText(">选择客户");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCustomer(CustomerVO customer) {
        customerProperty.set(customer);
    }


    public void chooseCommodity() {
        try {
            container.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../commodityui/CommodityPicker.fxml"));
            container.getChildren().add(loader.load());
            commodityPicker = loader.getController();
            commodityPicker.setUpper(this);
            sub.setText(">选择商品");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCommodity(List<BasicCommodityItemVO> commodity) {
        commodityList.addAll(commodity);
    }

    public void choosePromotion() throws IOException {
        container.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../promotionui/BenefitsPlan.fxml"));
        container.getChildren().add(loader.load());
        promotionPicker = loader.getController();
        promotionPicker.init(promotionBLService.getPromotionList(), this);
        sub.setText(">选择促销策略");

    }

    @Override
    public void setPromotion(PromotionVO promotion) {
        promotionProperty.set(promotion);
    }


    public void back() {
        container.getChildren().clear();
        container.getChildren().add(mainPane);
        sub.setText("");

    }


    //完成并提交
    public void finish() {

        if (customerVO == null) {
            failCustomer.setVisible(true); //未选择客户
        } else if (true) {

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
    void clearPromotion(){
//        commodityList.removeAll(
//                commodityList.stream()
//                .filter(x-> x.isGift())
//                .collect(Collectors.toList())
//        );
      //  promotionOff.setText(0+"");
        if (promotionText!=null)
        promotionText.setText("");
        promotionVO=null;

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

        docItemList = commodityListController.giftObservableList;
        commodityList.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()){
                    docItemList.addAll(c.getAddedSubList().stream()
                    .map(x-> new CommodityItem(x,1))
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
        accountBeforeDis.textProperty().bind(commodityListController.totalLabel.textProperty());

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

        accountBeforeDis.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clearPromotion();
                account.setText("" + (
                        Double.parseDouble(accountBeforeDis.textProperty().get())
                                - Double.parseDouble(discount.textProperty().get())
                                - Double.parseDouble(voucher.textProperty().get())
                               // - Double.parseDouble(promotionOff.textProperty().get())
                ));
            }
        });

    }

    void setReturn(){
        title.setText("制定销售退货单");
        verticalVBox.getChildren().remove(promotionBox);
    }



}
