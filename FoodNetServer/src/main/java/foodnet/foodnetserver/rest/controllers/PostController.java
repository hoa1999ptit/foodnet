/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.Post;
import foodnet.foodnetserver.rest.services.PostService;
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
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public void create(@PathVariable int businessId, @RequestBody Post post) {
        post.setBiznesId(new Biznesi(businessId));
        postService.create(post);
    }
    
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    //@PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public List<Post> getAll(@PathVariable int businessId) {
        return postService.getAll(businessId);
    }
    
    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.DELETE)
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public void delete(@PathVariable int businessId, @PathVariable int postId) {
        postService.delete(postId);
    }
    
    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.PUT)
    @PreAuthorize("#businessId == principal.getId() && hasAuthority('BUSINESS')")
    public void update(@PathVariable int businessId, @PathVariable int postId, @RequestBody Post post) {
        postService.update(post, postId, businessId);
    }
    
    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public Post get(@PathVariable int businessId, @PathVariable int postId) {
        return postService.get(postId);
    }
}
