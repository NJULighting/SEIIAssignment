package nju.lighting.presentation.utils;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.factory.ApprovalBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.DocType;
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
    private static HashMap<DocType,String> typeStringHashMap=new HashMap<>();
    private static HashMap<String,DocType> stringTypeHasMap=new HashMap<>();
    private static HashMap<DocState,String> stateStringHashMap=new HashMap<>();

    static {
        typeStringHashMap.put(DocType.ACCOUNT_OUT,"付款单");
        typeStringHashMap.put(DocType.ACCOUNT_IN,"收款单");
        typeStringHashMap.put(DocType.ALERT,"报警单");
        typeStringHashMap.put(DocType.COST,"现金费用单");
        typeStringHashMap.put(DocType.GIFT,"赠品单");
        typeStringHashMap.put(DocType.LOSS_AND_GAIN,"报损报溢单");
        typeStringHashMap.put(DocType.SALES,"销售单");
        typeStringHashMap.put(DocType.SALES_RETURN,"销售退货单");
        typeStringHashMap.put(DocType.STOCK,"进货单");
        typeStringHashMap.put(DocType.STOCK_RETURN,"进货退货单");

        stringTypeHasMap.put("付款单",DocType.ACCOUNT_OUT);
        stringTypeHasMap.put("收款单",DocType.ACCOUNT_IN);
        stringTypeHasMap.put("报警单",DocType.ALERT);
        stringTypeHasMap.put("现金费用单",DocType.COST);
        stringTypeHasMap.put("赠品单",DocType.GIFT);
        stringTypeHasMap.put("报损报溢单",DocType.LOSS_AND_GAIN);
        stringTypeHasMap.put("销售单",DocType.SALES);
        stringTypeHasMap.put("销售退货单",DocType.SALES_RETURN);
        stringTypeHasMap.put("进货单",DocType.STOCK);
        stringTypeHasMap.put("进货退货单",DocType.STOCK_RETURN);

        stateStringHashMap.put(DocState.APPROVAL,"审批通过");
        stateStringHashMap.put(DocState.DISAPPROVAL,"审批不通过");
        stateStringHashMap.put(DocState.UN_CHECKED,"待审批");

    }

 //   public static String typeToString(DocType type){return typeStringHashMap.get(type);}

    public static DocType stringToType(String string){return stringTypeHasMap.get(string);}

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

    public static void saveAndApprove(DocVO docVO){
        ResultMessage resultMessage= ApprovalBLServiceFactory.getApprovalBLService().save(
                new HistoryDocVO(Client.getUserVO(),"",docVO,
                        DocState.APPROVAL,new Date())
        );
        DialogHelper.dialog("保存并通过",resultMessage, MainUI.getStackPane());
    }

    public static String stateToString(DocState state){return stateStringHashMap.get(state);}

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
