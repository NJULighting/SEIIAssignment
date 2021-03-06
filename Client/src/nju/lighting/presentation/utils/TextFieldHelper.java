package nju.lighting.presentation.utils;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * Created on 2017/12/23.
 * Description
 *
 * @author 陈俊宇
 */
public class TextFieldHelper {

    public static void addDeleteMenuItem(TextField textField, SimpleObjectProperty objectProperty){
        MenuItem delete=new MenuItem("清除");
        delete.setOnAction(e->{
            textField.setText("");
            objectProperty.set(null);
        });

        textField.setContextMenu(new ContextMenu(delete));
        textField.setEditable(false);

    }

    private static void binds(JFXTextField textField, ValidatorBase validator, String message) {
        validator.setMessage(message);
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


    private static void binds(JFXPasswordField passwordField, ValidatorBase validator, String message) {
        validator.setMessage(message);
        passwordField.getValidators().add(validator);
        passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    passwordField.validate();
                } else
                    passwordField.resetValidation();
            }
        });
    }

    public static void addDoubleValidator(JFXTextField textField) {
        addDoubleValidator(textField, "请输入小数");
    }

    public static void addDoubleValidator(JFXTextField textField, String message) {
        ValidatorBase validator = new ValidatorBase() {
            @Override
            protected void eval() {
                try {
                    double target = Double.parseDouble(textField.getText());
                    System.out.println("target" + target);
                    if (target >=0)
                        this.hasErrors.set(false);
                    else this.hasErrors.set(true);
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        };
        binds(textField, validator, message);
    }

    public static void addNumberValidator(JFXTextField textField) {
        addNumberValidator(textField, "请输入整数");
    }

    public static void addNumberValidator(JFXTextField textField, String message) {
        ValidatorBase validator = new ValidatorBase() {
            @Override
            protected void eval() {
                try {
                    int target = Integer.parseInt(textField.getText());
                    if (target >= 0)
                        this.hasErrors.set(false);
                    else
                        this.hasErrors.set(true);
                } catch (Exception var3) {
                    this.hasErrors.set(true);
                }
            }
        };
        binds(textField, validator, message);
    }

    public static void addRequiredValidator(JFXTextField textField) {
        addRequiredValidator(textField, "不能为空");
    }

    public static void addRequiredValidator(JFXTextField textField, String message) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        binds(textField, validator, message);
    }


    public static void addRequiredValidator(JFXPasswordField passwordField) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        binds(passwordField, validator, "不能为空");
    }

    public static void addSameValidator(JFXPasswordField passwordField, JFXPasswordField target) {
        ValidatorBase validatorBase = new ValidatorBase() {
            @Override
            protected void eval() {
                if (passwordField.getText().equals(target.getText()))
                    this.hasErrors.set(false);
                else
                    this.hasErrors.set(true);
            }
        };

        binds(passwordField, validatorBase, "两次输入密码不一致");
    }

    public static void addNameValidator(JFXTextField textField) {
        ValidatorBase validatorBase = new ValidatorBase() {
            @Override
            protected void eval() {
                if (NameChecker.validName(textField.getText()))
                    this.hasErrors.set(false);
                else this.hasErrors.set(true);
            }
        };

        binds(textField, validatorBase, "格式不正确");
    }

    public static void addIdValidator(JFXTextField textField) {
        ValidatorBase validatorBase = new ValidatorBase() {
            @Override
            protected void eval() {
                if (NameChecker.validID(textField.getText()))
                    this.hasErrors.set(false);
                else
                    this.hasErrors.set(true);
            }
        };
        binds(textField, validatorBase, "格式不正确");
    }

    public static double getDouble(JFXTextField textField) {
        if (textField.validate() && textField.getText() != null && textField.getText().length() != 0)
            return Double.parseDouble(textField.getText());
        else
            return 0;
    }

}
