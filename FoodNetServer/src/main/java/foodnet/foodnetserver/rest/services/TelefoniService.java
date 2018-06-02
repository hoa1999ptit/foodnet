/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Telefoni;
import foodnet.foodnetserver.DAL.TelefoniRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class TelefoniService extends EntityService {
    
    @Autowired
    private TelefoniRepository telefoniRepo;
 
    public void create(Telefoni t) {
        telefoniRepo.save(t);
    }

}
