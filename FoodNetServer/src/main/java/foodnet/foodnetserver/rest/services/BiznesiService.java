/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Adresa;
import foodnet.foodnetserver.BLL.Biznesi;
import foodnet.foodnetserver.BLL.LoginEntity;
import foodnet.foodnetserver.BLL.LoginsRoles;
import foodnet.foodnetserver.BLL.Roles;
import foodnet.foodnetserver.BLL.Telefoni;
import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.DAL.BiznesiRepository;
import foodnet.foodnetserver.DAL.LoginRolesRepository;
import foodnet.foodnetserver.DAL.RolesRepository;
import foodnet.foodnetserver.rest.entities.ErrorMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class BiznesiService {

    @Autowired
    private BiznesiRepository biznesiRepo;
        
    @Autowired
    private RolesRepository rolesRepo;
    
    @Autowired
    private LoginRolesRepository lrRepo;
    
    @Autowired
    private LoginService loginService;
    
    public ResponseEntity create(Biznesi b) {
        ResponseEntity re = save(b);
        if (re.getStatusCode() == HttpStatus.OK)
            loginService.setRole(b.getLoginEntityId(), Roles.BUSINESS);
        return re;
    }
    
    public ResponseEntity update(Biznesi b) {
        return save(b);
    }
    
    private ResponseEntity save(Biznesi b) {
        LoginEntity le = b.getLoginEntityId();
        if (!LoginService.hasValidEmail(le)) {
            return new ResponseEntity(new ErrorMessage("Email not valid."), HttpStatus.FORBIDDEN);
        } else if (LoginService.hasAdminPrefix(le)) {
            return new ResponseEntity(new ErrorMessage("Username not valid."), HttpStatus.FORBIDDEN);
        } else {
            try {
                link(b);
                LoginService.encodePassword(b.getLoginEntityId());
                biznesiRepo.save(b);
                biznesiRepo.flush();
            } catch (DataIntegrityViolationException ex) {
                return LoginService.getExceptionMessage(ex);
            } 
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public Biznesi get(int id) {
        return biznesiRepo.findById(id).get();
    }

    public List<Biznesi> getAll() {
        return biznesiRepo.findAll();
    }

    public List<Biznesi> getAllPublic() {
        List<Biznesi> bizneset = getAll();
        bizneset.forEach(b -> b.setLoginEntityId(null));
        return bizneset;
    }
    
    public void delete(int id) {
        biznesiRepo.deleteById(id);
        biznesiRepo.flush();
    }
    
    public Biznesi getPublic(int id) {
        Biznesi b = get(id);
        b.setLoginEntityId(null);
        return b;
    }
        
    private void link(Biznesi b) {
        for (Adresa a: b.getAdresaList())
            a.setBiznesId(b);
        for (Telefoni t: b.getTelefoniList())
            t.setBiznesId(b);
    }
    
    private void setRole(Biznesi b) {
        LoginsRoles lrole = new LoginsRoles();
        lrole.setRoleId(rolesRepo.findByEmri(Roles.BUSINESS));
        lrole.setLoginEntityId(b.getLoginEntityId());
        
        lrRepo.saveAndFlush(lrole);
    }
    
    public List<Biznesi> getUserFavorites(int userId) {
        return biznesiRepo.findUserFavorites(userId);
    }
    
}
