/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

/**
 *
 * @author Kushtrim Hajrizi
 */

import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/register/user")
    public ResponseEntity register(@RequestBody Useri user) {
        return userService.create(user);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    @PreAuthorize("#id == principal.getId()")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    @PreAuthorize("#id == principal.getId()")
    public Useri get(@PathVariable int id) {
        return userService.get(id);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    @PreAuthorize("#id == principal.getId()")
    public ResponseEntity update(@RequestBody Useri user, @PathVariable int id) {
       return userService.update(user);
    }
}
