import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by Administrator on 2017/9/2.
 */
public class SignUtil {

    private static final String APPKEY = "appKey";
    private static final String APPSECRET = "appSecret";
    private static final String NONCE = "nonce";

    /**
     * 获取签名
     * @param paramMap 包含业务参数，和appKey,nonce,timestamp这3个公共参数
     * @param appSecret
     * @return
     */
    public static String getSign(Map<String, Object> paramMap, String appSecret) {
        String text = getUrlText(paramMap);
        text += "&appSecret=" + appSecret;
        return DigestUtils.md5Hex(text).toUpperCase();
    }

    public static String getSign(String parameterStr, String appSecret){
        parameterStr += "&appSecret=" + appSecret;
        return DigestUtils.md5Hex(parameterStr).toUpperCase();
    }

    private static String getUrlText(Map<String, Object> beanMap) {
        beanMap = getSortedMap(beanMap);
        StringBuilder builder = new StringBuilder();
        for (String key : beanMap.keySet()) {
            String value = beanMap.get(key).toString();
            builder.append(key);
            builder.append('=');
            builder.append(value);
            builder.append('&');
        }
        String text = builder.toString();
        return text.substring(0, text.length() - 1);
    }

    /**
     *  对普通map进行排序
     * @param paramMap
     * @return
     */
    private static Map<String, Object> getSortedMap(Map<String, Object> paramMap) {
        SortedMap<String, Object> map = new TreeMap<>();
        for (String key : paramMap.keySet()) {
            if (key != null && !APPSECRET.equals(key)) {
                Object value = paramMap.get(key);
                if (value != null) {
                    String valueStr = String.valueOf(value);
                    if (valueStr != null && !"".equals(valueStr)) {
                        map.put(key, value);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获取带sign的请求参数queryString
     * @param paramMap
     * @param appSecret
     * @param appkey
     * @return
     */
    public static String getRequestQueryStringWithSign(Map<String,Object> paramMap, String appSecret, String appkey){
        paramMap.put("appKey", appkey);
        paramMap.put("nonce", create_nonce_str());
        paramMap.put("timestamp", System.currentTimeMillis());
        String requestParameter = getUrlText(paramMap);
        return requestParameter + "&sign=" + getSign(requestParameter, appSecret);
    }

    private static String create_nonce_str()
    {
        return UUID.randomUUID().toString().substring(0, 32);
    }
}
