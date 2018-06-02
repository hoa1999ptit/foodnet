/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class RaportRequest {
    
    public static final String ALL_USERS = "AllUsers";
    public static final String ALL_BUSINESSES = "AllBusinesses";
    public static final String ALL_POSTS = "AllPosts";
    public static final String ALL_FEEDBACKS = "AllFeedbacks";
    public static final String ALL_PRODUCTS = "AllProducts";
    
    private Request request;
    
    public RaportRequest(Request request) {
        if (request == null)
            request = new Request();
        this.request = request;
    }
    
    public void getRaport(String raportName, File saveAt) throws RequestException {
        try {
            HttpGet getReq = new HttpGet(request.buildURI(request.path("(raports)/{}", raportName), null));
            HttpResponse res = request.getClient().execute(getReq);
            if (res.getStatusLine().getStatusCode() != 200)
                throw new RequestException("Raport not found: " + raportName);
            
            OutputStream out = new FileOutputStream(saveAt);
            out.write(IOUtils.toByteArray(res.getEntity().getContent()));
            out.close();
            
        } catch (IOException ex) {
            System.out.println("RAPORT EXCEPTION: " + ex.getMessage());
        }
    }
    
}
