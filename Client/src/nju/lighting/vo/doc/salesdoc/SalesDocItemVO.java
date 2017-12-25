package nju.lighting.vo.doc.salesdoc;

import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
public class SalesDocItemVO {

    private int id;
    private BasicCommodityItemVO commodity;
    private int number;
    private int price;
    private double totalAmount;
    private String remarks = "";

    /**
     * Constructor for pre
     */
    public SalesDocItemVO(int number, String remarks, BasicCommodityItemVO commodity) {
        this.commodity = commodity;
        this.number = number;
        this.remarks = remarks;
        totalAmount = number * commodity.getRecentSellPrice();
    }

    /**
     * Constructor for bl
     */
    public SalesDocItemVO(BasicCommodityItemVO commodity, int number, double totalAmount, String remarks) {
        this.commodity = commodity;
        this.number = number;
        this.totalAmount = totalAmount;
        this.remarks = remarks;
    }

    public SalesDocItemVO(int id, BasicCommodityItemVO commodity, int number, double totalAmount, String remarks) {
        this.id = id;
        this.commodity = commodity;
        this.number = number;
        this.totalAmount = totalAmount;
        this.remarks = remarks;
    }

    private void updateTotalAmount() {
        totalAmount = number * commodity.getRecentSellPrice();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        updateTotalAmount();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }

    public int getId() {
        return id;
    }

    SalesDocItemPO toPO() {
        return new SalesDocItemPO(commodity.getId(), number, totalAmount, remarks);
    }

}
