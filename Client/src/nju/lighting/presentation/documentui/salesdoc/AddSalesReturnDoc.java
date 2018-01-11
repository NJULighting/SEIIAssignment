package nju.lighting.presentation.documentui.salesdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.documentui.AddDoc;
import nju.lighting.presentation.documentui.Modifiable;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.salesdoc.SalesReturnDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/24.
 * Description
 *
 * @author 陈俊宇
 */
public class AddSalesReturnDoc extends AddDoc implements Initializable {
    @FXML
    Pane container;

    private AddSalesDoc controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSalesDoc.fxml"));

        try {
            container.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller = loader.getController();
        controller.setReturn();
    }

    @Override
    protected DocVO getDoc() {
        return controller.getReturnDoc();
    }

    @Override
    public void modify(Upper upper, DocVO docVO, boolean redFlush) {
        SalesReturnDocVO salesReturnDocVO = (SalesReturnDocVO) docVO;

        controller.init(upper, salesReturnDocVO.getItems(), salesReturnDocVO.getCustomerId(),
                salesReturnDocVO.getRemarks(), salesReturnDocVO.getVoucher() + "",
                salesReturnDocVO.getDiscount() + "", false);

        commitBtn = controller.getCommitBtn();
        super.modify(upper, docVO, redFlush);

    }

    @Override
    public Node getMainPane() {
        return controller.getMainPane();
    }
}
