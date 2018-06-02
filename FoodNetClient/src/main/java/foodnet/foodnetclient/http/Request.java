/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.http;

import foodnet.foodnetclient.entities.AuthEntity;
import foodnet.foodnetclient.entities.Biznesi;
import foodnet.foodnetclient.entities.Entity;
import foodnet.foodnetclient.entities.ErrorMessage;
import foodnet.foodnetclient.entities.Useri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

/**
 * Contains methods for making HTTP requests.
 * @author Kushtrim Hajrizi
 */
public class Request {
    
    /**
     * Contains all the names used in the path of urls.
     */
    private final static Map<String, String> pathNames;
    
    public static final int USER_LOGIN = 0;
    public static final int BUSINESS_LOGIN = 1;
    
    static {
        pathNames = new HashMap<>();
        pathNames.put("(users)", "users");
        pathNames.put("(businesses)", "businesses");
        pathNames.put("(posts)", "posts");
        pathNames.put("(products)", "products");
        pathNames.put("(orders)", "orders");
        pathNames.put("(favorites)", "favorites");
        pathNames.put("(likes)", "likes");
        pathNames.put("(feedback)", "feedback");
        pathNames.put("(compact)", "compact");
        pathNames.put("(search)", "search");
        pathNames.put("(raports)", "raports");
        pathNames.put("(comments)", "comments");
    }
            
    /**
     * Information about the server
     */
    private static final String BASE_URL = "localhost";
    private static final int PORT = 8888;
    
    /**
     * HttpClient instance used to execute requests
     */
    private final CloseableHttpClient client;
    
    private Header authorizationHeader;
    
    public Request() {
        client = HttpClients.createDefault();
    }
        
    protected CloseableHttpClient getClient() {
        return client;
    }
    
    protected Header getAuthHeader() {
        return authorizationHeader;
    }
    
    public static String path(String path, Object... pathValues) {
        StringBuilder finalUrl = new StringBuilder();
        int pathValIndex = 0;
        
        String[] splittedPath = path.split("/");
        for (String pathPart: splittedPath) {
            if (pathPart.matches("\\(.*\\)"))
                finalUrl.append(pathNames.getOrDefault(pathPart, "")).append("/");
            else if (pathPart.matches("\\{.*\\}"))
                try {
                    finalUrl.append(pathValues[pathValIndex++]).append("/");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return finalUrl.toString();
                }
        }
        
        System.out.println("[DEBUGGING] The final url is: " + finalUrl.toString());
        
        return finalUrl.toString();
    }
    
    /**
     * Builds an URI using the server information and
     * the path values provided as arguments
     * @param pathValues
     * @return constructed URI
     */
    protected URI buildURI(String path, ParametersGroup params) {
        List<NameValuePair> paramsList = null;
        if (params != null)
            paramsList = params.getAsList();
        
        try {
            URIBuilder ub = new URIBuilder()
                    .setScheme("http")
                    .setHost(BASE_URL)
                    .setPort(PORT)
                    .setPath(path);
            if (paramsList != null)
                ub.addParameters(paramsList);
            return ub.build();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            // Logger here
        }
        
        return null;
    }
    
    private URI buildURI(String path) {
        return buildURI(path, null);
    }

    /**
     * Reads the content from an HttpEntity and returns it as a String.
     * @param entity
     * @return 
     */
    private String readEntity(HttpEntity entity) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(entity.getContent()))) {
            
            content += reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content;
    }
    
    public void setAuthorizationParameters(String username, String password) {
        String value = username + ":" + password;
        authorizationHeader = new BasicHeader(
                HttpHeaders.AUTHORIZATION,
                "Basic " + Base64.getEncoder().encodeToString(value.getBytes()));
    }
    
    ////////////////////////////////
    // Login and register methods //
    ////////////////////////////////
    
    public AuthEntity login(String username, String password, int loginType) throws RequestException {
        String loginPath = loginType == BUSINESS_LOGIN ? "business" : "user";
        setAuthorizationParameters(username, password);
        
        String content = authorizedRequest(new HttpPost(buildURI("login/" + loginPath)));
        
        if (loginType == BUSINESS_LOGIN)
            return JsonUtils.deserialize(content, Biznesi.class);
        else
            return JsonUtils.deserialize(content, Useri.class);
    }
    
    public void register(Useri user) throws RequestException {
        try {
            register(new StringEntity(JsonUtils.serialize(user)), "user");
        } catch (UnsupportedEncodingException ex) {
            throw new RequestException(ex);
        }
    }
    
    public void register(Biznesi biz) throws RequestException {
        try {
            register(new StringEntity(JsonUtils.serialize(biz)), "business");
        } catch (UnsupportedEncodingException ex) {
            throw new RequestException(ex);
        }
    }
    
    private void register(StringEntity en, String entityPath) throws RequestException {
        HttpPost postReq = new HttpPost(buildURI("register/" + entityPath));
        postReq.setEntity(en);
        request(postReq);
    }
    
    
                
    //////////////////////////
    // Http Request Methods //
    //////////////////////////
    
    /**
     * Retrieves and entity from the database.
     * Sends a GET request to the URL that is build based on
     * the given entity class and id.
     */
    public <T extends Entity> T get(Class<T> cls, String path, ParametersGroup params) 
            throws RequestException {
        String jsonResponse = get(buildURI(path, params));
        return JsonUtils.deserialize(jsonResponse, cls);
    }
    
    /**
     * Retrieves all entities from the database.
     * Sends a GET request to the URL that is build based
     * on the given entity.
     */
    public <T extends Entity> List<T> getAll(Class<T> cls, String path, ParametersGroup params)
            throws RequestException {
        return JsonUtils.deserializeList(get(buildURI(path, params)), cls);
    }
    
    /**
     * Deletes an entity from the database.
     * Sends a GET request to the URL that is build based
     * on the given entity.
     */
    public <T extends Entity> void delete(Class<T> cls, String path)
            throws RequestException {
        delete(buildURI(path));
    }
    
    /**
     * Sends a POST request to the given URL, with the 'data' variables
     * as the request body.
     */
    public <T extends Entity> void create(Class<T> cls, T entity, String path)
            throws RequestException {
        try {
            StringEntity httpEntity = new StringEntity(JsonUtils.serialize(entity));
            System.out.println("[DEBUGGING] Created entity: " + readEntity(httpEntity));
            create(httpEntity, buildURI(path));
        } catch (UnsupportedEncodingException ex) {
            throw new RequestException(ex);
        }
    }    
    
    /**
     * Sends a PUT request to the given URL, with the 'data' variables
     * as the request body.
     */
    public <T extends Entity> void update(Class<T> cls, T entity, String path)
            throws RequestException {
        try {
            StringEntity httpEntity = new StringEntity(JsonUtils.serialize(entity));
            update(httpEntity, buildURI(path));
        } catch (UnsupportedEncodingException ex) {
            throw new RequestException(ex);
        }
    }
    
    ///////////////////////////////
    // Http Base Request Methods //
    ///////////////////////////////
        
    private String get(URI uri) throws RequestException {
        return authorizedRequest(new HttpGet(uri));
    }
    
    private void delete(URI uri) throws RequestException {
        authorizedRequest(new HttpDelete(uri));
    }
    
    private void create(HttpEntity requestBody, URI uri) throws RequestException {
        HttpPost postReq = new HttpPost(uri);
        postReq.setEntity(requestBody);
        authorizedRequest(postReq);
    }
    
    private void update(HttpEntity requestBody, URI uri) throws RequestException {
        HttpPut putReq = new HttpPut(uri);
        putReq.setEntity(requestBody);
        authorizedRequest(putReq);
    }
    
    private String authorizedRequest(HttpUriRequest req) throws RequestException {
        req.addHeader(authorizationHeader);
        return request(req);
    }
    
    private String request(HttpUriRequest req) throws RequestException {
        req.addHeader("Content-Type", "application/json");
        try {
            HttpResponse response = client.execute(req);
            String content = readEntity(response.getEntity());

            if (response.getStatusLine().getStatusCode() != 200)
               throw new RequestException(
                       JsonUtils.deserialize(content, ErrorMessage.class).getMessage());
            
            return content;
        } catch (IOException ex) {
            // Logger here
            throw new RequestException(ex);
        }
    }
    
}