package shared;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;

/**
 * Created on 2017/12/5.
 * Description: 用法同 <code>UserChangeInfo</code>
 * @author Liao
 */
public class CustomerChangeInfo {
    public final int id;
    public final CustomerType type;
    public final CustomerGrade grade;
    public final String name;
    public final String telephone;
    public final String address;
    public final String postage;
    public final String email;
    public final String salesman;
    private final String description;

    private CustomerChangeInfo(Builder builder) {
        id = builder.id;
        type = builder.type;
        grade = builder.grade;
        name = builder.name;
        telephone = builder.telephone;
        address = builder.address;
        postage = builder.postage;
        email = builder.email;
        salesman = builder.salesman;
        description = builder.description;
    }

    @Override
    public String toString() {
        return description;
    }

    public static class Builder {
        private final int id;
        private CustomerType type;
        private CustomerGrade grade;
        private String name;
        private String telephone;
        private String address;
        private String postage;
        private String email;
        private String salesman;
        private String description;

        /**
         * @param customerID id of the customer
         * @throws RemoteException        if network failed
         * @throws NoSuchElementException If the id is invalid
         */
        public Builder(int customerID) throws RemoteException {
            id = customerID;
            description = "客户ID " + id + " ";
            try {
                CustomerDataService dataService = DataFactory.getDataBase(CustomerDataService.class);
                CustomerPO po = dataService.getCustomerById(customerID);
                if (po == null)
                    throw new NoSuchElementException("Invalid id of customer");

                type = po.getType();
                grade = po.getGrade();
                name = po.getName();
                telephone = po.getTelephone();
                address = po.getAddress();
                postage = po.getPostage();
                email = po.getEmail();
                salesman = po.getSalesman();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        public Builder changeType(CustomerType type) {
            if (this.type == type)
                return this;
            this.type = type;
            description += "修改客户类型为" + type + " ";
            return this;
        }

        public Builder changeGrade(CustomerGrade grade) {
            if (this.grade == grade)
                return this;
            this.grade = grade;
            description += "修改客户等级为" + grade + " ";
            return this;
        }

        public Builder changeName(String name) {
            if (this.name.equals(name))
                return this;

            this.name = name;
            description += "修改客户名称为" + name + " ";
            return this;
        }

        public Builder changeTelephone(String telephone) {
            if (this.telephone.equals(telephone))
                return this;

            this.telephone = telephone;
            description += "修改电话为" + telephone + " ";
            return this;
        }

        public Builder changeAddress(String address) {
            if (this.address.equals(address))
                return this;

            this.address = address;
            description += "修改地址为" + address + " ";
            return this;
        }

        public Builder changeEmail(String email) {
            if (this.email.equals(email))
                return this;

            this.email = email;
            description += "修改邮箱为" + email + " ";
            return this;
        }

        public Builder changePostage(String postage) {
            if (this.postage.equals(postage))
                return this;

            this.postage = postage;
            description += "修改邮编为" + postage + " ";
            return this;
        }

        public Builder changeSalesMan(String salesman) {
            if (this.salesman.equals(salesman))
                return this;

            this.salesman = salesman;
            description += "修改默认业务员为" + salesman + " ";
            return this;
        }

        public CustomerChangeInfo build() {
            return new CustomerChangeInfo(this);
        }
    }
}
