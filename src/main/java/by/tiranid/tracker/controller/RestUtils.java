package by.tiranid.tracker.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class RestUtils {

    public static final String durationDefault = "00:25:00";

    public static Time convertStringToTime(String time) {
        if (time == null) {
            return new Time(new java.util.Date().getTime());
        } else if (time.contains(":")) {
            return Time.valueOf(time);
        }
        try {
            long t = Long.valueOf(time);
            return new Time(t);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Time convertStringToDurationTime(String time) {
        if (time == null) {
            return Time.valueOf(durationDefault);
        } else if (time.contains(":")) {
            return Time.valueOf(time);
        }
        return null;
    }

    public static Date convertStringToDate(String date) {
        if (date == null) {
            return new Date(new java.util.Date().getTime());
        }
        // yyyy-mm-dd
        else if (date.contains("-")) {
            return Date.valueOf(date);
        }
        try {
            long t = Long.valueOf(date);
            return new Date(t);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String changeNtoBr(String message) {
        return Pattern.compile("\n").matcher(message).replaceAll("<br/>");
    }

    /**
     * current local time (by user's PC time) converted to "hh:mm:ss" format
     *
     * @return current local time in "hh:mm:ss" format
     */
    public static String getCurrentTimeString() {
        LocalTime t = LocalTime.now();
        return t.getHour() + ":" + t.getMinute() + ":" + t.getSecond();
    }

}
