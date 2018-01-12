package nju.lighting.presentation.utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.factory.ApprovalBLServiceFactory;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.DocType;
import shared.Result;
import shared.ResultMessage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created on 2018/1/5.
 * Description
 *
 * @author 陈俊宇
 */
public class DocHelper {


    public static void commitDoc(DocVO doc){
        if (doc!=null){
            Result<DocVO> result= DocBLServiceFactory.getDocBLService().commitDoc(doc);
            DialogHelper.dialog("提交"+doc.getType(),result.getResultMessage(), MainUI.getStackPane());
        }
    }

    public static void commitModifiedDoc(boolean redFlush, Button commitBtn, ValidateEventHandle eventHandle,
                                              SimpleObjectProperty<DocVO> res, Upper upper ){
        if (redFlush)
            commitBtn.setOnAction(e ->{
                if (eventHandle.validate())
                    commitInRedFlush(res.getValue(), upper);
            });
        else{
            commitBtn.setText("保存并通过");
            commitBtn.setOnAction(e->{
                if (eventHandle.validate())
                    saveAndApprove(commitBtn,res.getValue(),upper);
            });
        }

    }

    public static void commitModifiedDoc(boolean redFlush,Button button,DocVO docVO,Upper upper){

    }

    private static void commitInRedFlush(DocVO doc, Upper upper){
        commitDoc(doc);
        upper.back();
        upper.back();
    }

    private static void saveAndApprove(Button commitBtn, DocVO docVO, Upper upper){
        ResultMessage resultMessage= ApprovalBLServiceFactory.getApprovalBLService().save(
                new HistoryDocVO(Client.getUserVO(),"",docVO,
                        DocState.APPROVAL,new Date())
        );
        DialogHelper.dialog("保存并通过",resultMessage, MainUI.getStackPane());
        upper.back();
    }






    public static void search(JFXNodesList nodesList,HamburgerBasicCloseTransition burgerTask){
        nodesList.animateList(false);
        burgerTask.setRate(burgerTask.getRate() * -1);

        burgerTask.play();
    }

    public static void addFilter(HamburgerBasicCloseTransition burgerTask, JFXHamburger hamburger,
                                 JFXNodesList nodesList, Pane filterBox, Pane container){

        burgerTask.setRate(-1);


        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {

            burgerTask.setRate(burgerTask.getRate() * -1);

            burgerTask.play();

        });

        nodesList.setSpacing(30);

        nodesList.getChildren().clear();
        nodesList.addAnimatedNode(hamburger);
        nodesList.addAnimatedNode(filterBox);

        nodesList.setMargin(hamburger, new Insets(0, 0, 0, 990));
        nodesList.setMargin(filterBox, new Insets(-20, 0, 0, 20));
        nodesList.setPadding(new Insets(25, 0, 0, 40));
        container.getChildren().add(nodesList);

    }




    public static void showDoc(DocVO docVO, Pane container){

        if (container.getChildren().size() > 0)
            container.getChildren().remove(container.getChildren().size() - 1);

        Doc.setDoc(docVO);

        try {
            container.getChildren().add(Doc.getLoader().load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
