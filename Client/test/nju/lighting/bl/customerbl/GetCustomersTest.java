package nju.lighting.bl.customerbl;

import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.vo.CustomerVO;
import org.junit.Test;
import shared.CustomerType;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/5.
 * Description:
 * @author Liao
 */
public class GetCustomersTest {

    private static final int CUSTOMER_NUMBER = 4;
    private static final int CUSTOMER_ID = 10;
    private static final int INVALID_CUSTOMER_ID = 5;
    private static final String CUSTOMER_NAME = "SuperFrog";

    private CustomerBLService blService = new CustomerController();

    @Test
    public void getCustomerVOListTest() throws Exception {
        List<CustomerVO> voList = blService.getCustomerList();

        assertEquals(CUSTOMER_NUMBER, voList.size());
        assertEquals(CUSTOMER_NAME,
                voList.stream().filter(vo -> vo.getName().equals(CUSTOMER_NAME)).findAny().map(CustomerVO::getName).orElse(""));
    }

    @Test
    public void getCustomerTest() throws Exception {
        CustomerVO vo = blService.findCustomerByID(CUSTOMER_ID);

        assertNotNull(vo);
        assertEquals(CUSTOMER_NAME, vo.getName());
    }

    @Test
    public void getCustomerFailTest() throws Exception {
        CustomerVO vo = blService.findCustomerByID(INVALID_CUSTOMER_ID);

        assertNull(vo);
    }

    @Test
    public void findCustomerByTypeTest() throws Exception {
        List<CustomerVO> voList = blService.findCustomerByType(CustomerType.SALESPERSON);

        assertEquals(2, voList.size());
    }
}