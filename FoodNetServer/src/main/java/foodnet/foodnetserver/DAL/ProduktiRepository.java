/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.DAL;

import foodnet.foodnetserver.BLL.Produkti;
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
public interface ProduktiRepository extends JpaRepository<Produkti, Integer> {
    
    @Query("SELECT p FROM Produkti p WHERE p.biznesId.biznesId = :biznesId")
    public List<Produkti> findAllByBusinessId(@Param("biznesId") int biznesId);
}
