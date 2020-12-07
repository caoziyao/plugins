import lombok.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 时间类
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
public class DateTool {

//    private static final long second = 1;
//    private static final long seconds = 30;
//    private static final long minute = 60;


    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String strFormatWithDate(@NonNull Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT);
        return formatter.format(date);
    }

    public static String strFormatWithDate(@NonNull Date date, @NonNull String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
