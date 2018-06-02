/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.rest.entities.CompactBusiness;
import foodnet.foodnetserver.rest.services.SearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kushtrim Hajrizi
 */
@RestController
public class SearchController {
    
    @Autowired
    private SearchService searchService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public List<CompactBusiness> get(@RequestParam("content") String content) {
        return searchService.get(content);
    }
}
