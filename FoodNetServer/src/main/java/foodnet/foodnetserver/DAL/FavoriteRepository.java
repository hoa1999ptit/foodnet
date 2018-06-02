/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.DAL;

import foodnet.foodnetserver.BLL.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    
    @Query("SELECT f FROM Favorite f WHERE f.userId.userId = :userId AND f.biznesId.biznesId = :businessId")
    public Favorite findFavorite(@Param("userId") int userId, @Param("businessId") int businessId);
}
