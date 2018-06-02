/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.http;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class RequestException extends Exception {
    
    private int errCode;
    
    public RequestException(Exception ex) {
        super(ex);
    }
    
    public RequestException(String msg) {
        super(msg);
    }
    
    public RequestException(String msg, Exception ex) {
        super(msg, ex);
    }
    
    public int getErrorCode() {
        return errCode;
    }
}
