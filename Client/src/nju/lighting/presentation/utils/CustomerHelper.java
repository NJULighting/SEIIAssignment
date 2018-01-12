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
