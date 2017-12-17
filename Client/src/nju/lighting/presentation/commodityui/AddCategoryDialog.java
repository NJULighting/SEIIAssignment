package nju.lighting.presentation.commodityui;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import shared.ResultMessage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class AddCategoryDialog extends nju.lighting.presentation.commodityui.Dialog implements Initializable  {


    @FXML
    JFXTextField textField;





    @FXML
    void ok() throws IOException {

        CommodityCategoryVO upperCategory= ((CommodityCategoryVO)treeItem.getValue());

        CommodityCategoryVO categoryVO=new CommodityCategoryVO(upperCategory,textField.getText());
        ResultMessage resultMessage=commodityBLService.addCategory(categoryVO);
        if (resultMessage==ResultMessage.SUCCESS){
            treeItem.getChildren().add(new TreeItem<>(categoryVO));
            dialog.close();
        }else {
            DialogHelper.dialog(resultMessage,stackPane);
        }

    }

    @FXML
    void cancel(){
        dialog.close();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
