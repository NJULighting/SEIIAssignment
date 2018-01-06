package nju.lighting.presentation.documentui.lossandgaindoc;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nju.lighting.presentation.documentui.Modifiable;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.DocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/25.
 * Description
 * 制定报损报溢单的主界面，库存人员界面上制定报损报溢单即跳转到此界面
 * @author 陈俊宇
 */
public class AddLossAndGainDoc implements Initializable, Upper,Modifiable {
    @FXML
    HBox container;

    @FXML
    Label sub, title;

    @FXML
    StackPane stackPane;


    private Pane mainPane;

    private AddLossAndGainDocUI controller;

    private ObservableList<LossAndGainItem> observableList;



    public void back() {
        setChildren(mainPane, "");
    }


    public void set(Node node) {
        container.getChildren().setAll(node);
    }

    public void setChildren(Node children, String title) {
        container.getChildren().setAll(children);
        sub.setText(title);
    }

    public void setAlert() {
        title.setText("制定报警单");
        controller.setAlert();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddLossAndGainDocUI.fxml"));
        try {
            mainPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        container.getChildren().add(mainPane);

        controller = loader.getController();
        controller.setMain(this);
        observableList=controller.docItemList;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public ObservableList<LossAndGainItem> getObservableList() {
        return observableList;
    }

    public String getComments(){return controller.getComments();}
    public void setComments(String comments){controller.setComments(comments);}

    @Override
    public void modify(DocVO docVO, boolean redFlush) {

    }

    public JFXButton getCommitBtn(){return controller.getCommitBtn();}
}
