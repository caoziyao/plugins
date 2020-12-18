package com.zel.market.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonHelper {

    private static ObjectMapper toJSONMapper = new ObjectMapper();
    private static ObjectMapper fromJSONMapper = new ObjectMapper();

    static {
//        fromJSONMapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
//        toJSONMapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
//        fromJSONMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        fromJSONMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        fromJSONMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    public JacksonHelper() {
    }

    public static <T> T fromJSON(String json, Class<T> clazz) {
        ObjectMapper mapper = fromJSONMapper;

        try {
            return mapper.readValue(json, clazz);
        } catch (JsonParseException var4) {
            throw new RuntimeException(var4);
        } catch (JsonMappingException var5) {
            throw new RuntimeException(var5);
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        }
    }

    public static <T> T fromJSON(String json, TypeReference<T> type) {
        ObjectMapper mapper = fromJSONMapper;

        try {
            return mapper.readValue(json, type);
        } catch (JsonParseException var4) {
            throw new RuntimeException(var4);
        } catch (JsonMappingException var5) {
            throw new RuntimeException(var5);
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        }
    }

    public static void main(String[] args) {

    }
}
