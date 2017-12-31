package nju.lighting.vo.viewtables;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class BusinessConditionTableVO {
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

    private double revenue;

    private double expenditure;


    public BusinessConditionTableVO(double salesRevenue, double commodityGainRevenue, double costAdjustRevenue, double spreadRevenue,
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
        revenue=salesRevenue+commodityGainRevenue+costAdjustRevenue+spreadRevenue+voucherCausedRevenue;
        expenditure=costExpenditure+commodityLossExpenditure+giftExpenditure;
    }

    /*
    for stub
     */
    public BusinessConditionTableVO(double salesRevenue, double commodityGainRevenue, double costAdjustRevenue, double spreadRevenue, double voucherCausedRevenue, double salesRevenueOff,
                                    double costExpenditure, double commodityLossExpenditure, double giftExpenditure) {
        this.salesRevenue = salesRevenue;
        this.commodityGainRevenue = commodityGainRevenue;
        this.costAdjustRevenue = costAdjustRevenue;
        this.spreadRevenue = spreadRevenue;
        this.voucherCausedRevenue = voucherCausedRevenue;
        this.salesRevenueOff = salesRevenueOff;
        this.costExpenditure = costExpenditure;
        this.commodityLossExpenditure = commodityLossExpenditure;
        this.giftExpenditure = giftExpenditure;
        revenue=salesRevenue+commodityGainRevenue+costAdjustRevenue+spreadRevenue+voucherCausedRevenue;
        expenditure=costExpenditure+commodityLossExpenditure+giftExpenditure;
        profit=revenue-expenditure;
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

    public double getRevenue() {
        return revenue;
    }

    public double getExpenditure() {
        return expenditure;
    }

    @Override
    public String toString() {
        return "BusinessConditionTableVO{" +
                "salesRevenue=" + salesRevenue +
                ", commodityGainRevenue=" + commodityGainRevenue +
                ", costAdjustRevenue=" + costAdjustRevenue +
                ", spreadRevenue=" + spreadRevenue +
                ", voucherCausedRevenue=" + voucherCausedRevenue +
                ", salesRevenueOff=" + salesRevenueOff +
                ", costExpenditure=" + costExpenditure +
                ", commodityLossExpenditure=" + commodityLossExpenditure +
                ", giftExpenditure=" + giftExpenditure +
                ", profit=" + profit +
                '}';
    }
}
