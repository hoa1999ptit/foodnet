/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.rest.security.model.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Kushtrim Hajrizi
 */
abstract class BaseController {
    
    public static int getPrincipalId() {
        return ((AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
    
    public static int getPrincipalAuthId() {
        return ((AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthId();
    }
}
