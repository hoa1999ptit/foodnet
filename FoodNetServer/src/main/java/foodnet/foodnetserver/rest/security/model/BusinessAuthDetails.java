/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.security.model;

import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.Roles;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class BusinessAuthDetails extends AuthDetails {

    private Biznesi b;
    
    public BusinessAuthDetails(Biznesi b) {
        this.b = b;
    }
    
    @Override
    public String getRole() {
        return Roles.BUSINESS;
    }
    
    @Override
    public boolean entityIsNull() {
        return b == null;
    }

    @Override
    public Integer getId() {
        return b.getBiznesId();
    }
    
    @Override
    public Integer getAuthId() {
        return b.getLoginEntityId().getLoginId();
    }
    
    @Override
    public String getPassword() {
        return b.getLoginEntityId().getUsername();
    }

    @Override
    public String getUsername() {
        return b.getLoginEntityId().getUsername();
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
