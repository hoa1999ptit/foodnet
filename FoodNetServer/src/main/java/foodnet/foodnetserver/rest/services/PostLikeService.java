/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.PostLike;
import foodnet.foodnetserver.DAL.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class PostLikeService {
    
    @Autowired
    private PostLikeRepository postLikeRepo;
    
     public void create(int userId, int postId) {
        postLikeRepo.saveAndFlush(new PostLike(userId, postId));
    }
    
    public void delete(int userId, int postId) {
        PostLike pl = postLikeRepo.findPostLike(userId, postId);
        if (pl != null)
            postLikeRepo.deleteById(pl.getPostLikeId());
    }
}
