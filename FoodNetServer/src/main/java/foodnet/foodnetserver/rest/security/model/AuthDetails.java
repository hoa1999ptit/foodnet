/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.security.model;

import foodnet.foodnetserver.BLL.LoginsRoles;
import foodnet.foodnetserver.DAL.LoginRolesRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Kushtrim Hajrizi
 */
public abstract class AuthDetails implements UserDetails {

    private LoginRolesRepository lrRepo;
    
    public abstract boolean entityIsNull();
    public abstract Integer getId();
    public abstract Integer getAuthId();
    public abstract String getRole();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        LoginsRoles lr = lrRepo.findByLoginId(getAuthId());
        if (lr == null)
            return null;
        authorities.add(new SimpleGrantedAuthority(lr.getRoleId().getEmri()));
        return authorities;
    }
   
    
    public void setLoginsRolesRepo(LoginRolesRepository lrRepo) {
        this.lrRepo = lrRepo;
    }
}
