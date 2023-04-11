package com.crush.security_code.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.io.InputStream;

/**
 * JSON 解析处理
 * @author cuberxp
 * @since 1.0.0
 * Create time 2020/2/16 12:34
 */
public class JSON {
    public static final String DEFAULT_FAIL = "parse fail";
    public static final ObjectMapper objectmapper = new ObjectMapper();
    public static final ObjectWriter objectWrite = objectmapper.writerWithDefaultPrettyPrinter();

    public static<T> T unmarshal(InputStream inputStream, Class<T> valType) throws IOException {
        return objectmapper.readValue(inputStream, valType);
    }

    public static<T> T unmarshal(String jsonString, Class<T> valType) throws Exception {
        try {
            return objectmapper.readValue(jsonString, valType);
        }
        catch (JsonParseException e)
        {
            throw new Exception(e);
        }
        catch (JsonMappingException e)
        {
            throw new Exception(e);
        }
        catch (IOException e) {
            throw new Exception(e);
        }
    }
}
