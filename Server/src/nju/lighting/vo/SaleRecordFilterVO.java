package nju.lighting.vo;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class SaleRecordFilterVO {
    private long startDate;

    private long endDate;

    private String commodity;

    private String customer;

    private String user;

    private String repository;

    public long getStartDate(){return startDate;}

    public long getEndDate(){return endDate;}

    public String getCommodity(){return commodity;}

    public String getRepository(){return repository;}

    public String getCustomer(){return  customer;}

    public String getUser(){return user;}

    public void setStartDate(long startDate){
        this.startDate=startDate;
    }

    public void setEndDate(long endDate){
        this.endDate=endDate;
    }

    public void setType(String commodity){
        this.commodity=commodity;
    }

    public void setCustomer(String customer){
        this.customer=customer;
    }

    public void setUser(String user){
        this.user=user;
    }

    public void setRepository(String repository){
        this.repository=repository;
    }


}
