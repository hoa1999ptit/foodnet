/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.DAL;

import foodnet.foodnetserver.BLL.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer>{
    
    @Query("SELECT pl FROM PostLike pl WHERE pl.userId.userId = :userId AND pl.postId.postId = :postId")
    public PostLike findPostLike(@Param("userId") int userId, @Param("postId") int postId);
}
