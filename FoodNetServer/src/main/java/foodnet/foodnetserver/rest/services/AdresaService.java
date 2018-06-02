/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Adresa;
import foodnet.foodnetserver.DAL.AdresaRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class AdresaService extends EntityService {

    @Autowired
    private AdresaRepository adresaRepo;
    
    public void create(Adresa address) {
        adresaRepo.save(address);
    }
    
    public void update(Adresa address) {
        adresaRepo.save(address);
    }

}
