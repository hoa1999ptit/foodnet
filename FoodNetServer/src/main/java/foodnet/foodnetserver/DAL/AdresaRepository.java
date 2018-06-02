/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.DAL;

import foodnet.foodnetserver.BLL.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Integer> {
    
}
