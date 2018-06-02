/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.entities;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class ErrorMessage {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorMessage(String message) {
        this.message = message;
    }
    
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" + "status=" + status + ", message=" + message + ", path=" + path + '}';
    }
    
    
}
