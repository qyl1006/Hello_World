package cn.wolfcode.wms.util;

import com.fasterxml.jackson.databind.ObjectMapper;

//把对象转JSON
public abstract class JSONUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJSONString(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
