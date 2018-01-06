package nju.lighting.blservice.customerblservice;

import nju.lighting.vo.CustomerVO;
import shared.*;

import java.util.List;

public interface CustomerBLService {

    /**
     * Get all customers
     * @return a vo list contains all customers
     */
    List<CustomerVO> getCustomerList();

    /**
     * Create a new customer, id should be ignored 'cause the database will generate a id for it automatically
     * @param vo vo contains all information except id of a customer
     * @return <code>SUCCESS</code> if add successfully<br>
     * <code>FAILURE</code> if there's an exception in database
     * <code>NETWORK_FAIL</code> if network fails
     */
    Result<CustomerVO> createCustomer(CustomerVO vo);

    /**
     * Change a customer's upper limit of receivables
     * @param customerID id of the customer
     * @param newLimit   new receivable limit of this customer
     * @return <code>SUCCESS</code> if change successfully<br>
     * <code>FAILURE</code> if id matches no customer or updating fails
     * <code>NETWORK_FAIL</code> if network fails
     */
    ResultMessage changeReceivableLimit(int customerID, double newLimit);

    List<CustomerVO> search(String keyword, CustomerType type);

    /**
     * Delete a customer
     * @param customerID id of customer
     * @return <code>SUCCESS</code> if network works well<br>
     * <code>NETWORK_FAIL</code> otherwise
     */
    ResultMessage deleteCustomer(int customerID);

    /**
     * Change user's attributes
     * @param changeInfo <code>CustomerChangeInfo</code> object contains change information for the customer
     * @return <code>ResultMessage.SUCCESS</code> if change successfully<br>
     * <code>ResultMessage.NETWORK</code> if network fails
     */
    ResultMessage changeCustomer(CustomerChangeInfo changeInfo);

    /**
     * Get a customer with this id
     * @param id type of target customers
     * @return customer with the id you passed <br>
     * or <code>null</code> if network fails
     */
    CustomerVO findCustomerByID(int id);

    /**
     * Get vo list of customers by type
     * @param type type of target customers
     * @return customers with the type you passed <br>
     * or <code>null</code> if network fails
     */
    List<CustomerVO> findCustomerByType(CustomerType type);
}
