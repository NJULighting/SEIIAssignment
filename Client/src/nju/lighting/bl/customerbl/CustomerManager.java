package nju.lighting.bl.customerbl;


import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import nju.lighting.vo.CustomerVO;
import shared.CustomerChangeInfo;
import shared.CustomerType;
import shared.OPType;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create on 11/12
 * Description:
 * @author 高梦婷
 */
public enum CustomerManager {
    INSTANCE;

    private CustomerDataService dataService;
    private Logger logger;

    CustomerManager() {
        try {
            dataService = DataFactory.getDataBase(CustomerDataService.class);
            logger = new UserLogger();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change user's attributes
     * @param changeInfo <code>CustomerChangeInfo</code> object contains change information for the customer
     * @return <code>ResultMessage.SUCCESS</code> if change successfully<br>
     * <code>ResultMessage.NETWORK</code> if network fails
     */
    ResultMessage changeCustomer(CustomerChangeInfo changeInfo) {
        try {
            Customer target = new Customer(changeInfo.id);
            target.changeInfo(changeInfo);
            logger.add(OPType.MODIFY, "修改客户信息 " + changeInfo.toString());
            return ResultMessage.SUCCESS;
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * Delete a customer
     * @param customerID id of customer
     * @return <code>SUCCESS</code> if network works well<br>
     * <code>NETWORK_FAIL</code> otherwise
     */
    ResultMessage delete(int customerID) {
        try {
            logger.add(OPType.DELETE, "删除客户 " + customerID);
            return dataService.deleteCustomer(customerID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * Get all customers
     * @return a vo list contains all customers
     */
    List<CustomerVO> getCustomerVOList() {
        try {
            List<CustomerPO> poList = dataService.getAllCustomer();
            return poList.stream().map(Customer::new).map(Customer::toVO).collect(Collectors.toList());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Create a new customer, id should be ignored 'cause the database will generate a id for it automatically
     * @param vo vo contains all information except id of a customer
     * @return <code>SUCCESS</code> if add successfully<br>
     *     <code>FAILURE</code> if there's an exception in database
     *     <code>NETWORK_FAIL</code> if network fails
     */
    ResultMessage createCustomer(CustomerVO vo) {
        Customer customer = new Customer(vo);
        try {
            ResultMessage res =  dataService.insertCustomer(customer.toPO());
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.ADD, "添加新客户" + vo.getName());
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * Get a customer with this id
     * @param id type of target customers
     * @return customer with the id you passed <br>
     *     or <code>null</code> if network fails
     */
    CustomerVO findCustomerByID(int id) {
        try {
            Customer customer = new Customer(id);
            return customer.toVO();
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get vo list of customers by type
     * @param type type of target customers
     * @return customers with the type you passed <br>
     *     or <code>null</code> if network fails
     */
    List<CustomerVO> findCustomerByType(CustomerType type) {
        try {
            List<CustomerPO> poList = dataService.getAllCustomer();
            return poList.stream().filter(po -> po.getType() == type)
                    .map(po -> new Customer(po).toVO()).collect(Collectors.toList());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Change a customer's upper limit of receivables
     * @param id    id of the customer
     * @param limit new receivable limit of this customer
     * @return <code>SUCCESS</code> if change successfully<br>
     * <code>FAILURE</code> if id matches no customer or updating fails
     * <code>NETWORK_FAIL</code> if network fails
     */
    ResultMessage changeReceivableLimit(int id, double limit) {
        // Check authority
        UserInfo userInfo = new UserInfoImpl();
        if (!userInfo.authorized())
            return ResultMessage.FAILURE;

        // Execute changing
        try {
            ResultMessage res =  dataService.changeReceivableLimit(id, limit);
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.MODIFY, "修改客户 " + id + " 应收额度为 " + limit);
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }
}
