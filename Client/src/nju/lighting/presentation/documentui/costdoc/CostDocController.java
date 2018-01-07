package nju.lighting.presentation.documentui.costdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.doc.costdoc.CostDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CostDocController implements Initializable {

    @FXML
    Label account,total,creator,id,time;
    @FXML
    HBox tableContainer;
    private CostDocVO costDocVO;

    public CostDocController(){costDocVO= (CostDocVO) Doc.getDoc();}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        account.setText(costDocVO.getAccount().getId());
        total.setText(costDocVO.getTotal()+"");
        creator.setText(UserHelper.getUserString(costDocVO.getCreatorId()));
        id.setText(costDocVO.getDocId());
        time.setText(DateHelper.approximateTime(costDocVO.getTime()));

        FXMLLoader loader=new FXMLLoader(getClass().getResource("CostItemList.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        CostItemList controller=loader.getController();
        controller.getObservableList().addAll(costDocVO.getItemList());
    }
}
