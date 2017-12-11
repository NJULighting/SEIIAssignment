package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/9.
 * Description
 *
 * @author 陈俊宇
 */
public class SalesConditionFilter implements Initializable{
    private SalesConditionTable salesConditionTable;

    @FXML
    JFXButton okBtn;

    @FXML
    void ok(){
        salesConditionTable.getNodesList().animateList(false);
        HamburgerBasicCloseTransition burgTask= salesConditionTable.getBurgTask();
        burgTask.setRate(burgTask.getRate() * -1);

        burgTask.play();
    }

    public void setSalesConditionTable(SalesConditionTable salesConditionTable) {
        this.salesConditionTable = salesConditionTable;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
