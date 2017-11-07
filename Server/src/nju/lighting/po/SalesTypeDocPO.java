package nju.lighting.po;

import nju.lighting.po.doc.DocPO;

public class SalesTypeDocPO extends DocPO {

    private String ID;
    private CustomerPO customer;
    private String salesman;
    private String repository;
    private UserPO operator;
    private CommodityListPO commodityList;
    private String remarks;
    private double beforeDiscountAmount = 0;
    private double discount = 0;
    private double voucher = 0;
    private double finalAmount = 0;

    private void updateFinalAmount() {
        finalAmount = beforeDiscountAmount - discount - voucher;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        ID = id;
    }

    public CustomerPO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerPO customer) {
        this.customer = customer;
        this.salesman = customer.getSalesman();
    }

    public String getSalesman() {
        return salesman;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public UserPO getOperator() {
        return operator;
    }

    public void setOperator(UserPO user) {
        operator = user;
    }

    public CommodityListPO getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(CommodityListPO commodityList) {
        this.commodityList = commodityList;
        beforeDiscountAmount = commodityList.getTotalAmount();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getBeforeDiscountAmount() {
        return beforeDiscountAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        updateFinalAmount();
        ;
    }

    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
        updateFinalAmount();
        ;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

}
