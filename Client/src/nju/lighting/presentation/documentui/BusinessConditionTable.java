package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.viewtables.BusinessConditionTableVO;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


/**
 * Created on 2017/12/29.
 * Description
 *
 * @author 陈俊宇
 */
public class BusinessConditionTable implements Initializable {

    private DocBLService blService = DocBLServiceFactory.getDocBLService();
    private BusinessConditionTableVO businessConditionTable;

    @FXML
    Label salesRevenueText, commodityGainRevenueText, costAdjustRevenueText, spreadRevenueText,
            voucherCausedRevenueText, salesRevenueOffText, costExpenditureText, giftExpenditureText,
            commodityLossExpenditureText, profitText, revenueText, expenditureText;


    @FXML
    JFXButton exportBtn,searchBtn,todayBtn,weekBtn,monthBtn,threeMonthBtn,halfYearBtn,yearBtn;

    @FXML
    JFXDatePicker startDate,endDate;

    private void refresh() {
        salesRevenueText.setText(businessConditionTable.getSalesRevenue() + "");
        commodityGainRevenueText.setText(businessConditionTable.getCommodityGainRevenue() + "");
        costAdjustRevenueText.setText(businessConditionTable.getCostAdjustRevenue()+"");
        spreadRevenueText.setText(businessConditionTable.getSpreadRevenue()+"");
        voucherCausedRevenueText.setText(businessConditionTable.getVoucherCausedRevenue()+"");
        salesRevenueOffText.setText(businessConditionTable.getSalesRevenueOff()+"");
        costExpenditureText.setText(businessConditionTable.getCostExpenditure()+"");
        giftExpenditureText.setText(businessConditionTable.getGiftExpenditure()+"");
        commodityLossExpenditureText.setText(businessConditionTable.getCommodityLossExpenditure()+"");
        profitText.setText(businessConditionTable.getProfit()+"");
        revenueText.setText(businessConditionTable.getRevenue()+"");
        expenditureText.setText(businessConditionTable.getExpenditure()+"");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        businessConditionTable = blService.findRevenueAndExpenditure(DateHelper.weekAgo(), new Date());
        refresh();

        startDate.setValue(DateHelper.dateToLocalDate(DateHelper.weekAgo()));
        endDate.setValue(DateHelper.dateToLocalDate(new Date()));



        exportBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择保存路径");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files", "*.xls"));
            fileChooser.showSaveDialog(new Stage());
        });
    }
}
