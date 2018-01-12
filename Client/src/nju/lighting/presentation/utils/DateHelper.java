package nju.lighting.presentation.utils;

import com.jfoenix.controls.JFXDatePicker;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created on 2017/12/5.
 * Description
 * @author 陈俊宇
 */
public class DateHelper {

    /**
     * 查表是在给定的时间区间内查询，所以时间区间需要有默认值
     * @param datePicker 要设置的datePicker
     * @param date       默认时间
     */
    public static void setDefaultTime(JFXDatePicker datePicker, LocalDate date) {
        datePicker.setValue(date);
        datePicker.valueProperty().addListener(c -> {
            if (datePicker.getValue() == null)
                datePicker.setValue(date);
        });
    }



    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);

    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String approximateTime(Date date) {
        if (date==null)
            return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date).toString();
    }

    public static String accurateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date).toString();
    }

    public static LocalDate weekAgo() {
        return dateToLocalDate(findDateByDay(-7));
    }

    public static LocalDate halfMonthAgo() {
        return dateToLocalDate(findDateByDay(-15));
    }

    public static LocalDate monthAgo() {
        return dateToLocalDate(findDateByMonth(-1));
    }

    public static LocalDate threeMonthsAgo() {
        return dateToLocalDate(findDateByMonth(-3));
    }

    public static LocalDate halfYearAgo() {
        return dateToLocalDate(findDateByMonth(-6));
    }

    public static LocalDate yearAgo() {
        return dateToLocalDate(findDateByMonth(-12));
    }

    private static Date findDateByDay(int delay) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(Calendar.DATE, delay);
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    private static Date findDateByMonth(int delay) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(Calendar.MONTH, delay);
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


}
