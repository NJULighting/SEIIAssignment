package nju.lighting.presentation.approvalui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.lighting.bl.approvalbl.ApprovalBLService_Stub;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/28.
 * Description
 *
 * @author 陈俊宇
 */
public class ApprovalUIController implements Initializable {
    ApprovalBLService approvalBLService ;
    ArrayList<DocVO> docs;
    ObservableList selectedDocList;
    String comment;
    static Stage dialog;

    @FXML
    JFXListView docList;

    @FXML
    Pane detail;

    @FXML
    Text detailNum;

    @FXML
    Label test;

    @FXML
    JFXButton massApprove;

    @FXML
    JFXButton closeMassApprove;

    @FXML
    JFXButton approveBtn;

    @FXML
    JFXButton rejectBtn;



    @FXML
    void approve() {
        approvalBLService =new ApprovalBLService_Stub();
        if (selectedDocList != null && selectedDocList.size() != 0) {
            DocVO currentDoc;
            for (int i = 0; i < selectedDocList.size(); i++) {
                currentDoc = findDoc(((Label) selectedDocList.get(i)).getText());
                    approvalBLService.approve(new HistoryDocVO(Client.user.getID(), "", currentDoc));
            }
        }
        refresh();
    }

    @FXML
    void reject() throws IOException {
        dialog=new Stage();
        DocVO currentDoc=findDoc(((Label) selectedDocList.get(0)).getText());

        FXMLLoader loader=new FXMLLoader(getClass().getResource("comments.fxml"));
        dialog.setScene(new Scene(loader.load()));
        CommentsController controller=loader.getController();
        controller.setApprovalUIController(this);

        dialog.initStyle(StageStyle.TRANSPARENT);
        //在对话框关闭之前无法返回主界面
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
        approvalBLService.reject(new HistoryDocVO(Client.user.getID(),comment,currentDoc));
        refresh();
    }

    void refresh(){
        docList.getItems().removeAll(selectedDocList);

        if (docList.getItems().size()>0){
            docList.getSelectionModel().clearAndSelect(0);
            rejectBtn.setDisable(false);
            showSelectedDoc();
        }else {
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
        Label clicked = (Label) docList.getSelectionModel().getSelectedItems().get(0);

        if (detail.getChildren().size() > 0)
            detail.getChildren().remove(detail.getChildren().size() - 1);

        Doc.doc = findDoc(clicked.getText());
        FXMLLoader loader = null;
        try {
            loader = Doc.getLoader();
            detail.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    DocVO findDoc(String Id) {
        for (int i = 0; i < docs.size(); i++) {
            if (Id.equals(docs.get(i).getDocId()))
                return docs.get(i);
        }
        return null;
    }

//    void initialize(String choice) {
//        docList.getItems().clear();
//        if (choice.equals(LABEL))
//            addLable();
//        else if (choice.equals(CHECKBOX))
//            addCheckBox();
//    }
//
//    void addCheckBox() {
//        for (int i = 0; i < docs.size(); i++) {
//            JFXCheckBox item = new JFXCheckBox(docs.get(i).getDocId());
//            docList.getItems().addAll(item);
//        }
//    }
//
//    void addLable() {
//        for (int i = 0; i < docs.size(); i++) {
//            Label item = new Label(""+docs.get(i).getDocId());
//            docList.getItems().addAll(item);
//        }
//    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        approvalBLService = new ApprovalBLService_Stub();
        docs = approvalBLService.getDocumentList();
        for (int i = 0; i < docs.size(); i++) {
            Label item = new Label("" + docs.get(i).getDocId());
            docList.getItems().add(item);
        }
        docList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


}
