/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.Favorite;
import foodnet.foodnetserver.rest.services.FavoriteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kushtrim Hajrizi
 */
@RestController
public class FavoriteController {
    
    @Autowired
    private FavoriteService favoriteService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/businesses/{businessId}/favorites")
    @PreAuthorize("#userId == principal.getId() && hasAuthority('USER')")
    public void create(@PathVariable int userId, @PathVariable int businessId) {
        favoriteService.create(userId, businessId);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/businesses/{businessId}/favorites")
    @PreAuthorize("#userId == principal.getId() && hasAuthority('USER')")
    public void delete(@PathVariable int userId, @PathVariable int businessId) {
        favoriteService.delete(userId, businessId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/businesses/{businessId}/favorites")
    @PreAuthorize("#userId == principal.getId() && hasAuthority('USER')")
    public Favorite get(@PathVariable int userId, @PathVariable int businessId) {
        return favoriteService.getFavorite(userId, businessId);
    }
}
