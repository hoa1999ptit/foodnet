/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.BusinessFeedback;
import foodnet.foodnetserver.rest.services.BusinessFeedbackService;
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
@RequestMapping("")
public class BusinessFeedbackController {
    
    @Autowired
    private BusinessFeedbackService bfService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/businesses/{businessId}/users/{userId}/feedback")
    @PreAuthorize("#userId == principal.getId() && hasAuthority('USER')")
    public void create(@PathVariable int businessId, @PathVariable int userId, 
            @RequestBody BusinessFeedback bf) {
        bfService.create(bf, businessId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/businesses/{businessId}/feedback")
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public List<BusinessFeedback> get(@PathVariable int businessId) {
        return bfService.getByBusinessId(businessId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/businesses/{businessId}/feedback/{feedbackId}")
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public BusinessFeedback get(@PathVariable int businessId, @PathVariable int feedbackId) {
        return bfService.get(feedbackId);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/businesses/{businessId}/feedback/{feedbackId}")
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public void delete(@PathVariable int businessId, @PathVariable int feedbackId) {
        bfService.delete(feedbackId);
    }
}
