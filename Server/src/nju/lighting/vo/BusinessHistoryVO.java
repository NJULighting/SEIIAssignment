package nju.lighting.vo;

import nju.lighting.po.DocType;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class BusinessHistoryVO {

    private long date;

    private DocType type;

    private String customer;

    private String repository;


    public long getDate(){return date;}

    public DocType getType(){return  type;}

    public String getCustomer(){return customer;}

    public String getRepository(){return repository;}

    public void setDate(long date){this.date=date;}

    public void setType(DocType type){this.type=type;}

    public void setCustomer(String customer){this.customer=customer;}

    public void setRepository(String repository){this.repository=repository;}

}
