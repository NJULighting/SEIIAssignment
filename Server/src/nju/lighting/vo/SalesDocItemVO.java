package nju.lighting.vo;
/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
public class SalesDocItemVO {

    private int id;
    private String salesDocID;
    private String commodityID;
    private int number = 0;
    private double totalAmount = 0;
    private String remarks = "";

    public SalesDocItemVO(){}

    /**
     *
     * @param id
     * 销售类单据中一条商品条目的内部id
     * @param SalesDocID
     * 此条商品条目所属的显示类单据编号
     * @param commodityID
     * 此条商品条目中的商品ID
     * @param number
     * 此条商品条目中的商品数量
     * @param remarks
     * 此条商品条目中的备注
     */
    public SalesDocItemVO(int id, String SalesDocID, String commodityID,
                          int number, String remarks){
        this.id = id;
        this.salesDocID = SalesDocID;
        this.commodityID=commodityID;
        this.number=number;
        this.remarks=remarks;
    }

    public void setId(int id){ this.id=id; }
    public void setSalesDocID(String salesDocID){ this.salesDocID = salesDocID; }
    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }
    public void setNumber(int number){ this.number = number; }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public void setTotalAmount(double totalAmount){this.totalAmount = totalAmount; }


    public int getId(){ return id; }
    public String getSalesDocID(){ return salesDocID; }
    public String getCommodityID(){ return commodityID; }
    public int getNumber(){return number;}
    public double getTotalAmount(){return totalAmount;}
    public String getRemarks(){return remarks; }

}
