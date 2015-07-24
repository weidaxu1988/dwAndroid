package com.xuweida.daweilibrary;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Dawei on 2015/3/7.
 */
public class TextAndDateHelper {

    public static String getDDMMYYString(Date date) {
        return new SimpleDateFormat("dd.MM.yy").format(date);
    }

    public static String getddMMyyyyHHmmString(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
    }

    public static String getyyyyMMddHHmmString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }

    public static String getMonthAndDayPreferWeekDay(Date date) {
        final String formatWeekDay = "EEE";

        GregorianCalendar rightNow = new GregorianCalendar();
        GregorianCalendar pointDate = new GregorianCalendar();
        pointDate.setTime(date);
//
//Log.e("year", pointDate.get(Calendar.YEAR) + "");
//Log.e("month", pointDate.get(Calendar.MONTH)+"");
//Log.e("day", pointDate.get(Calendar.DAY_OF_MONTH)+"");

        for (int i = 0; i < 5; i++) {
            int dayNow = rightNow.get(Calendar.DAY_OF_YEAR);
            int dayPoint = pointDate.get(Calendar.DAY_OF_YEAR);

            if (rightNow.get(Calendar.YEAR) == pointDate.get(Calendar.YEAR)) {
                if (dayNow == dayPoint) {
                    if (i == 0)
                        return "Today";
//                    else if (i == 1)
//                        return "Yesterday";
                    else
                        return new SimpleDateFormat(formatWeekDay).format(date);
                }
            }
            pointDate.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }
        return getMonthAndDay(date);
    }

    public static String getMonthAndDay(Date date) {
        final String formatMonthDay = "MMM d";

        return new SimpleDateFormat(formatMonthDay).format(date);
    }

    public static Date getDayDateFromNow(int offset) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(GregorianCalendar.DAY_OF_YEAR, offset);
        return calendar.getTime();
    }

    public static String getMonthAndDayAndWeekDay(Date date) {
        final String formatWeekDay = "EEE";

        GregorianCalendar rightNow = new GregorianCalendar();
        GregorianCalendar pointDate = new GregorianCalendar();
        pointDate.setTime(date);

        if (rightNow.get(Calendar.YEAR) == pointDate.get(Calendar.YEAR)) {
            if (rightNow.get(Calendar.DAY_OF_YEAR) == pointDate.get(Calendar.DAY_OF_YEAR)) {
                return "Today, " + getMonthAndDay(date);
            } else {
                pointDate.add(GregorianCalendar.DAY_OF_YEAR, 1);
                if (rightNow.get(Calendar.DAY_OF_YEAR) == pointDate.get(Calendar.DAY_OF_YEAR)) {
                    return "Yesterday, " + getMonthAndDay(date);
                }
            }
        }
        return new SimpleDateFormat(formatWeekDay).format(date) + ", " + getMonthAndDay(date);
    }

    public static int[] getTimeDuration(long different) {

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        int days = elapsedDays >= 1 ? (int) elapsedDays : 0;
        int hours = elapsedHours >= 1 ? (int) elapsedHours : 0;
        int mins = elapsedMinutes >= 1 ? (int) elapsedMinutes : 0;
        int secs = (int) elapsedSeconds;

        return new int[]{days, hours, mins, secs};
    }

    public static int[] getDateDuration(Date s, Date e) {
        DateTime start = new DateTime(s);
        DateTime end = new DateTime(e);

        Period period = new Period(start, end);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        return new int[]{years, months, days};
    }
}
