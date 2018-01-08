package nju.lighting.bl.customerbl;

import org.junit.Test;
import shared.ResultMessage;

import static org.junit.Assert.*;
/**
 * Created on 2018/1/8.
 * Description:
 * @author Liao
 */
public class CustomerInfoImplTest {
    private static final int TARGET_CUSTOMER = 1;
    private static final double MAKE_IT_EXCEEDING = 99999999;
    private static final double NORMAL_ADDITION = 10;

    @Test
    public void changeReceivableTest1() throws Exception {
        // Try to add customer's receivable until exceed the limit
        CustomerInfo customerInfo = new CustomerInfoImpl();
        ResultMessage res = customerInfo.changeReceivable(TARGET_CUSTOMER, MAKE_IT_EXCEEDING);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void changeReceivableTest2() throws Exception {
        // Adding a customer's receivable, which won't make it exceed the limit
        CustomerInfo customerInfo = new CustomerInfoImpl();
        ResultMessage res = customerInfo.changeReceivable(TARGET_CUSTOMER, NORMAL_ADDITION);

        assertEquals(ResultMessage.SUCCESS, res);

        // Tear down
        res = customerInfo.changeReceivable(TARGET_CUSTOMER, -NORMAL_ADDITION);
        assertEquals(ResultMessage.SUCCESS, res);
    }
}