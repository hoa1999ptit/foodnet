/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

/**
 *
 * @author Kushtrim Hajrizi
 */
public interface AuthEntity {
    
    public static final int BUSINESS = 13;
    public static final int USER = 14;
    
    String getUsername();
    int getId();
    int getAuthenticationId();
    String getName();
    String getEmail();
    int getType();
}
