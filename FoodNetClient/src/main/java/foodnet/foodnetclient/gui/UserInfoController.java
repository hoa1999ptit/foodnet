/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Adresa;
import foodnet.foodnetclient.entities.Useri;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class UserInfoController extends DynamicController implements Initializable {

    @FXML
    private Label fullnameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label phoneNumberLabel;

    private Useri user;
    @FXML
    private Label cityLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void init() {
        try {
            user = request.get(Useri.class, path("(users)/{}", context.getId()), null);
            loadUserData();
        } catch (RequestException ex) {
            Alerts.info("Could not load user from server: " + ex.getMessage());
        }
    }
    
    private void loadUserData() {
        fullnameLabel.setText(user.getEmri() + " " + user.getMbiemri());
        emailLabel.setText(user.getEmail());
        usernameLabel.setText(user.getUsername());
        Adresa adr = user.getAdresaList().get(0);
        cityLabel.setText(adr.getQyteti());
        addressLabel.setText(adr.getRruga());
        phoneNumberLabel.setText(user.getTelefoniList().get(0).getNumri());
    }
    
    @FXML
    private void onEdit(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.EDIT_USER);
    }

    @FXML
    private void onDelete(ActionEvent event) {
        if (Alerts.confirm()) {
            try {
                request.delete(Useri.class, path("(users)/{}", user.getUserId()));
                Alerts.info("Account deleted successfully!");

                root.setLeft(null);
                context.clear();
                DynamicController.setToCenter(root, FXMLFiles.SIGN_IN);
            } catch (RequestException ex) {
                Alerts.info("Could not delete user: " + ex.getMessage());
            }
        }
    }
    
}
