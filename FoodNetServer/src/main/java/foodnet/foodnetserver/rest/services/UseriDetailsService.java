/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.LoginEntity;
import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.DAL.BiznesiRepository;
import foodnet.foodnetserver.DAL.LoginEntityRepository;
import foodnet.foodnetserver.DAL.LoginRolesRepository;
import foodnet.foodnetserver.rest.security.model.AuthDetails;
import foodnet.foodnetserver.DAL.UseriRepository;
import foodnet.foodnetserver.rest.security.model.BusinessAuthDetails;
import foodnet.foodnetserver.rest.security.model.UserAuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class UseriDetailsService implements UserDetailsService {

    @Autowired
    private UseriRepository userRepo;
    
    @Autowired
    private BiznesiRepository biznesiRepo;
    
    @Autowired
    private LoginEntityRepository loginRepo;
    
    @Autowired
    private LoginRolesRepository lrRepo;
    
    @Override
    public AuthDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        LoginEntity le = loginRepo.findByUsername(username);
        AuthDetails details = null;
        
        Useri user = userRepo.findByLoginId(le.getLoginId());
        if (user != null) {
            details = new UserAuthDetails(user);
        } else {
            Biznesi b = biznesiRepo.findByLoginId(le.getLoginId());
            if (b != null)
                details = new BusinessAuthDetails(b);
        }        
      
        if (details == null || details.entityIsNull())
            throw new UsernameNotFoundException("User with username '" + username + "' does not exist");
          
        System.out.println("Comes here!!!");
        System.out.println("Subbmited username: " + username);
        System.out.println("User found: " + details);
        details.setLoginsRolesRepo(lrRepo);
        System.out.println("SETS IT TOOO!");
        return details;
        
    }

}
