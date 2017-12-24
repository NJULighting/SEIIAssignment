package nju.lighting.bl.customerbl;

import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.vo.CustomerVO;
import shared.CustomerChangeInfo;
import shared.CustomerType;
import shared.ResultMessage;

import java.util.List;

class CustomerController implements CustomerBLService {
    private CustomerManager manager = CustomerManager.INSTANCE;

    @Override
    public List<CustomerVO> getCustomerList() {
        return manager.getCustomerVOList();
    }

    @Override
    public ResultMessage createCustomer(CustomerVO vo) {
        return manager.createCustomer(vo);
    }

    @Override
    public ResultMessage changeReceivableLimit(int customerID, double newLimit) {
        return manager.changeReceivableLimit(customerID, newLimit);
    }

    @Override
    public List<CustomerVO> search(String keyword) {
        return null;
    }

    @Override
    public List<CustomerVO> searchInType(String keyword, CustomerType type) {
        return null; //todo complete this method
    }

    @Override
    public ResultMessage deleteCustomer(int customerID) {
        return manager.delete(customerID);
    }

    @Override
    public ResultMessage changeCustomer(CustomerChangeInfo changeInfo) {
        return manager.changeCustomer(changeInfo);
    }

    @Override
    public CustomerVO findCustomerByID(int id) {
        return manager.findCustomerByID(id);
    }

    @Override
    public List<CustomerVO> findCustomerByType(CustomerType type) {
        return manager.findCustomerByType(type);
    }
}
