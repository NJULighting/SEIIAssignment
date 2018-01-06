package nju.lighting.presentation.documentui.costdoc;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.presentation.utils.CostDocHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import shared.CostDocItemType;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2018/1/4.
 * Description
 *
 * @author 陈俊宇
 */
public class AddItem implements Initializable {

    @FXML
    JFXComboBox<String> itemBox;

    @FXML
    JFXTextField amountText;

    @FXML
    JFXTextArea commentsText;

    /**
     * 添加现金费用单条目到 现金费用单中
     *
     * @param list 现金费用单中的条目列表
     * @return 一个有验证功能的函数类， 此添加条目界面于弹出框中展示，当点击弹出框确定按钮时触发此validate方法，若返回true则关闭弹出框
     */

    public ValidateEventHandle AddItem(ObservableList<CostDocItemVO> list) {
        return new ValidateEventHandle() {
            @Override
            public boolean validate() {
                if (amountText.validate()) {
                    list.add(new CostDocItemVO(
                            CostDocHelper.stringToType(itemBox.getValue()),
                            Double.parseDouble(amountText.getText()),
                            commentsText.getText()));
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBox.getItems().addAll(Arrays.stream(CostDocItemType.values())
                .map(x -> CostDocHelper.typeToString(x))
                .collect(Collectors.toList()));


        itemBox.setValue(itemBox.getItems().get(0));

        TextFieldHelper.addDoubleValidator(amountText);
    }
}
