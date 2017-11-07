package shared;

import shared.DocType;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class BusinessHistoryFilter {
    private long startDate;

    private long endDate;

    private DocType type;

    private String customer;

    private String  user;

    private String repository;

    public long getStartDate(){return startDate;}

    public long getEndDate(){return endDate;}

    public DocType getDocType(){return type;}

    public String getRepository(){return repository;}

    public String getCustomer(){return  customer;}

    public String getUser(){return user;}

    public void setStartDate(long startDate){
        this.startDate=startDate;
    }

    public void setEndDate(long endDate){
        this.endDate=endDate;
    }

    public void setType(DocType type){
        this.type=type;
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