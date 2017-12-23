package nju.lighting.presentation.utils;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created on 2017/12/23.
 * Description
 *
 * @author 陈俊宇
 */
public class TextFieldHelper {
    public static void binds(JFXTextField textField, ValidatorBase validator) {

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
}
