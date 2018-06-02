/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.rest.entities.CompactBusiness;
import foodnet.foodnetserver.rest.services.BiznesiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kushtrim Hajrizi
 */
@RestController
public class BiznesiController {
    
    public static final String LIST_TYPE_ALL = "all";
    public static final String LIST_TYPE_MINE = "mine";
   
    @Autowired
    private BiznesiService biznesiService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/register/business")
    public ResponseEntity register(@RequestBody Biznesi b) {
        return biznesiService.create(b);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/businesses/{id}")
    @PreAuthorize("#id == principal.getId()")
    public void delete(@PathVariable int id) {
        biznesiService.delete(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/businesses/{id}")
    public Biznesi get(@PathVariable int id) {
        return biznesiService.get(id);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/businesses/{id}")
    @PreAuthorize("#id == principal.getId()")
    public ResponseEntity update(@RequestBody Biznesi b, @PathVariable int id) {
        return biznesiService.update(b);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/businesses")
    public List<Biznesi> getAll() {
        return biznesiService.getAll();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/businesses/compact")
    public List<CompactBusiness> getAllCompact() {
        return CompactBusiness.fromBusinessesList(biznesiService.getAll());
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/businesses/users/{userId}/favorites")
    @PreAuthorize("#userId == principal.getId() && hasAuthority('USER')")
    public List<CompactBusiness> getUserFavoritesCompact(@PathVariable int userId) {
        return CompactBusiness.fromBusinessesList(biznesiService.getUserFavorites(userId));
    }
}
