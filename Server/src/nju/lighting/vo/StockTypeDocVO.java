package nju.lighting.vo;

public class StockTypeDocVO extends DocVO {

    private String ID;
    private CustomerVO supplier;
    private String repository;
    private UserVO operator;
    private CommodityListVO commodityList;
    private String remarks;
    private double totalAmount = 0;

    public void setID(String id){
        ID = id;
    }
    public void setSupplier(CustomerVO customer){
        supplier = customer;
    }
    public void setRepository(String repository){
        this.repository = repository;
    }
    public void setOperator(UserVO user){
        operator = user;
    }
    public void setCommodityList(CommodityListVO commodityList){
        this.commodityList = commodityList;
        totalAmount = commodityList.getTotalAmount();
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public String getID(){return  ID;}
    public CustomerVO getSupplier(){return supplier;}
    public String getRepository(){return  repository;}
    public UserVO getOperator(){return  operator;}
    public CommodityListVO getCommodityList(){return  commodityList;}
    public String getRemarks(){return remarks;}
    public double getTotalAmount(){return totalAmount;}


}
