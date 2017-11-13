package nju.lighting.vo.viewtables;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class SalesDetailVO {
    private double salesRevenue; //销售收入

    private double commodityGainRevenue; //商品报溢收入

    private double costAdjustRevenue; //成本调价收入

    private double spreadRevenue;  //近退货差价收入

    private double voucherCausedRevenue; //代金券与实际收款额差额收入

    private double salesRevenueOff; //销售收入折让额

    private double costExpenditure;  //成本支出

    private double commodityLossExpenditure; //商品报损支出

    private double giftExpenditure; //赠品支出

    private double profit;


    public SalesDetailVO(double salesRevenue, double commodityGainRevenue, double costAdjustRevenue, double spreadRevenue,
                         double voucherCausedRevenue, double salesRevenueOff, double costExpenditure, double commodityLossExpenditure,
                         double giftExpenditure, double profit) {
        this.salesRevenue = salesRevenue;
        this.commodityGainRevenue = commodityGainRevenue;
        this.costAdjustRevenue = costAdjustRevenue;
        this.spreadRevenue = spreadRevenue;
        this.voucherCausedRevenue = voucherCausedRevenue;
        this.salesRevenueOff = salesRevenueOff;
        this.costExpenditure = costExpenditure;
        this.commodityLossExpenditure = commodityLossExpenditure;
        this.giftExpenditure = giftExpenditure;
        this.profit = profit;
    }

    public double getSalesRevenue() {
        return salesRevenue;
    }

    public void setSalesRevenue(double salesRevenue) {
        this.salesRevenue = salesRevenue;
    }

    public double getCommodityGainRevenue() {
        return commodityGainRevenue;
    }

    public void setCommodityGainRevenue(double commodityGainRevenue) {
        this.commodityGainRevenue = commodityGainRevenue;
    }

    public double getCostAdjustRevenue() {
        return costAdjustRevenue;
    }

    public void setCostAdjustRevenue(double costAdjustRevenue) {
        this.costAdjustRevenue = costAdjustRevenue;
    }

    public double getSpreadRevenue() {
        return spreadRevenue;
    }

    public void setSpreadRevenue(double spreadRevenue) {
        this.spreadRevenue = spreadRevenue;
    }

    public double getVoucherCausedRevenue() {
        return voucherCausedRevenue;
    }

    public void setVoucherCausedRevenue(double voucherCausedRevenue) {
        this.voucherCausedRevenue = voucherCausedRevenue;
    }

    public double getSalesRevenueOff() {
        return salesRevenueOff;
    }

    public void setSalesRevenueOff(double salesRevenueOff) {
        this.salesRevenueOff = salesRevenueOff;
    }

    public double getCommodityLossExpenditure() {
        return commodityLossExpenditure;
    }

    public void setCommodityLossExpenditure(double commodityLossExpenditure) {
        this.commodityLossExpenditure = commodityLossExpenditure;
    }

    public double getCostExpenditure() {
        return costExpenditure;
    }

    public void setCostExpenditure(double costExpenditure) {
        this.costExpenditure = costExpenditure;
    }

    public double getGiftExpenditure() {
        return giftExpenditure;
    }

    public void setGiftExpenditure(double giftExpenditure) {
        this.giftExpenditure = giftExpenditure;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }


}
