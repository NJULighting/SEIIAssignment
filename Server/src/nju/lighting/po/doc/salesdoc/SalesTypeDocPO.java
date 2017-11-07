package nju.lighting.po.doc.salesdoc;

import nju.lighting.po.DocPO;

public class SalesTypeDocPO extends DocPO {

    private String salesTypeDocID;
    private String customerId;
    private String repository;
    private String userId;
    private String remarks;
    private double discount = 0;
    private double voucher = 0;
    private double finalAmount =0;

    public void setSalesTypeDocID(String salesTypeDocID){
        this.salesTypeDocID = salesTypeDocID;
    }
    public void setCustomerId(String customerId){this.customerId = customerId;}
    public void setRepository(String repository){
        this.repository = repository;
    }
    public void setUserId(String userId){this.userId = userId;}
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }
    public void setVoucher(double voucher){
        this.voucher = voucher;
    }
    public void setFinalAmount(double finalAmount){
        this.finalAmount = finalAmount;
    }

    public String getSalesTypeDocID(){return  salesTypeDocID;}
    public String getCustomerId(){return customerId;}
    public String getRepository(){return  repository;}
    public String getUserID(){return userId;}
    public String getRemarks(){return remarks;}
    public double getDiscount(){return discount;}
    public double getVoucher(){return voucher;}
    public double getFinalAmount(){return finalAmount;}

}
