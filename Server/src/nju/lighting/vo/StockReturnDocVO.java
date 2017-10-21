package nju.lighting.vo;

import nju.lighting.po.CommodityListPO;
import nju.lighting.po.CustomerPO;
import nju.lighting.po.DocPO;
import nju.lighting.po.UserPO;

public class StockReturnDocVO extends DocPO {

    private String ID;
    private CustomerPO supplier;
    private String repository;
    private UserPO operator;
    private CommodityListPO commodityList;
    private String remarks;
    private double totalAmount = 0;

    public StockReturnDocVO(String id, CustomerPO su, String re, UserPO u, CommodityListPO com, String rem, double toA){
        ID =id;
        supplier =su;
        repository=re;
        operator=u;
        commodityList =com;
        remarks =rem;
        totalAmount =toA;
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
