/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.Produkti;
import foodnet.foodnetserver.DAL.ProduktiRepository;
import foodnet.foodnetserver.rest.security.AuthContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gazmend Haziri
 */
@Service
public class ProduktiService extends EntityService {

    @Autowired
    private ProduktiRepository produktiRepo;
    
    public void create(Produkti t) {
        t.setBiznesId(new Biznesi(AuthContext.getPrincipalId()));
        produktiRepo.saveAndFlush(t);
    }
    
    public Produkti get(int produktId) {
        return produktiRepo.findById(produktId).get();
    }
    
    public List<Produkti> getAllByBusinessId(int businessId) {
        return produktiRepo.findAllByBusinessId(businessId);
    }

    public void delete(int productId) {
        Produkti p = produktiRepo.findById(productId).get();
        if (p != null && p.getBiznesId().getBiznesId() == AuthContext.getPrincipalId())
            produktiRepo.deleteById(productId);
    }

    public void update(Produkti t, int productId) {
        t.setBiznesId(new Biznesi(AuthContext.getPrincipalId()));
        t.setProduktId(productId);
        Produkti checkProduct = produktiRepo.findById(productId).get();
        if (checkProduct != null && checkProduct.getBiznesId().getBiznesId() == AuthContext.getPrincipalId())
            produktiRepo.saveAndFlush(t);
    }
    
}
