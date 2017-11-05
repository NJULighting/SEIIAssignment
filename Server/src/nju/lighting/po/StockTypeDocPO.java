package nju.lighting.po;

public class StockTypeDocPO extends DocPO {

    private String ID;
    private CustomerPO supplier;
    private String repository;
    private UserPO operator;
    private CommodityListPO commodityList;
    private String remarks;
    private double totalAmount = 0;

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        ID = id;
    }

    public CustomerPO getSupplier() {
        return supplier;
    }

    public void setSupplier(CustomerPO customer) {
        supplier = customer;
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
        totalAmount = commodityList.getTotalAmount();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


}
