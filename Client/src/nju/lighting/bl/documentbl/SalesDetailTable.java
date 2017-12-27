package nju.lighting.bl.documentbl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.giftdoc.GiftDoc;
import nju.lighting.bl.documentbl.lossandgaindoc.LossAndGainDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesDoc;
import nju.lighting.bl.documentbl.stockdoc.StockReturnDoc;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.viewtables.SalesDetailVO;
import shared.DocType;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/11/7.
 * Description: 处理销售明细表业务相关功能
 * @author Liao
 */
public class SalesDetailTable {
    private double salesRevenue;
    private double commodityGainRevenue;
    private double costAdjustRevenue;
    private double spreadRevenue;
    private double voucherCausedRevenue;
    private double salesRevenueOff;
    private double costExpenditure;
    private double commodityLossExpenditure;
    private double giftExpenditure;
    private double profit;

    private List<LossAndGainDoc> lossAndGainDocs;
    private List<SalesDoc> salesDocs;
    private List<StockReturnDoc> stockReturnDocs;
    private List<GiftDoc> giftDocs;

    private Date startDate;
    private Date endDate;

    private DocDataService dataService;

    public SalesDetailTable(Date startDate, Date endDate) {

        try {
            dataService = DataFactory.getDataBase(DocDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        this.startDate = startDate;
        this.endDate = endDate;

        lossAndGainDocs = findToDoc(LossAndGainDoc.class, DocType.LOSS_AND_GAIN);
        salesDocs = findToDoc(SalesDoc.class, DocType.SALES);
        stockReturnDocs = findToDoc(StockReturnDoc.class, DocType.STOCK_RETURN);
        giftDocs = findToDoc(GiftDoc.class, DocType.GIFT);

        calculateCostAdjustRevenue();
        calculateGiftDoc();
        calculateLossAndGainDoc();
        calculateSalesDoc();
        calculateSpreadRevenue();
        calculateProfit();
    }

    private void calculateProfit() {
        profit = salesRevenue + commodityGainRevenue + voucherCausedRevenue + spreadRevenue + costAdjustRevenue
                - costExpenditure - commodityLossExpenditure - giftExpenditure;
    }

    /**
     * 根据所给的日期区间生成经营情况表
     * @return 相对应的经营情况表的值对象
     */
    public SalesDetailVO getSalesDetailTable() {
        return new SalesDetailVO(salesRevenue, commodityGainRevenue, costAdjustRevenue, spreadRevenue,
                voucherCausedRevenue, salesRevenueOff, costExpenditure, commodityLossExpenditure,
                giftExpenditure, profit);
    }

    private void calculateSalesDoc() {
        for (SalesDoc salesDoc : salesDocs) {
            salesRevenue += salesDoc.getFinalAmount();
            salesRevenueOff += salesDoc.getDiscountAmount();
            voucherCausedRevenue += salesDoc.getVoucherCausedRevenue();
            costExpenditure += salesDoc.getTotalCost();
        }
    }

    private void calculateLossAndGainDoc() {
        lossAndGainDocs.forEach(doc -> {
            double amount = doc.getAmount();
            if (amount >= 0) commodityGainRevenue += amount;
            else commodityLossExpenditure -= amount;
        });
    }

    private void calculateGiftDoc() {
        giftExpenditure = giftDocs.stream().mapToDouble(GiftDoc::getTotal).sum();
    }

    private void calculateSpreadRevenue() {
        spreadRevenue = stockReturnDocs.stream().mapToDouble(StockReturnDoc::getRevenue).sum();
    }

    private void calculateCostAdjustRevenue() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        costAdjustRevenue = commodityInfo.getCostAdjustRevenue();
    }

    private <T> List<T> findToDoc(Class<T> type, DocType docType) {
        try {
            DocFactory docFactory = DocFactory.INSTANT;
            return dataService.findByTimeAndType(startDate, endDate, docType)
                    .stream()
                    .map(docFactory::poToDoc)
                    .map(type::cast)
                    .collect(Collectors.toList());
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
