/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import foodnet.foodnetserver.BLL.Adresa;
import foodnet.foodnetserver.BLL.LoginEntity;
import foodnet.foodnetserver.BLL.LoginsRoles;
import foodnet.foodnetserver.BLL.Roles;
import foodnet.foodnetserver.BLL.Telefoni;
import foodnet.foodnetserver.BLL.Useri;
import foodnet.foodnetserver.DAL.LoginRolesRepository;
import foodnet.foodnetserver.DAL.RolesRepository;
import foodnet.foodnetserver.DAL.UseriRepository;
import foodnet.foodnetserver.rest.entities.ErrorMessage;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class UserService extends EntityService {
    
    @Autowired
    private UseriRepository userRepo;
    
    @Autowired
    private LoginService loginService;

    public List<Useri> getAll() {
        return userRepo.findAll();
    }
    
    public Useri get(int userId) {
        return userRepo.findById(userId).get();
    }
    
    public ResponseEntity create(Useri user) {
        ResponseEntity re = save(user);
        if (re.getStatusCode() == HttpStatus.OK)
            loginService.setRole(user.getLoginEntityId(), Roles.USER);
        return re;
    }
    
    public ResponseEntity update(Useri user) {
        return save(user);
    }
    
    private ResponseEntity save(Useri user) {
        LoginEntity le = user.getLoginEntityId();
        if (!LoginService.hasValidEmail(le)) {
            return new ResponseEntity(new ErrorMessage("Email not valid."), HttpStatus.FORBIDDEN);
        } else if (LoginService.hasAdminPrefix(le)) {
            return new ResponseEntity(new ErrorMessage("Username not valid."), HttpStatus.FORBIDDEN);
        } else {
            try {
                link(user);
                LoginService.encodePassword(user.getLoginEntityId());
                userRepo.save(user);
                userRepo.flush();
            } catch (DataIntegrityViolationException ex) {
                return LoginService.getExceptionMessage(ex);
            } 
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    
    public void delete(int userId) {
        userRepo.deleteById(userId);
    }
    
    private void link(Useri user) {
        for (Adresa a: user.getAdresaList())
            a.setUserId(user);
        for (Telefoni t: user.getTelefoniList())
            t.setUserId(user);
    }

    
    //PER BIZNES EDHE CASCADE
//    public Useri getUser(String username) {
//        return userRepo.findByUsername(username);
//    }
    //    /**
//     * Creates a Useri instance along with an Adresa and Telefoni instance,
//     * and adds them to the database.
//     * @param data Map object containing the user, address and telephone data.
//     */
//    @Override
//    public void createFromMap(Map<String, Object> data) {
//        // Get user from the data map
//        Useri user = fromMap(data, Useri.class);
//        userRepo.save(user);
//        
//        // Get address from the data map
//        Adresa address = fromMap(data, Adresa.class);
//        address.setUserId(user);
//        adresaService.create(address);
//        
//        // Check if a phone number was provided
//        if (data.containsKey("numri")) {
//            Telefoni tf = fromMap(data, Telefoni.class);
//            tf.setUserID(user);
//            telefoniService.create(tf);
//        }
//    }
//
//    /**
//     * Updates a Useri instance.
//     * Values of the Adresa and Telefoni instances that belong to the updated
//     * Useri instance are also updated.
//     * @param data Map object containing the user, address and telephone data.
//     */
//    @Override
//    public void updateFromMap(Map<String, Object> data) {
//        // Get the user data and update it
//        Useri user = fromMap(data, Useri.class);
//        userRepo.save(user);
//        
//        // Update the address and the phone number too
//        Adresa address = fromMap(data, Adresa.class);
//        adresaService.update(address);
//        Telefoni tf = fromMap(data, Telefoni.class);
//        telefoniService.update(tf);
//    }
//      public void create(Useri user) {
//        if (!LoginService.hasAdminPrefix(user.getLoginEntityId())) {
//            save(user);
//            setRole(user);
//        }
////        save(user);
////        setRole(user);
//    }
//    
//    public void createAdmin(Useri user) {
//        save(user);
//        setRole(user);
//    }
//    
//    public void update(Useri user) {
//        if (!LoginService.hasAdminPrefix(user.getLoginEntityId()))
//            save(user);
//    }
//    
//    private void save(Useri user) {
//        link(user);
//        LoginService.encodePassword(user.getLoginEntityId());
//        userRepo.saveAndFlush(user);
//    }
    
}