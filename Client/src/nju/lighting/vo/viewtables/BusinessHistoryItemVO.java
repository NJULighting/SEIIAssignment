package nju.lighting.vo.viewtables;

import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class BusinessHistoryItemVO {

    private Date date;

    private DocType type;

    private DocVO docVO;

    private String salesman;

    private String customer;

    private String repository;

    public BusinessHistoryItemVO(Date date, DocType type, DocVO docVO, String customer, String salesman, String repository) {
        this.date = date;
        this.type = type;
        this.docVO = docVO;
        this.customer = customer;
        this.repository = repository;
        this.salesman = salesman;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DocType getType() {
        return type;
    }

    public void setType(DocType type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public DocVO getDocVO() {
        return docVO;
    }

    public void setDocVO(DocVO docVO) {
        this.docVO = docVO;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }
}
