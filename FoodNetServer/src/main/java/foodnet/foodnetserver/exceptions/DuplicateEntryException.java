/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.exceptions;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class DuplicateEntryException extends RuntimeException {
    
    public DuplicateEntryException(String msg) {
        super("Duplicate value: " + msg);
    }
    
}
