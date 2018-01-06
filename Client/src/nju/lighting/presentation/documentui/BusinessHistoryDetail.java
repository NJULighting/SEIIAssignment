package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.DocVO;
import shared.Identity;
import shared.ResultMessage;

import java.io.IOException;

/**
 * Created on 2018/1/6.
 * Description
 *
 * @author 陈俊宇
 */
public class BusinessHistoryDetail {
    DocBLService blService= DocBLServiceFactory.getDocBLService();

    @FXML
    HBox docContainer,buttonBox;

    @FXML
    JFXButton redFlushBtn,redFlushAndCopyBtn;


    public void init(DocVO doc, Upper upper){
        Doc.setDoc(doc);
        try {
            docContainer.getChildren().add(Doc.getLoader().load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Client.getUserVO().getIdentity()== Identity.GENERAL)
            buttonBox.setVisible(false);
        else {
            redFlushBtn.setOnAction(e->{
                ResultMessage resultMessage=blService.redFlush(doc);
                DialogHelper.dialog(resultMessage, MainUI.getStackPane());
            });

            redFlushAndCopyBtn.setOnAction(e->{
                upper.setChildren(ModifyDoc.getNode(doc,true),">红冲并复制");
            });
        }
    }

}
