/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Adresa;
import foodnet.foodnetclient.entities.AuthEntity;
import foodnet.foodnetclient.entities.Biznesi;
import foodnet.foodnetclient.entities.LoginEntity;
import foodnet.foodnetclient.entities.Telefoni;
import static foodnet.foodnetclient.gui.DynamicController.request;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import foodnet.foodnetclient.http.Request;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class BusinessSignUpController extends DynamicController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private TextField openingTimeField;
    @FXML
    private TextField closingTimeField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSignUp(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(usernameField, nameField, openingTimeField, closingTimeField,
                cityField, emailField, addressField, phoneNumberField, passwordField, repeatPasswordField)) {
            Alerts.info("All fields must be filled");
        } else if (!passwordField.getText().equals(repeatPasswordField.getText())) {
            Alerts.info("Password does not match");
        } else if (!FieldUtils.validPhoneNumber(phoneNumberField)) {
            Alerts.info("Phone number is not valid");
        } else {
            Date opening = null;
            Date closing = null;
            if ((opening = FieldUtils.checkTimeFormat(openingTimeField)) == null ||
                    (closing = FieldUtils.checkTimeFormat(closingTimeField)) == null) {
                Alerts.info("Time format wrong! Example: 09:30");
                return;
            } 
            
            try {
                request.create(Biznesi.class, getBusinessFromFields(opening, closing), "register/business");
                signIn();
            } catch (RequestException ex) {
                Alerts.info(ex);
            }
        }
    }
    
    private Biznesi getBusinessFromFields(Date opening, Date closing) {
        Biznesi biz = new Biznesi();
        biz.setEmri(nameField.getText());
        biz.setOraHapes(opening);
        biz.setOraMbylljes(closing);
        
        LoginEntity le = new LoginEntity();
        le.setUsername(usernameField.getText());
        le.setEmail(emailField.getText());
        le.setPasswordi(passwordField.getText());
        biz.setLoginEntityId(le);
        
        Adresa adr = new Adresa();
        adr.setQyteti(cityField.getText());
        adr.setRruga(addressField.getText());
        List<Adresa> adrLst = new ArrayList<>();
        adrLst.add(adr);
        biz.setAdresaList(adrLst);
        
        Telefoni tf = new Telefoni();
        tf.setNumri(phoneNumberField.getText());
        List<Telefoni> tfList = new ArrayList<>();
        tfList.add(tf);
        biz.setTelefoniList(tfList);
        return biz;
    } 
    
    private void signIn() {
        try {
            AuthEntity ae = request.login(
                    usernameField.getText(), passwordField.getText(), Request.BUSINESS_LOGIN);
            context.setAuthEntity(ae);
            
            context.setBusiness((Biznesi) ae);
            DynamicController.setToLeft(root, FXMLFiles.BUSINESS_SIDEBAR);
            DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
        } catch (RequestException ex) {
            Alerts.info("Wrong username or password");
        }
    }

    @FXML
    private void onSignIn(MouseEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.SIGN_IN);
    }
    
}
