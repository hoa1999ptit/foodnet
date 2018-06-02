/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Adresa;
import foodnet.foodnetclient.entities.LoginEntity;
import foodnet.foodnetclient.entities.Telefoni;
import foodnet.foodnetclient.entities.Useri;
import static foodnet.foodnetclient.gui.DynamicController.request;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import static foodnet.foodnetclient.http.Request.path;
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

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class EditUserController extends DynamicController implements Initializable {

    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
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
    
    private Useri user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void init() {
        user = (Useri) context.getAuthEntity();
        firstnameField.setText(user.getEmri());
        lastnameField.setText(user.getMbiemri());
        LoginEntity le = user.getLoginEntityId();
        usernameField.setText(le.getUsername());
        emailField.setText(le.getEmail());
        Adresa adr = user.getAdresaList().get(0);
        cityField.setText(adr.getQyteti());
        addressField.setText(adr.getRruga());
        phoneNumberField.setText(user.getTelefoniList().get(0).getNumri());
    }

    @FXML
    private void onUpdateButtonClicked(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(usernameField, firstnameField, lastnameField,
                cityField, emailField, addressField, phoneNumberField, passwordField)) {
            Alerts.info("All fields must be filled");
        } else if (!FieldUtils.validPhoneNumber(phoneNumberField)) {
            Alerts.info("Phone number is not valid");
        } else {
            try {
                updateUserFromFields();
                request.update(Useri.class, user, path("(users)/{}", user.getUserId()));
                DynamicController.setToCenter(root, FXMLFiles.USER_INFO);
            } catch (RequestException ex) {
                Alerts.info(ex);
            }
        }
    }
    
    private void updateUserFromFields() {
        user.setEmri(firstnameField.getText());
        user.setMbiemri(lastnameField.getText());
        
        LoginEntity le = user.getLoginEntityId();
        le.setUsername(usernameField.getText());
        le.setEmail(emailField.getText());
        le.setPasswordi(passwordField.getText());
        user.setLoginEntityId(le);
        
        Adresa adr = user.getAdresaList().get(0);
        adr.setQyteti(cityField.getText());
        adr.setRruga(addressField.getText());
        List<Adresa> adrLst = new ArrayList<>();
        adrLst.add(adr);
        user.setAdresaList(adrLst);
        
        Telefoni tf = user.getTelefoniList().get(0);
        tf.setNumri(phoneNumberField.getText());
        List<Telefoni> tfList = new ArrayList<>();
        tfList.add(tf);
        user.setTelefoniList(tfList);
    }
}
