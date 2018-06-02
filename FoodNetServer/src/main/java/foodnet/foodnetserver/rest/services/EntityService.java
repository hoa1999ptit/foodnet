/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Base class for all entity services.
 * @author Kushtrim Hajrizi
 */
@Service
public abstract class EntityService {
    
    /**
     * Used to serialize and deserialize data. 
     */
    protected ObjectMapper om;
    
    public EntityService() {
        om = new ObjectMapper();
        
        // Disable FAIL_ON_UNKNOWN_PROPERTIES so no exceptions
        // are thrown when data is send bundled
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    
    /**
     * Uses the data in the 'data' parameter to construct an object of
     * the given type T. (Nonmatching data is ignored)
     * @param <T>
     * @param data
     * @param cls
     * @return constructed object of given type
     */
    protected <T> T fromMap(Map<String, Object> data, Class<T> cls) {
        return om.convertValue(data, cls);
    }
    
}
