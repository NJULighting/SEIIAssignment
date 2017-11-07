package nju.lighting.vo;

import shared.DocType;

import java.util.Date;

public class SalesTypeDocVO extends DocVO{

    private String salesTypeDocID;
    private String customerId;
    private String salesman;
    private String repository;
    private String userId;
    private String remarks;
    private double beforeDiscountAmount=0;
    private double discount=0;
    private double voucher=0;
    private double finalAmount=0;

    public void setSalesTypeDocID(String salesTypeDocID){
        this.salesTypeDocID = salesTypeDocID;
    }
    public void setCustomerId(String customerId){this.customerId = customerId;}
    public void setSalesman(String salesman){this.salesman = salesman;}
    public void setRepository(String repository){
        this.repository = repository;
    }
    public void setUserId(String userId){this.userId = userId;}
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public void setBeforeDiscountAmount(double beforeDiscountAmount){this.beforeDiscountAmount = beforeDiscountAmount;}
    public void setDiscount(double discount){
        this.discount = discount;
    }
    public void setVoucher(double voucher){
        this.voucher = voucher;
    }
    public void setFinalAmount(double finalAmount){this.beforeDiscountAmount = beforeDiscountAmount;}

    public String getSalesTypeDocID(){return  salesTypeDocID;}
    public String getCustomerId(){return customerId;}
    public String getSalesman(){return salesman;}
    public String getRepository(){return  repository;}
    public String getUserId(){return  userId;}
    public String getRemarks(){return remarks;}
    public double getBeforeDiscountAmount(){return beforeDiscountAmount;}
    public double getDiscount(){return discount;}
    public double getVoucher(){return voucher;}
    public double getFinalAmount(){return finalAmount;}

    public SalesTypeDocVO(Date time, String creatorId, String docId, DocType type,
                          String salesTypeDocID, String customerId, String salesman,
                          String repository, String userId, String remarks,
                          double beforeDiscountAmount, double discount,
                          double voucher, double finalAmount) {
        super(time, creatorId, docId, type);
        this.salesTypeDocID = salesTypeDocID;
        this.customerId = customerId;
        this.salesman = salesman;
        this.repository = repository;
        this.userId = userId;
        this.remarks = remarks;
        this.beforeDiscountAmount = beforeDiscountAmount;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
    }
}
