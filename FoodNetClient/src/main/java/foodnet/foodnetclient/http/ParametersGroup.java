/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.http;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class ParametersGroup {
    private List<NameValuePair> params;

    public ParametersGroup() {
        params = new ArrayList<>();
    }
    
    public ParametersGroup add(String key, String value) {
        params.add(new BasicNameValuePair(key, value));
        return this;
    }

    public List<NameValuePair> getAsList() {
        return params;
    }
}
