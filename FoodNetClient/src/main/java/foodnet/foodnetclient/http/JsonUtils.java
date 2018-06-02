/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.http;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class JsonUtils {
        /**
     * Used to serialize and deserialize data.
     */
    private static ObjectMapper om;
    
    static {
        om = new ObjectMapper();
        //om.setSerializationInclusion(Include.NON_NULL);
    }
    
    /**
     * Convert the key-value pairs in the 'data' object into JSON.
     * 
     * 
     */
    public static <T> String serialize(T entity) {
        try {
            return om.writeValueAsString(entity);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Deserialize JSON content to the given type T.
     * 
     * 
     * 
     * 
     */
    public static <T> T deserialize(String content, Class<T> cls) {
        try {
            return om.readValue(content, cls);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Deserialize JSON content to a list that contains elements
     * of the given type T.
     * 
     * 
     * 
     * 
     */
    public static <T> List<T> deserializeList(String content, Class<T> cls) {
        CollectionType ct = om.getTypeFactory().constructCollectionType(ArrayList.class, cls);
        try {
            return om.readValue(content, ct); 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
}
