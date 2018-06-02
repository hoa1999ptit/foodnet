/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Kategoria;
import foodnet.foodnetclient.entities.Produkti;
import static foodnet.foodnetclient.gui.DynamicController.request;
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
public class EditProductController extends DynamicController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ComboBox<String> categoryBox;
    
    private Produkti product;

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
        product = context.getProduct();
        nameField.setText(product.getEmri());
        priceField.setText(product.getCmimi().toString());
        descriptionArea.setText(product.getPershkrimi());
    }    

    @FXML
    private void onUpdate(ActionEvent event) {
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
            
            product.setEmri(nameField.getText());
            product.setPershkrimi(descriptionArea.getText());
            product.setCmimi(new BigDecimal(price));
            product.setEmriKategoris(new Kategoria(categoryBox.getSelectionModel().getSelectedItem()));
            
            try {
                request.update(
                        Produkti.class, 
                        product, 
                        path("(businesses)/{}/(products)/{}", context.getId(), product.getProduktId())
                );
                Alerts.info("Product updated successfully!");
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            } catch (RequestException ex) {
                Alerts.info("Could not update product: " + ex.getMessage());
            }
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        if (Alerts.confirm()) {
            try {
                request.delete(Produkti.class, 
                        path("(businesses)/{}/(products)/{}", context.getId(), product.getProduktId()));
                Alerts.info("Product deleted successfully!");
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            } catch (RequestException ex) {
                Alerts.info("Could not delete product: " + ex.getMessage());
            }
        }
    }
    
}
