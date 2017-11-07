package nju.lighting.po.doc.salesdoc;
/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
import nju.lighting.po.DocPO;

public class SalesTypeDocPO extends DocPO{

    private String SalesTypeDocID;
    private String customerId;
    private String salesman;
    private String repository;
    private String userId;
    private String remarks="";
    private double beforeDiscountAmount = 0;
    private double discount = 0;
    private double voucher = 0;
    private double finalAmount =0;


    private void updateFinalAmount(){
        finalAmount = beforeDiscountAmount -discount -voucher;
    }

    public void setSalesTypeDocID(String SalesTypeDocID){
        this.SalesTypeDocID = SalesTypeDocID;
    }
    public void setCustomerId(String customerId){ this.customerId=customerId; }
    public void setSalesman(String salesman){ this.salesman = salesman; }
    public void setRepository(String repository){
        this.repository = repository;
    }
    public void setUserId(String userId){ this.userId= userId; }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public void setBeforeDiscountAmount(double beforeDiscountAmount){ this.beforeDiscountAmount = beforeDiscountAmount;}
    public void setDiscount(double discount){
        this.discount = discount;
        updateFinalAmount();;
    }
    public void setVoucher(double voucher){
        this.voucher = voucher;
        updateFinalAmount();;
    }

    public String getSalesTypeDocID(){return SalesTypeDocID;}
    public String getCustomerId(){return customerId;}
    public String getSalesman(){return salesman;}
    public String getRepository(){return  repository;}
    public String getUserId(){return  userId;}
    public String getRemarks(){return remarks;}
    public double getBeforeDiscountAmount(){return beforeDiscountAmount;}
    public double getDiscount(){return discount;}
    public double getVoucher(){return voucher;}
    public double getFinalAmount(){return finalAmount;}

}
