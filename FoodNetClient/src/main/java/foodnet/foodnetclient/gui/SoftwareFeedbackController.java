/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.SoftwareFeedback;
import static foodnet.foodnetclient.gui.DynamicController.request;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class SoftwareFeedbackController extends DynamicController implements Initializable {

    @FXML
    private TextField subjectField;
    @FXML
    private TextArea contentArea;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField ratingField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onFeedbackSubmit(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(subjectField, ratingField, contentArea)) {
            Alerts.info("All fields must be filled");
        } else {
            int rating = 0;
            try {
                rating = Integer.parseInt(ratingField.getText());
            } catch (NumberFormatException ex) {
                Alerts.info("Ratings field must contain only number");
                return;
            }
            
            if (rating < 0 || rating > 5) {
                Alerts.info("Rating must be between 1 and 5");
                return;
            }
            
            SoftwareFeedback sf = new SoftwareFeedback(
                    subjectField.getText(),
                    contentArea.getText(),
                    rating
            );
            try {
                request.create(SoftwareFeedback.class, sf, path("(feedback)"));
                Alerts.info("Feedback Sent! Thank you for your help!");
                DynamicController.setToCenter(root, FXMLFiles.FOODNETS);
            } catch (RequestException ex) {
                Alerts.info("Could not create feedback! Please try again later");
            }
        }
    }
    
}
