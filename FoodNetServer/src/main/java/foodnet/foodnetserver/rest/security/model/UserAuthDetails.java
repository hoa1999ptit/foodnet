/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.security.model;

import foodnet.foodnetserver.BLL.Roles;
import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.DAL.LoginRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class UserAuthDetails extends AuthDetails {
    
    @Autowired
    private LoginRolesRepository lrRepo;
    
    private Useri u;
    
    public UserAuthDetails(Useri u) {
        this.u = u;
    }

    @Override
    public String getRole() {
        return Roles.USER;
    }

    @Override
    public boolean entityIsNull() {
        return u == null;
    }
    
    @Override
    public Integer getId() {
        return u.getUserId();
    }
      
    @Override
    public Integer getAuthId() {
        return u.getLoginEntityId().getLoginId();
    }

    @Override
    public String getPassword() {
        return u.getLoginEntityId().getPasswordi();
    }

    @Override
    public String getUsername() {
        return u.getLoginEntityId().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
