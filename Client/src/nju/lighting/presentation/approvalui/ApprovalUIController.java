package nju.lighting.presentation.approvalui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.presentation.documentui.ModifyDoc;
import nju.lighting.presentation.factory.ApprovalBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.presentation.utils.Helper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.ResultMessage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/28.
 * Description
 *
 * @author 陈俊宇
 */
public class ApprovalUIController implements Initializable,Upper {
    private ApprovalBLService approvalBLService = ApprovalBLServiceFactory.getApprovalBLService();
    private ObservableList<DocVO> selectedDocList;
    private ObservableList<Node> list= FXCollections.observableArrayList();
    private Upper upper=this;


    @FXML
    JFXListView<DocVO> docList;

    @FXML
    Pane detail;


    @FXML
    JFXButton approveBtn, rejectBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    Pane mainPane;

    @FXML
    HBox labelBox,container;


    @FXML
    void approve() {

        if (selectedDocList != null && selectedDocList.size() != 0) {
            DocVO currentDoc;
            for (int i = 0; i < selectedDocList.size(); i++) {
                currentDoc = selectedDocList.get(i);
                ResultMessage resultMessage= approvalBLService.approve(new HistoryDocVO(Client.getUserVO(), "无可奉告", currentDoc, DocState.APPROVAL, new Date()));
                if (resultMessage!=ResultMessage.SUCCESS)
                    DialogHelper.dialog("审批单据",resultMessage,MainUI.getStackPane());
            }
        }
        refresh();
    }

    @FXML
    void reject() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("comments.fxml"));
        Node node = loader.load();
        CommentsController controller = loader.getController();

        ValidateEventHandle rejectHandler = new ValidateEventHandle() {
            @Override
            public boolean validate() {
                ResultMessage resultMessage = approvalBLService.reject(new HistoryDocVO(
                        Client.getUserVO(),
                        controller.getComments(),
                        selectedDocList.get(0), DocState.DISAPPROVAL, new Date()
                ));
                DialogHelper.dialog("驳回单据",resultMessage, MainUI.getStackPane());
                if (resultMessage == ResultMessage.SUCCESS) {
                    refresh();
                    return true;
                } else
                    return false;
            }

            ;
        };
        DialogHelper.addDialog(node, MainUI.getStackPane(), rejectHandler);
    }

    /**
     * 刷新单据列表，如果列表中为空就把按钮设置为不可用， 否则自动选择显示第一个单据
     */

    void refresh() {
        docList.getItems().setAll(approvalBLService.getDocumentList());

        if (docList.getItems().size() > 0) {
            docList.getSelectionModel().clearAndSelect(0);
            rejectBtn.setDisable(false);
            showSelectedDoc();
        } else {
            approveBtn.setDisable(true);
            rejectBtn.setDisable(true);
            detail.getChildren().clear();
        }
    }


    /**
     * 点击列表后，会进行判断，若选中的单据个数唯一，则显示选中的单据，若选中单据有多个则将驳回按钮设置为不可用
     * @param event
     */
    @FXML
    public void clicked(MouseEvent event) {
        selectedDocList = docList.getSelectionModel().getSelectedItems();

        if (selectedDocList.size() == 0) {
            approveBtn.setDisable(true);
            rejectBtn.setDisable(true);
        } else if (selectedDocList.size() == 1) {
            approveBtn.setDisable(false);
            rejectBtn.setDisable(false);
            showSelectedDoc();
        } else
            rejectBtn.setDisable(true);
    }

    //显示所选的单据
    void showSelectedDoc() {
        DocHelper.showDoc((DocVO) docList.getSelectionModel().getSelectedItems().get(0),detail);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshBtn.setOnAction(e -> {
            docList.getItems().setAll(approvalBLService.getDocumentList());
        });

        List<DocVO> docs = approvalBLService.getDocumentList();
        for (int i = 0; i < docs.size(); i++) {

            docList.getItems().add(docs.get(i));
        }


        docList.setCellFactory(new Callback<ListView<DocVO>, ListCell<DocVO>>() {
            @Override
            public ListCell<DocVO> call(ListView<DocVO> param) {
                return new JFXListCell<DocVO>() {
                    @Override
                    protected void updateItem(DocVO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                            setContextMenu(null);
                        } else {
                            setText( item.getType().toString());
                            MenuItem modify=new MenuItem("修改单据");
                            modify.setOnAction(e->setChildren(ModifyDoc.getNode(upper,item,false),">修改单据"));
                            setContextMenu(new ContextMenu(modify));
                        }
                    }
                };
            }
        });

        docList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void backToMain(){
        Helper.bakToMain(container,labelBox,list,mainPane);
        refresh();
    }


    @Override
    public void back() {
        if (labelBox.getChildren().size() == 1)
            backToMain();
        else
            Helper.clearTitleLabel(container,labelBox,list);
    }

    @Override
    public void setChildren(Node node, String title) {
        list.add(node);
        container.getChildren().setAll(node);
        labelBox.getChildren().add(Helper.getTitleLabel(title,container,labelBox,node,list));
    }
}
