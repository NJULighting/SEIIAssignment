package nju.lighting.presentation.utils;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import nju.lighting.presentation.customerui.CustomerSearchListController;
import nju.lighting.presentation.factory.CustomerBLServiceFactory;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.CustomerVO;
import shared.CustomerGrade;
import shared.CustomerType;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Created on 2017/12/22.
 * Description
 *
 * @author 陈俊宇
 */
public class CustomerHelper {

    static HashMap<CustomerGrade, String> gradeStringHashMap = new HashMap<>();
    static HashMap<String, CustomerGrade> stringGradeHashMap = new HashMap<>();
    static HashMap<String, CustomerType> stringTypeHashMap = new HashMap<>();
    static HashMap<CustomerType, String> typeStringHashMap = new HashMap<>();

    static {
        gradeStringHashMap.put(CustomerGrade.ONE, "一级");
        gradeStringHashMap.put(CustomerGrade.TWO, "二级");
        gradeStringHashMap.put(CustomerGrade.THREE, "三级");
        gradeStringHashMap.put(CustomerGrade.FOUR, "四级");
        gradeStringHashMap.put(CustomerGrade.FIVE, "五级(vip)");

        stringGradeHashMap.put("一级", CustomerGrade.ONE);
        stringGradeHashMap.put("二级", CustomerGrade.TWO);
        stringGradeHashMap.put("三级", CustomerGrade.THREE);
        stringGradeHashMap.put("四级", CustomerGrade.FOUR);
        stringGradeHashMap.put("五级", CustomerGrade.FIVE);
        stringGradeHashMap.put("五级(vip)", CustomerGrade.FIVE);

        stringTypeHashMap.put("销售商", CustomerType.SALESPERSON);
        stringTypeHashMap.put("供应商", CustomerType.SUPPLIER);

        typeStringHashMap.put(CustomerType.SALESPERSON, "销售商");
        typeStringHashMap.put(CustomerType.SUPPLIER, "供应商");

    }

    public static String gradeToString(CustomerGrade grade) {
        return gradeStringHashMap.get(grade);
    }

    public static CustomerGrade stringToGrade(String string) {
        return stringGradeHashMap.get(string);
    }

    public static CustomerType stringToType(String string) {
        return stringTypeHashMap.get(string);
    }

    public static String typeToString(CustomerType type) {
        return typeStringHashMap.get(type);
    }

    public static void setCustomer(Upper upper, SimpleObjectProperty<CustomerVO> customer, CustomerType type) {

        CustomerSearchListController controller = loadCustomerPicker(upper, customer);
        controller.setReadOnly(upper, customer, type);
        controller.search();

    }

    public static void setCustomer(Upper upper, SimpleObjectProperty<CustomerVO> customer) {

        CustomerSearchListController controller = loadCustomerPicker(upper, customer);
        controller.setReadOnly(upper, customer);
        controller.search();

    }

    private static CustomerSearchListController loadCustomerPicker(Upper upper, SimpleObjectProperty<CustomerVO> customer) {
        FXMLLoader loader = new FXMLLoader(CustomerHelper.class.getClassLoader().getResource("nju/lighting/presentation/customerui/CustomerSearchList.fxml"));
        try {
            upper.setChildren(loader.load(), ">选择客户");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (CustomerSearchListController) loader.getController();
    }

    public static CustomerVO getCustomer(int id) {
        return CustomerBLServiceFactory.getCustomerBLService().findCustomerByID(id);
    }

    public static CustomerVO getCustomer(String id) {
        if (id == null || id.length() == 0)
            return null;
        else
            return getCustomer(Integer.parseInt(id));
    }


    public static String getCustomerString(String id){
        System.out.println("id"+id);
        CustomerVO customerVO=null;
        try{
            customerVO = getCustomer(id);
        }catch (NoSuchElementException e){
            return "佚名";
        }

        if (customerVO!=null)
            return customerVO.getName();
        else
            return "";
    }
}
