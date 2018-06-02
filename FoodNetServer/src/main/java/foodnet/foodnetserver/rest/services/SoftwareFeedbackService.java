/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.LoginEntity;
import foodnet.foodnetserver.BLL.SoftwareFeedback;
import foodnet.foodnetserver.DAL.SoftwareFeedbackRepository;
import foodnet.foodnetserver.rest.security.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class SoftwareFeedbackService {
    
    @Autowired
    private SoftwareFeedbackRepository sfRepo;
    
    public void create(SoftwareFeedback sf) {
        sf.setLoginId(new LoginEntity(AuthContext.getPrincipalAuthId()));
        sfRepo.saveAndFlush(sf);
    }
}
