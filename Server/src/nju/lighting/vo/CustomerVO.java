package nju.lighting.vo;

import nju.lighting.po.CustomerGrade;
import nju.lighting.po.CustomerType;

public class CustomerVO {

    private int ID;

    private CustomerType type;

    private CustomerGrade grade;

    private String name;

    private String telephone;

    private String address;

    private String postage;

    private String email;

    private double receivableLimit;

    private double receivable;

    private double payable;

    private String salesman;

    public CustomerVO(int i, CustomerType t, CustomerGrade g, String n, String te, String ad, String po, String em, double reL, double re, double pay, String sa){
        ID =i;
        type =t;
        grade=g;
        name=n;
        telephone=te;
        address=ad;
        postage=po;
        email=em;
        receivableLimit=reL;
        receivable=re;
        payable=pay;
        salesman =sa;
    }

    public int getID(){ return ID;}

    public CustomerType getType(){ return type;}

    public CustomerGrade getGrade(){ return grade;}

    public String getName(){ return name;}

    public String getTelephone(){ return telephone;}

    public String getAddress(){ return address;}

    public String getPostage(){ return postage;}

    public String getEmail() { return email;}

    public double getReceivableLimit(){ return receivableLimit;}

    public double getReceivable(){ return receivable;}

    public double getPayable(){ return payable;}

    public String getSalesman(){ return salesman;}


    public void setID(int id){ ID = id;}

    public void setType(CustomerType type){ this.type = type;}

    public void setGrade(CustomerGrade grade){ this.grade = grade;}

    public void setName(String name){ this.name = name;}

    public void setTelephone(String telephone){ this.telephone = telephone;}

    public void setAddress(String address){ this.address = address;}

    public void setPostage(String postage){ this.postage = postage;}

    public void setEmail(String email) { this.email = email;}

    public void setReceivableLimit(double receivableLimit){ this.receivableLimit = receivableLimit;}

    public void setReceivable(double receivable){this.receivable = receivable;}

    public void setPayable(double payable){ this.payable = payable;}

    public void setSalesman(String salesman){ this.salesman = salesman;}


}
