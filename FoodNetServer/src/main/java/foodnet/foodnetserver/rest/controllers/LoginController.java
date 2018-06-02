/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.LoginEntity;
import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.DAL.BiznesiRepository;
import foodnet.foodnetserver.DAL.UseriRepository;
import foodnet.foodnetserver.exceptions.LoginException;
import foodnet.foodnetserver.rest.entities.ErrorMessage;
import foodnet.foodnetserver.rest.security.model.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author Kushtrim Hajrizi
 */
@RestController
public class LoginController {
    
    @Autowired
    private UseriRepository userRepo;
    
    @Autowired
    private BiznesiRepository biznesRepo;

    @RequestMapping(method = RequestMethod.POST, value = "/login/user")
    public ResponseEntity<Useri> loginUser() {
        AuthDetails ad = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Useri u = userRepo.findByLoginId(ad.getAuthId());
        if (u == null)
            return new ResponseEntity(new ErrorMessage("Wrong username or password"), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity(u, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/login/business")
    public ResponseEntity<Biznesi> loginBusiness() {
        AuthDetails ad = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Biznesi b =  biznesRepo.findByLoginId(ad.getAuthId());
        if (b == null)
            return new ResponseEntity(new ErrorMessage("Wrong username or password"), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity(b, HttpStatus.OK);
    }
}
