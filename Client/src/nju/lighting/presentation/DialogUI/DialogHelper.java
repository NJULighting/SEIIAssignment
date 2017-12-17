package nju.lighting.presentation.DialogUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import shared.ResultMessage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class DialogHelper {

    static HashMap<ResultMessage,String> hashMap= new HashMap<>();

    static {
        hashMap.put(ResultMessage.DUPLICATE,"名称重复");
        hashMap.put(ResultMessage.FAILURE,"此次行为失败");
    }

    public  static void dialog(ResultMessage resultMessage, StackPane stackPane) throws IOException {


        JFXDialogLayout layout=new JFXDialogLayout();
        JFXButton button=new JFXButton("确认");
        FXMLLoader loader= new FXMLLoader(DialogHelper.class.getResource("DialogPane.fxml"));


        layout.setBody((AnchorPane)loader.load());
        DialogPane controller=loader.getController();
        Label label=controller.getLabel();
        label.setText(hashMap.get(resultMessage));



        JFXDialog dialog= new JFXDialog(stackPane,layout, JFXDialog.DialogTransition.BOTTOM);
        dialog.setPrefSize(280,135);
        dialog.show();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
    }


}
