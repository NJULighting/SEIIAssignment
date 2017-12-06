package nju.lighting.presentation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2017/12/5.
 * Description
 *
 * @author 陈俊宇
 */
public class DateFormat {
    public static String toString(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date).toString();
    }
}
