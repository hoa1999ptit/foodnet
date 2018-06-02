/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.security;

import foodnet.foodnetserver.rest.security.model.AuthDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Component
//@Order(5)
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj instanceof AuthDetails) {
            AuthDetails auth = (AuthDetails) obj;
            String type = req.getHeader("foodnet-type");
            String identifier = req.getHeader("foodnet-identifier");

            
            System.out.println("THIS IS THE USER: " + auth.getUsername());
        }
        return true;
    }
    
    
    
    
}
