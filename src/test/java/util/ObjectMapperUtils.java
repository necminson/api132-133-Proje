package util;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    public static <T> T convertJsonToJava(String json,Class<T> tClass){

        try {
           return new ObjectMapper().readValue(json,tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
