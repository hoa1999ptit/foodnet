/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.Porosia;
import foodnet.foodnetserver.rest.services.PorosiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/businesses/{businessId}")
public class PorosiaController {
    
    @Autowired
    private PorosiaService porosiaService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/orders")
    @PreAuthorize("#userId == principal.getId() && hasAuthority('USER')")
    public void create(
            @RequestBody Porosia p, @PathVariable int businessId, @PathVariable int userId) {
        porosiaService.create(p, businessId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public List<Porosia> get(@PathVariable int businessId) {
        return porosiaService.getByBusinessId(businessId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/orders/{orderId}")
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public Porosia get(@PathVariable int businessId, @PathVariable int orderId) {
        return porosiaService.get(orderId);
    }
    
    
}
