package neal.spark.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static boolean isInSevenDays(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //设置时间格式
            Date d = sdf.parse(date);
            Date now = new Date();
            now = sdf.parse(sdf.format(now));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, -7);
            Date before = calendar.getTime();
            //System.out.println(sdf.format(before));
            //System.out.println(sdf.format(d));
            return !d.before(before) || d.equals(before);


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("is in seven days error, date is: {}", date);
            return true;
        }
    }

    public static String getToday() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);

    }

    public static String getYesterday() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(calendar.getTime());

    }

    public static String getThreeMonthAgo(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        try {
            Date dNow = sdf.parse(dateString);
            Date dBefore;
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(dNow);//把当前时间赋给日历
            calendar.add(Calendar.MONTH, -3);  //设置为前3月
            dBefore = calendar.getTime();   //得到前3月的时间
            return sdf.format(dBefore);    //格式化前3月的时间

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("get date three month before error, date is:{}", dateString);
            return dateString;
        }
    }

    // [start, end] 区间
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        // tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(end);
        return result;
    }

    // [startDate, endDate]区间
    public static List<String> getStrBetweenDates(String startDate, String endDate) {
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        try {
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(sdf.parse(startDate));
            // tempStart.add(Calendar.DAY_OF_YEAR, 1);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(sdf.parse(endDate));
            while (tempStart.before(tempEnd)) {
                result.add(sdf.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
            result.add(endDate);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("get between dates error, date is:{}-{}", startDate, endDate);
            return null;
        }

    }

    public static long dateToStartTime(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            String time = dateStr + " 00:00:00";
            Date date = format.parse(time);
            return date.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("date to start time error, date is:{}", dateStr);
            return 0;
        }

    }

    public static long dateToEndTime(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            String time = dateStr + " 23:59:59";
            Date date = format.parse(time);
            return date.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("date to end time error, date is:{}", dateStr);
            return 0;
        }

    }

    public static long dateToTimeStamp(String dateStr) {

        try {
            dateStr = dateStr.split("\\.")[0];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = dateStr;
            Date date = format.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("date to start time error, date is:{}", dateStr);
            return 0;
        }

    }

    public static String getHourAgoDate(int hourAgo) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -hourAgo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(calendar.getTime());

    }

    public static int getHourAgoHour(int hourAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -hourAgo);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
