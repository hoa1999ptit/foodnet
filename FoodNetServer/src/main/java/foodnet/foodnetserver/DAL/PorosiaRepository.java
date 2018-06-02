/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.DAL;

import foodnet.foodnetserver.BLL.Porosia;
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
public interface PorosiaRepository extends JpaRepository<Porosia, Integer> {
    
    @Query("SELECT p FROM Porosia p WHERE p.biznesId.biznesId = :businessId")
    public List<Porosia> findByBusinessId(@Param("businessId") int businessId);
    
}
