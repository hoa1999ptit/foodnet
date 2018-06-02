/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.BusinessFeedback;
import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.DAL.BusinessFeedbackRepository;
import foodnet.foodnetserver.rest.security.AuthContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class BusinessFeedbackService extends EntityService {

    @Autowired
    private BusinessFeedbackRepository bfRepo;
    
    public void create(BusinessFeedback t, int businessId) {
        t.setBiznesId(new Biznesi(businessId));
        t.setUserId(new Useri(AuthContext.getPrincipalId()));
        bfRepo.saveAndFlush(t);
    }
    
    public List<BusinessFeedback> getByBusinessId(int businessId) {
        return bfRepo.findAllByBusinessId(businessId);
    }
    
    public BusinessFeedback get(int feedbackId) {
        BusinessFeedback checkBf = bfRepo.findByFeedbackId(feedbackId);
        if (belongsToCurrentBusiness(checkBf))
            return checkBf;
        return new BusinessFeedback();
    }
    
    public void delete(int feedbackId) {
        BusinessFeedback checkBf = bfRepo.findByFeedbackId(feedbackId);
        if (belongsToCurrentBusiness(checkBf))
            bfRepo.deleteById(feedbackId);
            
    }
    
    private boolean belongsToCurrentBusiness(BusinessFeedback checkBf) {
        return checkBf != null && checkBf.getUserId().getUserId() == AuthContext.getPrincipalId();
    }
    
}
