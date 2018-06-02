/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Post;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class CreatePostController extends DynamicController implements Initializable {

    @FXML
    private TextField titleField;
    @FXML
    private TextArea contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onCreatePost(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(titleField, contentArea)) {
            Alerts.info("Title and Content should not be empty!");
        } else {
            Post post = new Post(titleField.getText(), contentArea.getText());
            try {
                request.create(Post.class, post, path("(businesses)/{}/(posts)/", context.getId()));
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            } catch (RequestException ex) {
                Alerts.info("Could not create post right now: " + ex.getMessage());
            }
        }
    }

    
}
