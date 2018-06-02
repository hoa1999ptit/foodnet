/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Adresa;
import foodnet.foodnetclient.entities.AuthEntity;
import foodnet.foodnetclient.entities.LoginEntity;
import foodnet.foodnetclient.entities.Telefoni;
import foodnet.foodnetclient.entities.Useri;
import static foodnet.foodnetclient.gui.DynamicController.request;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import foodnet.foodnetclient.http.Request;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sun.reflect.misc.FieldUtil;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class SignUpController extends DynamicController implements Initializable {

    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordRepeatField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSignUp(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(usernameField, firstnameField, lastnameField,
                cityField, emailField, addressField, phoneNumberField, passwordField, passwordRepeatField)) {
            Alerts.info("All fields must be filled");
        } else if (!passwordField.getText().equals(passwordRepeatField.getText())) {
            Alerts.info("Password does not match");
        } else if (!FieldUtils.validPhoneNumber(phoneNumberField)) {
            Alerts.info("Phone number is not valid");
        } else {
            try {
                request.create(Useri.class, getUserFromFields(), "register/user");
                signIn();
            } catch (RequestException ex) {
                Alerts.info(ex);
            }
        }
    }
    
    private void signIn() {
        try {
            AuthEntity ae = request.login(
                    usernameField.getText(), passwordField.getText(), Request.USER_LOGIN);
            context.setAuthEntity(ae);
            
            DynamicController.setToLeft(root, FXMLFiles.USER_SIDERBAR);
            DynamicController.setToCenter(root, FXMLFiles.FOODNETS);
        } catch (RequestException ex) {
            Alerts.info("Wrong username or password");
        }
    }
    
    private Useri getUserFromFields() {
        Useri user = new Useri();
        user.setEmri(firstnameField.getText());
        user.setMbiemri(lastnameField.getText());
        
        LoginEntity le = new LoginEntity();
        le.setUsername(usernameField.getText());
        le.setEmail(emailField.getText());
        le.setPasswordi(passwordField.getText());
        user.setLoginEntityId(le);
        
        Adresa adr = new Adresa();
        adr.setQyteti(cityField.getText());
        adr.setRruga(addressField.getText());
        List<Adresa> adrLst = new ArrayList<>();
        adrLst.add(adr);
        user.setAdresaList(adrLst);
        
        Telefoni tf = new Telefoni();
        tf.setNumri(phoneNumberField.getText());
        List<Telefoni> tfList = new ArrayList<>();
        tfList.add(tf);
        user.setTelefoniList(tfList);
        return user;
    }

    @FXML
    private void onSignIn(MouseEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.SIGN_IN);
    }

    @FXML
    private void onCreateBusiness(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.BUSINESS_SIGN_UP);
    }
    
}
