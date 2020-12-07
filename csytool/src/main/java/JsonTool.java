//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//
///**
// * Description:
// *
// * @author csy
// * @version 1.0.0
// * @since 2020/10/24
// */
//public class JsonTool {
//    // private final String format = "%Y";
//    public static final ObjectMapper mapper = new ObjectMapper();
//
//    public JsonTool() {
//        //使用ObjectMapper来序列化和反序列化
////        mapper = new ObjectMapper();
////        // 美化输出
////        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        // // 允许序列化空的POJO类
//        // // （否则会抛出异常）
//        // mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//        // // 把java.util.Date, Calendar输出为数字（时间戳）
//        // mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        //
//        // // 在遇到未知属性的时候不抛出异常
//        // mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        // // 强制JSON 空字符串("")转换为null对象值:
//        // mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//        //
//        // // 在JSON中允许C/C++ 样式的注释(非标准，默认禁用)
//        // mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
//        // // 允许没有引号的字段名（非标准）
//        // mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        // // 允许单引号（非标准）
//        // mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        // // 强制转义非ASCII字符
//        // mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
//        // // 将内容包裹为一个JSON属性，属性名由@JsonRootName注解指定
//        // mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
//    }
//
//
//    // java obj -> string
//    public static String dumps(Object value) {
//        try {
//            return mapper.writeValueAsString(value);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // string -> java obj
//    public static  <T> T loads(final String data, Class<T> valueType) {
//
//        try {
//            return mapper.readValue(data, valueType);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // string -> java map
//    public static  <T> T loads(final String data, TypeReference<T> valueTypeRef) {
//
//        try {
//            return mapper.readValue(data, valueTypeRef);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
