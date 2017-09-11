import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by moxuandeng on 2017/7/21 10:54.
 */
public class EncodeUtil {
//    private static final Logger logger = LoggerFactory.getLogger(EncodeUtil.class);

    public static String encode(String string) {
        if (string == null || string.equals("")) {
            return string;
        }
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
//            logger.warn("字符串[{}]encode错误", string, e);
        }
        return "";
    }

    public static String decode(String string) {
        if (string == null || string.equals("")) {
            return string;
        }
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
//            logger.warn("字符串[{}]decode错误", string, e);
        }
        return "";
    }
}
