package nju.lighting.presentation.documentui.lossandgaindoc;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.documentui.Modifiable;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocVO;
import shared.LossAndGainItemType;
import shared.Result;
import shared.ResultMessage;
import shared.TwoTuple;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/25.
 * Description
 * 制定报损报溢单的主界面，库存人员界面上制定报损报溢单即跳转到此界面
 *
 * @author 陈俊宇
 */
public class AddLossAndGainDoc implements Initializable, Upper, Modifiable {
    @FXML
    HBox container, tableContainer;

    @FXML
    Label sub, title;

    @FXML
    StackPane stackPane;


    @FXML
    Button chooseCommodityBtn;

    @FXML
    JFXButton commitBtn;

    @FXML

    TextArea comments;

    @FXML
    Pane mainPane;


    private ObservableList<BasicCommodityItemVO> commodities = FXCollections.observableArrayList();
    private ObservableList<LossAndGainItem> docItemList;

    private DocBLService blService = DocBLServiceFactory.getDocBLService();

    private FXMLLoader loader;

    private LossAndGainList listController;


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
        listController.setAlert();
    }

    @FXML
    void removeAll() {
        docItemList.clear();
    }

    private LossAndGainDocVO getDoc(){
        return new LossAndGainDocVO(new Date(),
                Client.getUserVO().getID(),
                docItemList.stream()
                        .map(x -> x.toLossAndGainDocItemVO())
                        .collect(Collectors.toList()),
                comments.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commitBtn.setOnAction(e -> {
            Result<DocVO> res = blService.commitDoc(getDoc());
            DialogHelper.dialog("提交报损报溢单",res.getResultMessage(), stackPane);
        });

        chooseCommodityBtn.setOnAction(event -> {
            CommodityHelper.chooseCommodity(this, commodities);

        });

        loader = new FXMLLoader(getClass().getResource("LossAndGainList.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listController = loader.getController();
        listController.setEditable();
        docItemList = listController.getData();

        commodities.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()) {
                    if (c.wasAdded()) {
                        docItemList.addAll(
                                c.getAddedSubList().stream()
                                        .map(x -> new LossAndGainItem(x, 1, LossAndGainItemType.LOSS))
                                        .collect(Collectors.toList())
                        );
                    }
                }
            }
        });


    }


    public ObservableList<LossAndGainItem> getObservableList() {
        return docItemList;
    }

    public String getComments() {
        return comments.getText();
    }

    public void setComments(String comments) {
        this.comments.setText(comments);
    }

    @Override
    public void modify(Upper upper,DocVO docVO, boolean redFlush) {
        LossAndGainDocVO lossAndGainDoc= (LossAndGainDocVO) docVO;

        docItemList.setAll(lossAndGainDoc.getItems().stream()
        .map(LossAndGainItem::new)
        .collect(Collectors.toList()));

        setComments(lossAndGainDoc.getComment());

        if (!redFlush)
        commitBtn.setOnAction(e -> {
             DocHelper.saveAndApprove(getDoc());
        });
    }

    @Override
    public Node getMainPane() {
        return mainPane;
    }

    public JFXButton getCommitBtn() {
        return commitBtn;
    }
}
