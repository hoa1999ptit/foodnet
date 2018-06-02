/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import foodnet.foodnetserver.BLL.LoginEntity;
import foodnet.foodnetserver.BLL.LoginsRoles;
import foodnet.foodnetserver.BLL.Roles;
import foodnet.foodnetserver.DAL.LoginEntityRepository;
import foodnet.foodnetserver.DAL.LoginRolesRepository;
import foodnet.foodnetserver.DAL.RolesRepository;
import foodnet.foodnetserver.rest.entities.ErrorMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class LoginService extends EntityService {
    
    @Autowired
    private static LoginEntityRepository loginRepo;
    
    @Autowired
    private RolesRepository rolesRepo;
    
    @Autowired
    private LoginRolesRepository lrRepo;
    
    private static final Pattern emailRegex = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    private static final Pattern constraintRegex = 
        Pattern.compile(".*(?<unique>\\(.*\\)).*");
    
    public static void encodePassword(LoginEntity loginEntity) {
        loginEntity.setPasswordi(new BCryptPasswordEncoder().encode(loginEntity.getPasswordi()));
    }
    
    public static boolean hasAdminPrefix(LoginEntity loginEntity) {
        return loginEntity.getUsername().startsWith("fn_");
    }
    
    public static boolean hasValidEmail(LoginEntity loginEntity) {
        Matcher matcher = emailRegex .matcher(loginEntity.getEmail());
        return matcher.find();
    }
    
    public void setRole(LoginEntity loginEntity, String role) {
        LoginsRoles lrole = new LoginsRoles();
        lrole.setRoleId(rolesRepo.findByEmri(role));
        lrole.setLoginEntityId(loginEntity);
        
        lrRepo.saveAndFlush(lrole);
    }
    
    public static ResponseEntity getExceptionMessage(DataIntegrityViolationException dex) {
        Throwable t = dex.getCause();
        while ((t != null) && !(t instanceof SQLServerException)) {
            t = t.getCause();
        }
        if (t instanceof SQLServerException) {
            SQLServerException cex = (SQLServerException) t;
            Matcher m = constraintRegex.matcher(cex.getMessage());
            m.find();
            String result = m.group("unique");
            return new ResponseEntity(new ErrorMessage(result + " is taken. Try something different."), HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity(new ErrorMessage(dex.getMessage()), HttpStatus.FORBIDDEN);
        }
    }
}
