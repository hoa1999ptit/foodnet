/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.Produkti;
import foodnet.foodnetserver.rest.services.ProduktiService;
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
 * @author Gazmend Haziri
 */
@RestController
@RequestMapping("/businesses/{businessId}/")
public class ProduktiController {
    
    @Autowired
    private ProduktiService produktiService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<Produkti> get(@PathVariable int businessId) {
        return produktiService.getAllByBusinessId(businessId);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/products")
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public void create(@RequestBody Produkti p, @PathVariable int businessId) {
        produktiService.create(p);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    public Produkti get(@PathVariable int businessId, @PathVariable int productId) {
        return produktiService.get(productId);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}")
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public void delete(@PathVariable int businessId, @PathVariable int productId) {
        produktiService.delete(productId);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/products/{productId}")
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public void update(@RequestBody Produkti p, @PathVariable int businessId, @PathVariable int productId) {
        produktiService.update(p, productId);
    }

//    
//    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
//    public void edit(@RequestBody Produkti p, @PathVariable int userId, @PathVariable int businessId, @PathVariable int id) {
//        produktiService.update(p);
//    }
//    
//    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
//    public void edit(@PathVariable int userId, @PathVariable int businessId, @PathVariable int id) {
//        produktiService.delete(id);
//    }

    
}
