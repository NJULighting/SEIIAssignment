package nju.lighting.bl.customerbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.vo.CustomerVO;
import org.junit.Ignore;
import org.junit.Test;
import shared.CustomerGrade;
import shared.CustomerType;
import shared.ResultMessage;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/5.
 * Description:
 * @author Liao
 */
public class AddCustomerTest {

    private CustomerBLService customerBLService = new CustomerController();

    public AddCustomerTest() {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void addCustomerTest() throws Exception {
        CustomerVO customerVO = new CustomerVO(0, CustomerType.SALESPERSON, CustomerGrade.FIVE, "SuperFrog", "6666666"
        , "上海交通大学", "210046", "excited@frogmail.com", 666666, 0, 0, "LittleFrog");
        ResultMessage res = customerBLService.createCustomer(customerVO);

        assertEquals(ResultMessage.SUCCESS, res);
    }
}