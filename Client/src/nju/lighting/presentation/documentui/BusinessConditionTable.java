package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.viewtables.BusinessConditionTableVO;

import java.net.URL;
import java.time.LocalDate;
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
    JFXButton exportBtn, searchBtn, todayBtn, weekBtn, monthBtn, threeMonthBtn, halfYearBtn, yearBtn;

    @FXML
    JFXDatePicker startDate, endDate;

    @FXML
    PieChart revenuePieChart, expenditurePieChart;

    private ObservableList<PieChart.Data> revenueData = FXCollections.observableArrayList(),
            expenditureData = FXCollections.observableArrayList();

    private SimpleDoubleProperty salesRevenue = new SimpleDoubleProperty(),
            commodityGainRevenue = new SimpleDoubleProperty(),
            costAdjustRevenue = new SimpleDoubleProperty(),
            spreadRevenue = new SimpleDoubleProperty(),
            voucherCausedRevenue = new SimpleDoubleProperty(),
            salesRevenueOff = new SimpleDoubleProperty(),
            costExpenditure = new SimpleDoubleProperty(),
            giftExpenditure = new SimpleDoubleProperty(),
            commodityLossExpenditure = new SimpleDoubleProperty(),
            profit = new SimpleDoubleProperty(),
            revenue = new SimpleDoubleProperty(),
            expenditure = new SimpleDoubleProperty();

    /**
     * 搜索按钮绑定的方法，根据起始时间框与截止时间框 调用blService 得到新的经营情况表, 对应刷新界面数据
     */
    @FXML
    private void refresh() {

        Date end = DateHelper.localDateToDate(endDate.getValue());
        end.setDate(end.getDate() + 1);
        businessConditionTable = blService.findRevenueAndExpenditure(
                DateHelper.localDateToDate(startDate.getValue()),
                DateHelper.localDateToDate(endDate.getValue()));


        salesRevenue.set(businessConditionTable.getSalesRevenue());
        commodityGainRevenue.set(businessConditionTable.getCommodityGainRevenue());
        costAdjustRevenue.set(businessConditionTable.getCostAdjustRevenue());
        spreadRevenue.set(businessConditionTable.getSpreadRevenue());
        voucherCausedRevenue.set(businessConditionTable.getVoucherCausedRevenue());
        salesRevenueOff.set(businessConditionTable.getSalesRevenueOff());
        costExpenditure.set(businessConditionTable.getCostExpenditure());
        giftExpenditure.set(businessConditionTable.getGiftExpenditure());
        commodityLossExpenditure.set(businessConditionTable.getCommodityLossExpenditure());
        profit.set(businessConditionTable.getProfit());
        revenue.set(businessConditionTable.getRevenue());
        expenditure.set(businessConditionTable.getExpenditure());

        revenueData.setAll(new PieChart.Data("销售收入", salesRevenue.divide(revenue).doubleValue()),
                new PieChart.Data("商品报溢收入", commodityGainRevenue.divide(revenue).doubleValue()),
                new PieChart.Data("成本调价收入", costAdjustRevenue.divide(revenue).doubleValue()),
                new PieChart.Data("进货退货差价收入", spreadRevenue.divide(revenue).doubleValue()),
                new PieChart.Data("代金券差额收入", voucherCausedRevenue.divide(revenue).doubleValue()));
        expenditureData.setAll(new PieChart.Data("销售成本", costExpenditure.divide(expenditure).doubleValue()),
                new PieChart.Data("商品报损支出", commodityLossExpenditure.divide(expenditure).doubleValue()),
                new PieChart.Data("商品赠出支出", giftExpenditure.divide(expenditure).doubleValue()));
    }

    /**
     * @param pieChart 要设置的饼状图
     * @param side     饼状图中label放置的位置
     */

    private void setPieChart(PieChart pieChart, Side side) {
        pieChart.setLegendSide(side);
        pieChart.setClockwise(false);
        pieChart.setLabelsVisible(false);

    }

    /**
     * 几个快捷按钮所绑定的方法
     * 将传入的起始时间设置为起始时间，并以当前时间为截止时间，设置到datePicker中,并进行搜索，刷新界面
     * @param start Date类型的起始时间
     */
    private void setTime(LocalDate start) {
        startDate.setValue(start);
        endDate.setValue(DateHelper.dateToLocalDate(new Date()));
        refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DateHelper.setDefaultTime(startDate,DateHelper.weekAgo());
        DateHelper.setDefaultTime(endDate,DateHelper.dateToLocalDate(new Date()));
        refresh();

        revenuePieChart.setData(revenueData);
        expenditurePieChart.setData(expenditureData);
        setPieChart(revenuePieChart, Side.LEFT);
        setPieChart(expenditurePieChart, Side.LEFT);


        salesRevenueText.textProperty().bind(salesRevenue.asString());
        commodityGainRevenueText.textProperty().bind(commodityGainRevenue.asString());
        costAdjustRevenueText.textProperty().bind(costAdjustRevenue.asString());
        spreadRevenueText.textProperty().bind(spreadRevenue.asString());
        voucherCausedRevenueText.textProperty().bind(voucherCausedRevenue.asString());
        salesRevenueText.textProperty().bind(salesRevenue.asString());
        costExpenditureText.textProperty().bind(costExpenditure.asString());
        giftExpenditureText.textProperty().bind(giftExpenditure.asString());
        commodityLossExpenditureText.textProperty().bind(commodityLossExpenditure.asString());
        profitText.textProperty().bind(profit.asString());
        revenueText.textProperty().bind(revenue.asString());
        expenditureText.textProperty().bind(expenditure.asString());
        salesRevenueOffText.textProperty().bind(salesRevenueOff.asString());

        todayBtn.setOnAction(e -> setTime(DateHelper.dateToLocalDate(new Date())));
        weekBtn.setOnAction(e -> setTime(DateHelper.weekAgo()));
        monthBtn.setOnAction(e -> setTime(DateHelper.monthAgo()));
        threeMonthBtn.setOnAction(e->setTime(DateHelper.threeMonthsAgo()));
        halfYearBtn.setOnAction(e-> setTime(DateHelper.halfYearAgo()));
        yearBtn.setOnAction(e-> setTime(DateHelper.yearAgo()));


        exportBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择保存路径");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files", "*.xls"));
            fileChooser.showSaveDialog(new Stage());
        });
    }
}
