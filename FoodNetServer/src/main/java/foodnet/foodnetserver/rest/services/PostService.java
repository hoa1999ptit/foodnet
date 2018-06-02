/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.Post;
import foodnet.foodnetserver.DAL.PostRepository;
import foodnet.foodnetserver.rest.security.AuthContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class PostService extends EntityService {

    @Autowired
    private PostRepository postRepo;
    
    public void create(Post t) {
        postRepo.saveAndFlush(t);
    }
    
    public List<Post> getAll(int businessId) {
        //return postRepo.findByBusinessId(businessId);
        List<Post> lp = postRepo.findByBusinessId(businessId);
        System.out.println("LISTPOST: " + lp);
        return lp;
    }

    public void delete(int id) {
        postRepo.deleteById(id);
    }

    public void update(Post post, int postId, int businessId) {
        post.setPostId(postId);
        Post checkPost = postRepo.findById(postId).get();
        if (checkPost != null && businessId == checkPost.getBiznesId().getBiznesId()) {
            post.setBiznesId(checkPost.getBiznesId());
            postRepo.saveAndFlush(post);
        }
    }
    
    public Post get(int postId) {
        return postRepo.findById(postId).get();
    }
}
