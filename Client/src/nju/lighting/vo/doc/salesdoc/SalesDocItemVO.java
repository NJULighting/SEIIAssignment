package nju.lighting.vo.doc.salesdoc;

import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
public class SalesDocItemVO {

    private BasicCommodityItemVO commodity;
    private int number;
    private double salePrice;
    private double totalAmount;
    private String remarks = "";

    public SalesDocItemVO(int number, double salePrice, String remarks, BasicCommodityItemVO commodity) {
        this.commodity = commodity;
        this.number = number;
        this.salePrice = salePrice;
        this.remarks = remarks;

        updateTotalAmount();
    }

    private void updateTotalAmount() {
        totalAmount = number * salePrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        updateTotalAmount();
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
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

    SalesDocItemPO toPO() {
        return new SalesDocItemPO(commodity.getId(), number, totalAmount, remarks);
    }

}
