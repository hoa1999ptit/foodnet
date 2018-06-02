/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.SoftwareFeedback;
import foodnet.foodnetserver.rest.services.SoftwareFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kushtrim Hajrizi
 */
@RestController
public class SoftwareFeedbackController {
    
    @Autowired
    private SoftwareFeedbackService sfService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/feedback")
    public void create(@RequestBody SoftwareFeedback sf) {
        sfService.create(sf);
    }
}
