package nju.lighting.po.customer;

import shared.CustomerGrade;
import shared.CustomerType;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class CustomerPO {

    private String ID;

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

    @Override
    public String toString() {
        return "CustomerPO{" +
                "ID='" + ID + '\'' +
                ", type=" + type +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", postage='" + postage + '\'' +
                ", email='" + email + '\'' +
                ", receivableLimit=" + receivableLimit +
                ", receivable=" + receivable +
                ", payable=" + payable +
                ", salesman='" + salesman + '\'' +
                '}';
    }

    public CustomerPO () {

    }

    public CustomerPO(CustomerType type, CustomerGrade grade, String name, String telephone, String address, String postage, String email, double receivableLimit, double receivable, double payable, String salesman) {
        this.type = type;
        this.grade = grade;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.postage = postage;
        this.email = email;
        this.receivableLimit = receivableLimit;
        this.receivable = receivable;
        this.payable = payable;
        this.salesman = salesman;
    }

    public CustomerPO(String ID, CustomerType type, CustomerGrade grade, String name, String telephone, String address, String postage, String email, double receivableLimit, double receivable, double payable, String salesman) {
        this.ID = ID;
        this.type = type;
        this.grade = grade;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.postage = postage;
        this.email = email;
        this.receivableLimit = receivableLimit;
        this.receivable = receivable;
        this.payable = payable;
        this.salesman = salesman;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public String getID() {
        return ID;
    }

    public void setID(String id) {
        ID = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "CUSTOMER_TYPE", length = 10, nullable = false)
    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "CUSTOMER_GRADE", length = 10, nullable = false)
    public CustomerGrade getGrade() {
        return grade;
    }

    public void setGrade(CustomerGrade grade) {
        this.grade = grade;
    }

    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TELEPHONE", length = 20)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "ADDRESS", length = 20)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "POSTAGE", length = 20)
    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    @Column(name = "EMAIL", length = 40)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "RECEIVABLE_LIMIT", nullable = false)
    public double getReceivableLimit() {
        return receivableLimit;
    }

    public void setReceivableLimit(double receivableLimit) {
        this.receivableLimit = receivableLimit;
    }

    @Column(name = "RECEIVE_AMOUNT", nullable = false)
    public double getReceivable() {
        return receivable;
    }

    public void setReceivable(double receivable) {
        this.receivable = receivable;
    }

    @Column(name = "PAY_AMOUNT", nullable = false)
    public double getPayable() {
        return payable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }

    @Column(name = "SALES_MAN", nullable = false, length = 40)
    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }


}
