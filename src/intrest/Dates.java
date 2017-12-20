package intrest;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dates {
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final long MILLIS_PER_DAY = 24L*60L*60L*1000L;

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "([0-9]{4})-(1[0-2]|0[1-9])-([0-9]{2})");

    private final TimeZone tz;

    public Dates(TimeZone tz) {
        this.tz = tz;
    }

    public Date parseDate(String s) {
        Matcher matcher = DATE_PATTERN.matcher(s);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Unparseable date: \"" + s + "\"");
        }
        Calendar cal = Calendar.getInstance(tz);
        cal.set(Calendar.YEAR, Integer.parseInt(matcher.group(1)));
        cal.set(Calendar.MONTH, Integer.parseInt(matcher.group(2))-1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(matcher.group(3)));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public int yearOf(Date date) {
        Calendar cal = Calendar.getInstance(tz);
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance(tz);
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    public static int daysBetween(Date from, Date to) {
        return (int)((to.getTime()-from.getTime())/MILLIS_PER_DAY);
    }

    public static boolean isLeap(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else if (year % 400 != 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int daysInYear(int year) {
        return isLeap(year) ? 366 : 365;
    }
}
