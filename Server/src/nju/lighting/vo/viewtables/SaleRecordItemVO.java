package nju.lighting.vo.viewtables;

import nju.lighting.vo.DocVO;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class SaleRecordItemVO {
    private long date;

    private String commodityName;

    private String commodityType;

    private int number;

    private double salePrice;

    private double totalAmount;

    private DocVO doc;

    public SaleRecordItemVO(long date, String commodityName, String commodityType, int number, double salePrice, double totalAmount,DocVO docVO) {
        this.date = date;
        this.commodityName = commodityName;
        this.commodityType = commodityType;
        this.number = number;
        this.salePrice = salePrice;
        this.totalAmount = totalAmount;
        this.doc=docVO;
    }

    public long getDate(){return date;}

    public String getName(){return commodityName;}

    public String getCommodityType(){return commodityType;}

    public int getNumber(){return number;}

    public double getSalePrice(){ return salePrice;}

    public double getTotalAmount(){return totalAmount;}

    public void setDate(long date){this.date=date;}

    public void setName(String name) {
        this.commodityName = name;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public DocVO getDoc() {
        return doc;
    }

    public void setDoc(DocVO doc) {
        this.doc = doc;
    }
}
