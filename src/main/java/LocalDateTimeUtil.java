import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by moxuandeng on 2017/7/29 14:25.
 */
public class LocalDateTimeUtil {
    // 线程安全的
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    private static final DateTimeFormatter SIMPLE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static LocalDateTime string2LocalDateTime(String time) {
        if (time == null) {
            return null;
        }
        if (time.contains(".")) {
            time = time.split("\\.")[0];
        }
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER);
    }


    /**
     * 将DATE转为LocalDateTime
     *
     * @param date 日期
     */
    public static LocalDateTime trans2LocalDateTime(Date date)
    {
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(date.toInstant(), zone);
    }

    /**
     * 将DATE转为LocalDate
     *
     * @param date 日期
     */
    public static LocalDate trans2LocalDate(Date date)
    {
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(date.toInstant(), zone).toLocalDate();
    }

    /**
     * 将LocalDateTime 转为 Date
     */
    public static Date trans2Date(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(localDateTime.atZone(zone).toInstant());
    }

    public static void main(String[] args) {
        System.out.println(string2LocalDateTime("2017-07-29 14:35:23.11"));
    }

    /**
     * 将LocalDate转为yyyyMM格式
     * @param date 时间
     */
    public static String toMonthString(LocalDate date){
        return date.format(MONTH_FORMATTER);
    }

    /**
     * LocalDateTime 转为标准格式时间
     * @param date 时间
     */
    public static String toString(LocalDateTime date){
        return date.format(DATE_TIME_FORMATTER);
    }

    public static String toSimpleString(LocalDateTime date) {
        return date.format(SIMPLE_TIME_FORMATTER);
    }


}
