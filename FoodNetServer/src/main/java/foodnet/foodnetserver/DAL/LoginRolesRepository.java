/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.DAL;

import foodnet.foodnetserver.BLL.LoginsRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Repository
public interface LoginRolesRepository extends JpaRepository<LoginsRoles, Integer> {
    
    @Query("SELECT l FROM LoginsRoles l WHERE l.loginEntityId.loginId = :loginId")
    public LoginsRoles findByLoginId(@Param("loginId") int loginId);
}
