/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Post;
import static foodnet.foodnetclient.gui.DynamicController.request;
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
public class EditPostController extends DynamicController implements Initializable {

    @FXML
    private TextField titleField;
    @FXML
    private TextArea contentArea;
    
    private Post post;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
        post = context.getPost();
        
        titleField.setText(post.getTitle());
        contentArea.setText(post.getContent());
    }

    @FXML
    private void onUpdatePost(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(titleField, contentArea)) {
            Alerts.info("Title and Content should not be empty!");
        } else {
            Post updatedPost = new Post(titleField.getText(), contentArea.getText());
            try {
                request.update(Post.class, updatedPost, path("(businesses)/{}/(posts)/{}/", context.getId(), post.getPostId()));
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            } catch (RequestException ex) {
                Alerts.info("Could not update post right now: " + ex.getMessage());
            }
        }
    }

    @FXML
    private void onDeletePost(ActionEvent event) {
        if (Alerts.confirm()) {
            try {
                request.delete(Post.class, path("(businesses)/{}/(posts)/{}/", context.getId(), post.getPostId()));
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            } catch (RequestException ex) {
                Alerts.info("Could not delete post right now: " + ex.getMessage());
            }
        }
    }
    
}
