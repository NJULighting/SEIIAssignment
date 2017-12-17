package nju.lighting.presentation.commodityui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputControl;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class AddCommodity extends Dialog implements Initializable {


    JFXButton button;

    @FXML
    JFXTextField name, modelNumber, repCount, inPrice, sellPrice, recentInPrice, recentSellPrice;

    boolean verify(){
        return repCount.validate()&inPrice.validate()&inPrice.validate()&sellPrice.validate()
                &recentInPrice.validate()&recentSellPrice.validate()&name.validate()&modelNumber.validate();
    }

    void binds(JFXTextField textField, ValidatorBase validator) {

        textField.getValidators().add(validator);
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    textField.validate();
                }else
                    textField.resetValidation();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumberValidator repCountValidator = new NumberValidator();
        repCountValidator.setMessage("请输入整数");

        DoubleValidator inPriceValidator = new DoubleValidator();
        inPriceValidator.setMessage("请输入小数");

        DoubleValidator sellPriceValidator = new DoubleValidator();
        sellPriceValidator.setMessage("请输入小数");

        DoubleValidator recentInPriceValidator = new DoubleValidator();
        recentInPriceValidator.setMessage("请输入小数");

        DoubleValidator recentSellPriceValidator = new DoubleValidator();
        recentSellPriceValidator.setMessage("请输入小数");


        RequiredFieldValidator namedValidator= new RequiredFieldValidator();
        namedValidator.setMessage("名称不能为空");

        RequiredFieldValidator modelNumberValidator= new RequiredFieldValidator();
        modelNumberValidator.setMessage("型号不能为空");



        binds(repCount, repCountValidator);
        binds(inPrice,inPriceValidator);
        binds(sellPrice,sellPriceValidator);
        binds(recentInPrice,recentInPriceValidator);
        binds(recentSellPrice,recentSellPriceValidator);
        binds(name,namedValidator);
        binds(modelNumber,modelNumberValidator);






    }

    void init(CommodityItemVO commodity){
        name.setText(commodity.getName());
        modelNumber.setText(commodity.getModelNumber());
        repCount.setText(commodity.getRepCount()+"");
        inPrice.setText(commodity.getInPrice()+"");
        sellPrice.setText(commodity.getSellPrice()+"");
        recentInPrice.setText(commodity.getRecentInPrice()+"");
        recentSellPrice.setText(commodity.getRecentSellPrice()+"");
    }

    CommodityItemVO getCommodityItem() {
        CommodityItemVO commodityItemVO = null;
            commodityItemVO = new CommodityItemVO(name.getText(), modelNumber.getText(),
                    Integer.parseInt(repCount.getText()), Double.parseDouble(inPrice.getText()),
                    Double.parseDouble(sellPrice.getText()), Double.parseDouble(recentInPrice.getText()),
                    Double.parseDouble(recentSellPrice.getText()), null, null, null);
        return commodityItemVO;
    }



}
