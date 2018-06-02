/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class MainController extends DynamicController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void init() {
        root.setLeft(null);
    }

    @FXML
    private void onSignInClicked(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.SIGN_IN);
    }

    @FXML
    private void onSignUpClicked(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.SIGN_UP);
    }

    
}
