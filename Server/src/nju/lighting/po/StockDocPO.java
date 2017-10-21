package nju.lighting.po;

public class StockDocPO extends DocPO {

    private String ID;
    private CustomerPO supplier;
    private String repository;
    private UserPO operator;
    private CommodityListPO commodityList;
    private String remarks;
    private double totalAmount = 0;

    public StockDocPO(String ID,CustomerPO supplier,String repository,UserPO operator,CommodityListPO commodityList,String remarks,double totalAmount){
        this.ID =ID;
        this.supplier =supplier;
        this.repository=repository;
        this.operator=operator;
        this. commodityList =commodityList;
        this.remarks =remarks;
        this.totalAmount =totalAmount;
    }

    public void setID(String id){
        ID = id;
    }
    public void setSupplier(CustomerPO customer){
        supplier = customer;
    }
    public void setRepository(String repository){
        this.repository = repository;
    }
    public void setOperator(UserPO user){
        operator = user;
    }
    public void setCommodityList(CommodityListPO commodityList){
        this.commodityList = commodityList;
        totalAmount = commodityList.getTotalAmount();
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public String getID(){return  ID;}
    public CustomerPO getSupplier(){return supplier;}
    public String getRepository(){return  repository;}
    public UserPO getOperator(){return  operator;}
    public CommodityListPO getCommodityList(){return  commodityList;}
    public String getRemarks(){return remarks;}
    public double getTotalAmount(){return totalAmount;}


}
