package nju.lighting.vo.doc.stockdoc;

import nju.lighting.po.doc.stockdoc.StockDocItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
public class StockDocItemVO {

    private BasicCommodityItemVO commodity;
    private int number;
    private double totalAmount;
    private String remarks = "";

    public StockDocItemVO(BasicCommodityItemVO commodity, int number, String remarks, double totalAmount) {
        this.commodity = commodity;
        this.number = number;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    StockDocItemPO toPO() {
        return new StockDocItemPO(commodity.getId(), number, totalAmount, remarks);
    }
}
