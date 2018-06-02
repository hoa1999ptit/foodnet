/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Comment;
import foodnet.foodnetserver.BLL.Post;
import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.DAL.CommentRepository;
import foodnet.foodnetserver.rest.security.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class CommentService extends EntityService {

    @Autowired
    private CommentRepository commentRepo;
    
    public void create(Comment comment, int postId) {
        Useri user = comment.getUserId();
        if (user == null || user.getUserId() != AuthContext.getPrincipalId())
            return;
    
        comment.setPostId(new Post(postId));
        comment.setUserId(user);
        commentRepo.saveAndFlush(comment);
    }
    
    public void delete(int commentId) {
        Comment comment = commentRepo.findById(commentId).get();
        if (comment != null && comment.getUserId().getUserId() == AuthContext.getPrincipalId())
            commentRepo.deleteById(commentId);
    }
    
    public Comment get(int commentId) {
        return commentRepo.findById(commentId).get();
    }
    
}
