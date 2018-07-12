package vn.tima.timainspection.Util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by hoangngoc on 8/17/16.
 */

public class DateTimeHelper {
    public static final String FORMAT_DATE_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String FORMAT_DATE2 = "EEEE, dd/MM/yyyy";
    public static final String FORMAT_DATE3 = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATE4 = "HH:mm, dd/MM/yyyy";

    public static String changeDateFormat(String strDate) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(FORMAT_DATE_SERVER);
        DateFormat ndf = new SimpleDateFormat(FORMAT_DATE);
        df.setTimeZone(tz);
        ndf.setTimeZone(tz);

        try {
            Date date = df.parse(strDate);
            Log.i("TEST_DATE", date.toString());
            return ndf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String changeDateTimeFormat2(String strDate) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(FORMAT_DATE_SERVER);
        DateFormat ndf = new SimpleDateFormat(FORMAT_DATE4);
        df.setTimeZone(tz);
        ndf.setTimeZone(tz);

        try {
            Date date = df.parse(strDate);
            Log.i("TEST_DATE", date.toString());
            return ndf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Date parserDate(String str) {
        DateFormat df = new SimpleDateFormat(FORMAT_DATE_SERVER);
        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parserDate2(String str) {
        DateFormat df = new SimpleDateFormat(FORMAT_DATE);
        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
        return date_format.format(calendar.getTime());
    }

    public static long convertDateToLong(String date) {
        SimpleDateFormat f = new SimpleDateFormat(FORMAT_DATE);
        long milliseconds = 0;
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    public static String changeDateToString(Date date) {
        DateFormat df = new SimpleDateFormat(FORMAT_DATE);
        return df.format(date);
    }

    public static String changeDateToString2(Date date) {
        DateFormat df = new SimpleDateFormat(FORMAT_DATE2);
        return df.format(date);
    }

    public static String changeDateToString3(Date date) {
        DateFormat df = new SimpleDateFormat(FORMAT_DATE3);
        return df.format(date);
    }

    public static String nextDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        return sdf.format(c.getTime());
    }

    public static String prevDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, -1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        return sdf.format(c.getTime());
    }

    public static int getMonthBaby(String bithday) {
        Calendar calendar = Calendar.getInstance();
        Date date = parserDate2(bithday);
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.setTime(date);
        int diffYear = calendar.get(Calendar.YEAR) - mCalendar.get(Calendar.YEAR);
        return diffYear * 12 + calendar.get(Calendar.MONTH) - mCalendar.get(Calendar.MONTH);

    }

    public static String nextMonth(String currentMonth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(currentMonth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH, 1);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String preMonth(String currentMonth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(currentMonth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH, -1);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getCurrentDateTime() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }


}
