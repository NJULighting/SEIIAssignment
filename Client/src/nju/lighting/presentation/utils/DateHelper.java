package nju.lighting.presentation.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created on 2017/12/5.
 * Description
 *
 * @author 陈俊宇
 */
public class DateHelper {

    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);

    }

    public static String approximateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date).toString();
    }

    public static String accurateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date).toString();
    }

    public static Date WeekAgo() {
        return findDateByDay(-7);
    }

    public static Date halfMonthAgo() {
        return findDateByDay(-15);
    }

    public static Date MonthAgo() {
        return findDateByMonth(-1);
    }

    public static Date threeMonthsAgo() {
        return findDateByMonth(-3);
    }

    public static Date halfYearAgo() {
        return findDateByMonth(-6);
    }

    public static Date yearAgo() {
        return findDateByMonth(-12);
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
