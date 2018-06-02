/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Kategoria;
import foodnet.foodnetclient.entities.Produkti;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class CreateProductController extends DynamicController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ComboBox<String> categoryBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
        categoryBox.getItems().addAll(Kategoria.PIJE, Kategoria.USHQIM);
    }

    @FXML
    private void onCreate(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(nameField, priceField, descriptionArea)) {
            Alerts.info("All fields must be filled.");
        } else if (categoryBox.getSelectionModel().getSelectedItem() == null) {
            Alerts.info("A category must be selected.");
        } else {
            String price = priceField.getText();
            try {
                double cmimi = Double.parseDouble(price);
            } catch (NumberFormatException ex) {
                Alerts.info("Price must be a number.");
                return;
            }
            
            Produkti p = new Produkti(
                nameField.getText(),
                descriptionArea.getText(),
                new BigDecimal(price),
                new Kategoria(categoryBox.getSelectionModel().getSelectedItem())
            );
            
            try {
                request.create(Produkti.class, p, path("(businesses)/{}/(products)", context.getId()));
                Alerts.info("Product created successfully!");
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            } catch (RequestException ex) {
                Alerts.info("Could not create product: " + ex.getMessage());
            }
        }
    }
    
}
