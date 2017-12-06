package nju.lighting.bl.customerbl;

import nju.lighting.bl.userbl.LoginHelper;
import nju.lighting.bl.utls.LoginTestHelper;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.CustomerChangeInfo;
import shared.CustomerGrade;
import shared.ResultMessage;

import javax.naming.NamingException;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/5.
 * Description:
 * @author Liao
 */
public class ChangeCustomerTest {
    private static final String NEW_NAME = "UltimateFrog";
    private static final String NEW_EMAIL = "UltimateFrog";
    private static final String NEW_SALESMAN = "UltimateLittleFrog";
    private static final CustomerGrade NEW_GRADE = CustomerGrade.THREE;
    private static final double NEW_LIMIT = 123456;
    private static final int CUSTOMER_ID = 10;
    private static final int INVALID_CUSTOMER_ID = 4;

    private CustomerBLService customerBLService;
    private CustomerDataService dataService;
    private CustomerChangeInfo.Builder builder;
    private CustomerPO oldData;

    public ChangeCustomerTest() throws NamingException, RemoteException {
        LoginTestHelper.loginAuthorizedUser();
        customerBLService = new CustomerController();
        dataService = DataFactory.getDataBase(CustomerDataService.class);
        oldData = dataService.getCustomerById(CUSTOMER_ID);
    }

    @Before
    public void setUp() throws Exception {
        builder = new CustomerChangeInfo.Builder(CUSTOMER_ID);
    }

    @Test
    public void changeNameTest() throws Exception {
        builder.changeName(NEW_NAME);
        ResultMessage res = customerBLService.changeCustomer(builder.build());

        assertEquals(ResultMessage.SUCCESS, res);
        CustomerPO newData = dataService.getCustomerById(CUSTOMER_ID);
        assertEquals(NEW_NAME, newData.getName());
    }

    @Test
    public void multiChangeTest() throws Exception {
        builder.changeName(NEW_NAME).changeEmail(NEW_EMAIL).changeGrade(NEW_GRADE).changeSalesMan(NEW_SALESMAN);
        ResultMessage res = customerBLService.changeCustomer(builder.build());

        assertEquals(ResultMessage.SUCCESS, res);
        CustomerPO newData = dataService.getCustomerById(CUSTOMER_ID);
        assertEquals(NEW_NAME, newData.getName());
        assertEquals(NEW_EMAIL, newData.getEmail());
        assertEquals(NEW_SALESMAN, newData.getSalesman());
        assertEquals(NEW_GRADE, newData.getGrade());
    }

    @Test
    public void receivableLimitChangeTest() throws Exception {
        ResultMessage res = customerBLService.changeReceivableLimit(CUSTOMER_ID, NEW_LIMIT);

        assertEquals(ResultMessage.SUCCESS, res);
        CustomerPO po = dataService.getCustomerById(CUSTOMER_ID);
        assertEquals(NEW_LIMIT, po.getReceivableLimit(), 0.1);
    }

    @Test
    public void receivableLimitChangeFailTest() throws Exception {
        LoginTestHelper.loginNotAuthorizedUser();
        ResultMessage res = customerBLService.changeReceivableLimit(CUSTOMER_ID, NEW_LIMIT);

        assertEquals(ResultMessage.FAILURE, res);
        CustomerPO po = dataService.getCustomerById(CUSTOMER_ID);
        assertNotEquals(NEW_LIMIT, po.getReceivableLimit(), 0.1);
    }

    @After
    public void tearDown() throws Exception {
        dataService.updateCustomer(oldData);
    }
}