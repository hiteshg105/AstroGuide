package com.astro.programguide.util;

import com.astro.programguide.data.models.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hitesh on 8/21/17.
 */

public class Utils {

    public static final String TAG = "TVGuide";

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static String getTimeStamp(boolean isPast, long time) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date currentDate;
        if (isPast)
            currentDate = new Date(System.currentTimeMillis() - time);
        else
            currentDate = new Date(System.currentTimeMillis() + time);

        String strDate = sdfDate.format(currentDate);
        return strDate;
    }

    public static boolean isEventInCurrentTime(Event event) throws ParseException {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        Date startDate = sdfDate.parse(event.getDisplayDateTime());

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");

        String showLength = event.getDisplayDuration();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        Date showTime = parser.parse(showLength);

        cal.add(Calendar.HOUR_OF_DAY, showTime.getHours());
        cal.add(Calendar.MINUTE, showTime.getMinutes());
        cal.add(Calendar.SECOND, showTime.getSeconds());

        Date endDate = cal.getTime();

        //Check current time in between start date and end date

        if (currentDate.after(startDate) && currentDate.before(endDate)) {
            return true;
        }
        return false;

    }

    public static String convertDateToTime(String dateString) throws ParseException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        Date date = sdfDate.parse(dateString);
        String timeString = "";
        String hourString = "";
        String minuteString = "";
        String dayString = "";
        if (date.getHours() > 12) {
            hourString = String.valueOf(date.getHours() - 12);
            dayString = "pm";

        } else {
            if (date.getHours() < 10)
                hourString = "0";
            hourString = hourString + String.valueOf(date.getHours());
            dayString = "am";
        }
        if (date.getMinutes() < 10) {
            minuteString = "0" + date.getMinutes();
        } else {
            minuteString = String.valueOf(date.getMinutes());

        }
        timeString = hourString + ":" + minuteString + " " + dayString;
        return timeString;
    }
}
