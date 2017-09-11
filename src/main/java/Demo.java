import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * test
 * Created by Leven on 2017/9/11.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println(buildUrl());
    }


    public static String buildUrl(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("requestId", "0C709FC166254174A6E19D12F37E1A");
        paramMap.put("mobile", "15088605055");
        paramMap.put("name", EncodeUtil.encode("刘苏杰"));
        paramMap.put("amount","10.00");
        paramMap.put("identity", "332522199308229157");
        paramMap.put("bankAccount","6214835891748921");
        paramMap.put("dateTime", LocalDateTimeUtil.toSimpleString(LocalDateTime.now()));
        paramMap.put("remark", "124");
        String requestStr = SignUtil.getRequestQueryStringWithSign(paramMap, "2221a3ba2497674f4b1cddd9718cfe32", "c360376146f04a5dbf5d6988145f4bbc");
        return  "http://openapi-qa.gongmall.com" + "/api/withdraw/doWithdraw?" + requestStr;
    }
}
