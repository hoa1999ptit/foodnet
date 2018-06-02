/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.Porosia;
import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.DAL.PorosiaRepository;
import foodnet.foodnetserver.rest.security.AuthContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class PorosiaService {
 
    @Autowired
    private PorosiaRepository porosiaRepo;
    
    public void create(Porosia p, int businessId) {
        p.setUserId(new Useri(AuthContext.getPrincipalId()));
        p.setBiznesId(new Biznesi(businessId));
        if (p.getPorosiaProduktiList() != null) {
            link(p);
            porosiaRepo.saveAndFlush(p);
        }
    }
    
    public void link(Porosia porosia) {
        porosia.getPorosiaProduktiList().forEach(pp -> pp.setPorosiaId(porosia));
    }
    
    public List<Porosia> getByBusinessId(int businessId) {
        return porosiaRepo.findByBusinessId(businessId);
    }
    
    public Porosia get(int orderId) {
        Porosia checkPorosia = porosiaRepo.findById(orderId).get();
        if (checkPorosia != null && checkPorosia.getBiznesId().getBiznesId() == AuthContext.getPrincipalId())
            return checkPorosia;
        return new Porosia();
    }
}
