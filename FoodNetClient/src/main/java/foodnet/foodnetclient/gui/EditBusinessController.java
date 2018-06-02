/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Adresa;
import foodnet.foodnetclient.entities.Biznesi;
import foodnet.foodnetclient.entities.LoginEntity;
import foodnet.foodnetclient.entities.Telefoni;
import static foodnet.foodnetclient.gui.DynamicController.request;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class EditBusinessController extends DynamicController implements Initializable {

    private Biznesi biz;
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
    private TextField openingTimeField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField closingTimeField;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        biz = context.getBusiness();
        
        nameField.setText(biz.getEmri());
        openingTimeField.setText(sdf.format(biz.getOraHapes()));
        closingTimeField.setText(sdf.format(biz.getOraMbylljes()));
        LoginEntity le = biz.getLoginEntityId();
        usernameField.setText(le.getUsername());
        emailField.setText(le.getEmail());
        Adresa adr = biz.getAdresaList().get(0);
        cityField.setText(adr.getQyteti());
        addressField.setText(adr.getRruga());
        phoneNumberField.setText(biz.getTelefoniList().get(0).getNumri());
    }

    @FXML
    private void onUpdateButtonClicked(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(usernameField, nameField, openingTimeField, closingTimeField,
                cityField, emailField, addressField, phoneNumberField, passwordField)) {
            Alerts.info("All fields must be filled");
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
            
            updateBusinessFromFields(opening, closing);
            
            try {
                request.update(Biznesi.class, biz, path("(businesses)/{}", biz.getBiznesId()));
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            } catch (RequestException ex) {
                Alerts.info(ex);
            }
        }
    }
    
    private void updateBusinessFromFields(Date opening, Date closing) {
        biz.setEmri(nameField.getText());
        biz.setOraHapes(opening);
        biz.setOraMbylljes(closing);
        
        LoginEntity le = biz.getLoginEntityId();
        le.setUsername(usernameField.getText());
        le.setEmail(emailField.getText());
        le.setPasswordi(passwordField.getText());
        biz.setLoginEntityId(le);
        
        Adresa adr = biz.getAdresaList().get(0);
        adr.setQyteti(cityField.getText());
        adr.setRruga(addressField.getText());
        List<Adresa> adrLst = new ArrayList<>();
        adrLst.add(adr);
        biz.setAdresaList(adrLst);
        
        Telefoni tf = biz.getTelefoniList().get(0);
        tf.setNumri(phoneNumberField.getText());
        List<Telefoni> tfList = new ArrayList<>();
        tfList.add(tf);
        biz.setTelefoniList(tfList);
    }
    
}
