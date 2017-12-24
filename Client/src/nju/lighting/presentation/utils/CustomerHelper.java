package nju.lighting.presentation.utils;

import shared.CustomerGrade;
import shared.CustomerType;

import java.util.HashMap;

/**
 * Created on 2017/12/22.
 * Description
 *
 * @author 陈俊宇
 */
public class CustomerHelper {

    static HashMap<CustomerGrade,String > gradeStringHashMap =new HashMap<>();
    static HashMap<String,CustomerGrade> stringGradeHashMap =new HashMap<>();
    static HashMap<String, CustomerType> stringTypeHashMap =new HashMap<>();
    static HashMap<CustomerType,String> typeStringHashMap=new HashMap<>();
    static {
        gradeStringHashMap.put(CustomerGrade.ONE,"一级");
        gradeStringHashMap.put(CustomerGrade.TWO,"二级");
        gradeStringHashMap.put(CustomerGrade.THREE,"三级");
        gradeStringHashMap.put(CustomerGrade.FOUR,"四级");
        gradeStringHashMap.put(CustomerGrade.FIVE,"五级(vip)");

        stringGradeHashMap.put("一级",CustomerGrade.ONE);
        stringGradeHashMap.put("二级",CustomerGrade.TWO);
        stringGradeHashMap.put("三级",CustomerGrade.THREE);
        stringGradeHashMap.put("四级",CustomerGrade.FOUR);
        stringGradeHashMap.put("五级",CustomerGrade.FIVE);
        stringGradeHashMap.put("五级(vip)",CustomerGrade.FIVE);

        stringTypeHashMap.put("销售商",CustomerType.SALESPERSON);
        stringTypeHashMap.put("供应商",CustomerType.SUPPLIER);

        typeStringHashMap.put(CustomerType.SALESPERSON,"销售商");
        typeStringHashMap.put(CustomerType.SUPPLIER,"供应商");

    }

   public static String gradeToString(CustomerGrade grade){
        return gradeStringHashMap.get(grade);
    }
   public static CustomerGrade stringToGrade(String string){return stringGradeHashMap.get(string);}
   public static CustomerType stringToType(String string){return stringTypeHashMap.get(string);}
   public static String typeToString(CustomerType type){return typeStringHashMap.get(type);}
}
