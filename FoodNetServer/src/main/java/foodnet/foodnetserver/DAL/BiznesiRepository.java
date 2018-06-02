/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.DAL;

import foodnet.foodnetserver.BLL.Biznesi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Repository
public interface BiznesiRepository extends JpaRepository<Biznesi, Integer> {

    @Query("SELECT b FROM Biznesi b WHERE b.loginEntityId.loginId = :loginId")
    Biznesi findByLoginId(@Param("loginId") int loginId);
    
    @Query("SELECT b FROM Biznesi b INNER JOIN Favorite f ON b.biznesId = f.biznesId.biznesId WHERE f.userId.userId = :userId")
    List<Biznesi> findUserFavorites(@Param("userId") int userId);
    
    @Query("SELECT b FROM Biznesi b WHERE b.emri LIKE %:patt%")
    List<Biznesi> findByNamePattern(@Param("patt") String namePattern);
}
