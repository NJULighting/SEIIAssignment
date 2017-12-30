package nju.lighting.presentation.commodityui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import nju.lighting.builder.Builder;
import nju.lighting.builder.commodity.CommodityBuildInfo;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/17.
 * Description
 * @author 陈俊宇
 */
public class AddCommodity extends Dialog implements Initializable {


    JFXButton button;

    @FXML
    JFXTextField name, modelNumber, repCount, inPrice, sellPrice, recentInPrice, recentSellPrice;

    boolean verify() {
        return repCount.validate() & inPrice.validate() & inPrice.validate() & sellPrice.validate()
                & recentInPrice.validate() & recentSellPrice.validate() & name.validate() & modelNumber.validate();
    }

    void binds(JFXTextField textField, ValidatorBase validator) {

        textField.getValidators().add(validator);
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    textField.validate();
                } else
                    textField.resetValidation();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TextFieldHelper.addNumberValidator(repCount);
        TextFieldHelper.addDoubleValidator(inPrice);
        TextFieldHelper.addDoubleValidator(sellPrice);
        TextFieldHelper.addDoubleValidator(recentInPrice);
        TextFieldHelper.addDoubleValidator(recentSellPrice);
        TextFieldHelper.addRequiredValidator(name);
        TextFieldHelper.addRequiredValidator(modelNumber);

    }

    void init(CommodityItemVO commodity) {
        name.setText(commodity.getName());
        modelNumber.setText(commodity.getModelNumber());
        repCount.setText(commodity.getRepCount() + "");
        inPrice.setText(commodity.getInPrice() + "");
        sellPrice.setText(commodity.getSellPrice() + "");
        recentInPrice.setText(commodity.getRecentInPrice() + "");
        recentSellPrice.setText(commodity.getRecentSellPrice() + "");
    }

    Builder<CommodityBuildInfo> getCommodityItem(CommodityCategoryVO parentCategory) {
        if (verify()) {
            CommodityBuildInfo.CommodityBuilder builder = new CommodityBuildInfo.CommodityBuilder(parentCategory);
            builder.name(name.getText()).modelNumber(modelNumber.getText()).repCount(parseInt(repCount.getText()))
                    .inPrice(parseDouble(inPrice.getText())).sellPrice(parseDouble(sellPrice.getText()))
                    .recentInPrice(parseDouble(recentInPrice.getText())).recentSellPrice(parseDouble(recentSellPrice.getText()));
            return builder;
        } else
            return null;
    }

    CommodityItemVO getCommodityItem() {
        if (verify())
            return new CommodityItemVO(name.getText(), modelNumber.getText(),
                    Integer.parseInt(repCount.getText()), Double.parseDouble(inPrice.getText()),
                    Double.parseDouble(sellPrice.getText()), Double.parseDouble(recentInPrice.getText()),
                    Double.parseDouble(recentSellPrice.getText()), null, null, null);
        else return null;
    }

    private int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private double parseDouble(String s) {
        return Double.parseDouble(s);
    }


}
