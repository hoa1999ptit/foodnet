/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.controllers;

import foodnet.foodnetserver.BLL.Comment;
import foodnet.foodnetserver.rest.services.CommentService;
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
@RequestMapping("/businesses/{businessId}/posts/{postId}")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('USER')")
    public void create(
            @PathVariable int businessId, @PathVariable int postId, @RequestBody Comment comment) {
        commentService.create(comment, postId);
    }
    
    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('USER')")
    public void delete(@PathVariable int businessId, @PathVariable int postId, @PathVariable int commentId) {
        commentService.delete(commentId);
    }
    
    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.GET)
    public Comment get(@PathVariable int businessId, @PathVariable int postId, @PathVariable int commentId) {
        return commentService.get(commentId);
    }
}
