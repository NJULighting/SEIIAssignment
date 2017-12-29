package nju.lighting.bl.customerbl;

import nju.lighting.vo.CustomerVO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/29.
 * Description:
 * @author Liao
 */
public class CustomerSearchingTest {

    private CustomerManager manager = CustomerManager.INSTANCE;

    @Test
    public void test0() throws Exception {
        testHelper("五级", 6);
    }

    @Test
    public void test1() throws Exception {
        testHelper("一级", 0);
    }

    @Test
    public void test2() throws Exception {
        testHelper("naive", 3);
    }

    @Test
    public void test3() throws Exception {
        testHelper("frog", 3);
    }

    @Test
    public void test4() throws Exception {
        testHelper("excited", 6);
    }

    private void testHelper(String keyword, int expectSize) {
        List<CustomerVO> voList = manager.search(keyword);

        voList.forEach(System.out::println);
        assertEquals(expectSize, voList.size());
    }
}