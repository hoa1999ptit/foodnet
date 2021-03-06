/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.gui.utils.ControllerUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class UserSidebarController extends DynamicController implements Initializable {

    @FXML
    private Pane raportsPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
//        if (!ControllerUtils.hasAdminPrefix(context.getAuthEntity()))
//            raportsPane.setVisible(false);
    }

    @FXML
    private void onUserInfo(MouseEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.USER_INFO);
    }

    @FXML
    private void onFoodnets(MouseEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.FOODNETS);
    }

    @FXML
    private void onLogout(MouseEvent event) {
        context.clear();
        root.setLeft(null);
        DynamicController.setToCenter(root, FXMLFiles.SIGN_IN);
    }

    @FXML
    private void onShowRaports(MouseEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.RAPORTS);
    }

    @FXML
    private void onCreateFeedback(MouseEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.SOFTWARE_FEEDBACK);
    }
    
}
