package nju.lighting.bl.customerbl;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import nju.lighting.vo.CustomerVO;
import shared.CustomerChangeInfo;
import shared.CustomerGrade;
import shared.CustomerType;

import javax.naming.NamingException;
import java.rmi.RemoteException;

public class Customer {
    // TODO: 2017/12/6 modify access of this class
    private final int ID;
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

    Customer(CustomerVO vo) {
        ID = vo.getID();
        type = vo.getType();
        grade = vo.getGrade();
        address = vo.getAddress();
        email = vo.getEmail();
        name = vo.getName();
        postage = vo.getPostage();
        telephone = vo.getTelephone();
        receivable = vo.getReceivable();
        receivableLimit = vo.getReceivableLimit();
        payable = vo.getPayable();
        salesman = vo.getSalesman();
    }

    Customer(CustomerPO po) {
        ID = po.getID();
        type = po.getType();
        grade = po.getGrade();
        address = po.getAddress();
        email = po.getEmail();
        name = po.getName();
        postage = po.getPostage();
        telephone = po.getTelephone();
        receivable = po.getReceivable();
        receivableLimit = po.getReceivableLimit();
        payable = po.getPayable();
        salesman = po.getSalesman();
    }

    void changeInfo(CustomerChangeInfo info) throws RemoteException, NamingException {
        type = info.type;
        grade = info.grade;
        address = info.address;
        email = info.email;
        name = info.name;
        telephone = info.telephone;
        salesman = info.salesman;
        postage = info.postage;
        writeToDatabase();
    }

    private void writeToDatabase() throws RemoteException, NamingException {
        CustomerDataService dataService = DataFactory.getDataBase(CustomerDataService.class);
        dataService.updateCustomer(toPO());
    }

    CustomerPO toPO() {
        return new CustomerPO(ID, type, grade, name, telephone, address, postage, email, receivableLimit, receivable, payable, salesman);
    }

    CustomerVO toVO() {
        return new CustomerVO(ID, type, grade, name, telephone, address, postage, email, receivableLimit, receivable, payable, salesman);
    }

    void setReceivableLimit(double receivableLimit) throws RemoteException, NamingException {
        this.receivableLimit = receivableLimit;
        writeToDatabase();
    }

    public int getID() {
        return ID;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public CustomerGrade getGrade() {
        return grade;
    }

    public void setGrade(CustomerGrade grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
