/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.AuthEntity;
import foodnet.foodnetclient.entities.Biznesi;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import foodnet.foodnetclient.http.Request;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class SignInController extends DynamicController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSignIn(ActionEvent event) {
        signIn(Request.USER_LOGIN);
    }

    @FXML
    private void onSignInAsBusiness(ActionEvent event) {
        signIn(Request.BUSINESS_LOGIN);
    }
    
    private void signIn(int loginType) {
        if (FieldUtils.fieldsEmpty(usernameField, passwordField)) {
            Alerts.info("All fields must be filled!");
        } else {
            AuthEntity ae = null;
            try {
                ae = request.login(
                        usernameField.getText(), passwordField.getText(), loginType);
                context.setAuthEntity(ae);
            } catch (RequestException ex) {
                Alerts.info("Wrong username or password");
                return;
            }

            if (loginType == Request.BUSINESS_LOGIN) {
                context.setBusiness((Biznesi) ae);
                DynamicController.setToLeft(root, FXMLFiles.BUSINESS_SIDEBAR);
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            } else {
                DynamicController.setToLeft(root, FXMLFiles.USER_SIDERBAR);
                DynamicController.setToCenter(root, FXMLFiles.FOODNETS);
            }
        }
    }

    @FXML
    private void onCreateAccount(MouseEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.SIGN_UP);
    }
    
}

//   int loginType = Request.USER_LOGIN;
//        
//        AuthEntity ae = null;
//        try {
//            ae = request.login("trim", "123456", loginType);
//            context.setAuthEntity(ae);
//        } catch (RequestException ex) {
//            Alerts.info(ex);
//            return;
//        }
//        
//        if (loginType == Request.BUSINESS_LOGIN) {
//            context.setBusiness((Biznesi) ae);
//            DynamicController.setToLeft(root, FXMLFiles.BUSINESS_SIDEBAR);
//            DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
//        } else {
//            DynamicController.setToLeft(root, FXMLFiles.USER_SIDERBAR);
//        }