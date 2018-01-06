package nju.lighting.presentation.approvalui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.factory.ApprovalBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.ResultMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/28.
 * Description
 * @author 陈俊宇
 */
public class ApprovalUIController implements Initializable {
    ApprovalBLService approvalBLService = ApprovalBLServiceFactory.getApprovalBLService();
    List<DocVO> docs;
    ObservableList<DocVO> selectedDocList;
    String comment;


    @FXML
    JFXListView<DocVO> docList;

    @FXML
    Pane detail;


    @FXML
    JFXButton approveBtn, rejectBtn;
    @FXML
    StackPane stackPane;
    @FXML
    private Button refreshBtn;

    @FXML
    void approve() {

        if (selectedDocList != null && selectedDocList.size() != 0) {
            DocVO currentDoc;
            for (int i = 0; i < selectedDocList.size(); i++) {
                currentDoc = selectedDocList.get(i);
                approvalBLService.approve(new HistoryDocVO(Client.getUserVO(), "无可奉告", currentDoc, DocState.APPROVAL, new Date()));
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
                DialogHelper.dialog(resultMessage, stackPane);
                if (resultMessage == ResultMessage.SUCCESS) {
                    refresh();
                    return true;
                } else
                    return false;
            }

            ;
        };
        DialogHelper.addDialog(node, stackPane, rejectHandler);
    }


    void refresh() {
        docList.getItems().removeAll(selectedDocList);

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
        DocVO clicked = (DocVO) docList.getSelectionModel().getSelectedItems().get(0);

        if (detail.getChildren().size() > 0)
            detail.getChildren().remove(detail.getChildren().size() - 1);

        Doc.setDoc(clicked);

        try {
            detail.getChildren().add(Doc.getLoader().load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshBtn.setOnAction(e -> {
            docList.getItems().setAll(approvalBLService.getDocumentList());
        });

        docs = approvalBLService.getDocumentList();
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
                        } else {
                            setText(((DocVO) item).getType().toString());
                        }
                    }
                };
            }
        });

        docList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


}
