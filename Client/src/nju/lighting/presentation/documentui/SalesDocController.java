package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.promotionui.BenefitsPlan;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.*;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SalesDocController implements Initializable, Upper {
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

    ObservableList<CommodityItem> commodityList;
    boolean commodityCancel = true, customerCanceled = true, promotionCanceled = true;
    CommodityPicker commodityPicker;
    GiftListEditable commodityController;

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


    public void choosePromotion() throws IOException {
        container.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../promotionui/BenefitsPlan.fxml"));
        container.getChildren().add(loader.load());
        promotionPicker = loader.getController();
        promotionPicker.init(promotionBLService.getPromotionList(), this);
        sub.setText(">选择促销策略");

    }

    void refresh() {
        if (!commodityCancel) {

            commodityList.addAll(commodityPicker.getCommodities().stream()
                    .map(x -> new CommodityItem(x))
                    .collect(Collectors.toList()));
            commodityPicker.setCanceled(true);
            clearPromotion();
        }
       else if (!customerCanceled) {
            customerVO = customerPicker.getCustomer();
            customer.setText(customerVO.getName());
            clearPromotion();
            customerPicker.setCanceled(true);
        }
        else if (!promotionCanceled) {

            clearPromotion();
            promotionVO = promotionPicker.getPromotionVO();
           // promotionOff.setText(promotionVO.getOff() + "");
            promotionText.setText(promotionVO.getName());



            if (promotionVO.getType() != PromotionType.Combo&& promotionVO.getGoods()!=null)

                commodityList.addAll(
                        promotionVO.getGoods().stream()
                                .map(x -> new CommodityItem(x, true))
                                .collect(Collectors.toList())
                );

            promotionPicker.setCanceled(true);
        }





    }

    public void back() {
        container.getChildren().clear();
        container.getChildren().add(mainPane);
        sub.setText("");
        if (commodityPicker != null)
            commodityCancel = commodityPicker.isCanceled();
        if (customerPicker != null)
            customerCanceled = customerPicker.isCanceled();
        if (promotionPicker!=null){
            promotionCanceled=promotionPicker.isCanceled();
        }
        refresh();

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

            TwoTuple<String, ResultMessage> result = docBLService.commitDoc(salesDocVO);
            switch (result.r) {
                case SUCCESS:
                    salesDocID = result.t;
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
        commodityList.removeAll(
                commodityList.stream()
                .filter(x-> x.isGift())
                .collect(Collectors.toList())
        );
      //  promotionOff.setText(0+"");
        if (promotionText!=null)
        promotionText.setText("");
        promotionVO=null;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GiftListEditable.fxml"));
        try {
            commodityContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        commodityController = loader.getController();
        System.out.println("gift size"+commodityController.giftObservableList.size());
        commodityList = commodityController.giftObservableList;

//        //监听，如果价总价变化 account跟着变化
//        accountBeforeDis.textProperty().bind(commodityController.totalLabel.textProperty());
//
//        ChangeListener changeListener = new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                account.setText("" + (
//                        Double.parseDouble(accountBeforeDis.textProperty().get())
//                                - Double.parseDouble(discount.textProperty().get())
//                                - Double.parseDouble(voucher.textProperty().get())
//                               // - Double.parseDouble(promotionOff.textProperty().get())
//                ));
//            }
//        };
//
//        DoubleValidator discountValidator = new DoubleValidator();
//        discountValidator.setMessage("折让必须为小数");
//        DoubleValidator voucherValidator = new DoubleValidator();
//        voucherValidator.setMessage("代金券金额必须为小数");
//
//        TextFieldHelper.binds(discount, discountValidator, false);
//        TextFieldHelper.binds(voucher, voucherValidator, false);
//
//
//        discount.textProperty().addListener(changeListener);
//        voucher.textProperty().addListener(changeListener);
//        //promotionOff.textProperty().addListener(changeListener);
//
//        accountBeforeDis.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                clearPromotion();
//                account.setText("" + (
//                        Double.parseDouble(accountBeforeDis.textProperty().get())
//                                - Double.parseDouble(discount.textProperty().get())
//                                - Double.parseDouble(voucher.textProperty().get())
//                               // - Double.parseDouble(promotionOff.textProperty().get())
//                ));
//            }
//        });

    }

    void setReturn(){
        title.setText("制定销售退货单");
        verticalVBox.getChildren().remove(promotionBox);
    }
}
