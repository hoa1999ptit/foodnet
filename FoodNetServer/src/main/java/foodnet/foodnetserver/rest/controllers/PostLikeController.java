/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.rest.services.PostLikeService;
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
public class PostLikeController {
    
    @Autowired
    private PostLikeService plService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/posts/{postId}/likes")
    @PreAuthorize("#userId == principal.getId() && hasAuthority('USER')")
    public void create(@PathVariable int userId, @PathVariable int postId) {
        plService.create(userId, postId);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/posts/{postId}/likes")
    @PreAuthorize("#userId == principal.getId() && hasAuthority('USER')")
    public void delete(@PathVariable int userId, @PathVariable int postId) {
        plService.delete(userId, postId);
    }
}
