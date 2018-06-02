/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.exceptions;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Kushtrim Hajrizi
 */
//@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginException extends RuntimeException {
    
    public LoginException(String msg) {
        super(msg);
    }
}
