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
 * Description 查看经营历程表中查看单据详情的控制类
 *
 * @author 陈俊宇
 */
public class BusinessHistoryDetail {
    private DocBLService blService = DocBLServiceFactory.getDocBLService();

    @FXML
    HBox docContainer, buttonBox;

    @FXML
    JFXButton redFlushBtn, redFlushAndCopyBtn;

    private DocVO docVO;

    private void redFlush() {
        ResultMessage resultMessage = blService.redFlush(docVO);
        DialogHelper.dialog("红冲", resultMessage, MainUI.getStackPane());
    }

    /**
     * 显示选中的单据
     *
     * @param doc   要显示的单据
     * @param upper 上一层界面
     */
    public void init(DocVO doc, Upper upper) {
        docVO = doc;
        Doc.setDoc(doc);
        try {
            docContainer.getChildren().add(Doc.getLoader().load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Client.getUserVO().getIdentity() == Identity.GENERAL)
            buttonBox.setVisible(false);
        else {
            redFlushBtn.setOnAction(e -> {
                redFlush();
            });

            redFlushAndCopyBtn.setOnAction(e -> {
                redFlush();
                upper.setChildren(ModifyDoc.getNode(upper, doc, true), ">红冲并复制");
            });
        }
    }

}
