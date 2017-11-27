import nju.lighting.data.customerdata.CustomerData;
import nju.lighting.po.customer.CustomerPO;
import org.junit.Test;
import shared.CustomerGrade;
import shared.CustomerType;
import shared.ResultMessage;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/27.
 * Description: CustomerData模块测试代码
 * 测试通过 （只能用于第一次测试，之后主键会递增的）
 * @author iznauy
 */
public class CustomerDataTest {

    private CustomerData customerData = new CustomerData();

    @Test
    public void insertCustomer() throws Exception {
        CustomerPO po1 = new CustomerPO(CustomerType.SUPPLIER, CustomerGrade.THREE, "孙知良",
                "12322222222", "一栋626", "210046",
                null, 1000, 1000, 0, null);
        CustomerPO po2 = new CustomerPO(CustomerType.SUPPLIER, CustomerGrade.ONE, "宋抟",
                "899999", null, null, null, 100,
                50, 0, null);
        CustomerPO po3 = new CustomerPO(CustomerType.SALESPERSON, CustomerGrade.FIVE, "刘钦", "99999",
                null, null, null, 0, 0, 10000, null);
        ResultMessage resultMessage1 = customerData.insertCustomer(po1);
        assertEquals(ResultMessage.SUCCESS, resultMessage1);
        ResultMessage resultMessage2 = customerData.insertCustomer(po2);
        assertEquals(ResultMessage.SUCCESS, resultMessage2);
        ResultMessage resultMessage3 = customerData.insertCustomer(po3);
        assertEquals(ResultMessage.SUCCESS, resultMessage3);
    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerPO customerPO = customerData.getCustomerById(1);
        System.out.println(customerPO);
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerPO po = new CustomerPO(1, CustomerType.SUPPLIER, CustomerGrade.THREE, "孙知良",
                "12322222222", "一栋626", "210046",
                null, 10000, 1000, 0, null);
        ResultMessage resultMessage = customerData.updateCustomer(po);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void changeReceivable() throws Exception {
        ResultMessage resultMessage = customerData.changeReceivable(1, 200);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void changePayable() throws Exception {
        ResultMessage resultMessage = customerData.changePayable(3, 1000);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void changeReceivableLimit() throws Exception {
        ResultMessage resultMessage = customerData.changeReceivableLimit(1, 200);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void getAllCustomer() throws Exception {
        List<CustomerPO> customerPOS = customerData.getAllCustomer();
        for (CustomerPO customerPO: customerPOS) {
            System.out.println(customerPO);
        }
    }

    @Test
    public void deleteCustomer() throws Exception {
        ResultMessage resultMessage1 = customerData.deleteCustomer(1);
        assertEquals(ResultMessage.SUCCESS, resultMessage1);
        ResultMessage resultMessage2 = customerData.deleteCustomer(2);
        assertEquals(ResultMessage.SUCCESS, resultMessage2);
        ResultMessage resultMessage3 = customerData.deleteCustomer(3);
        assertEquals(ResultMessage.SUCCESS, resultMessage3);
    }
}
