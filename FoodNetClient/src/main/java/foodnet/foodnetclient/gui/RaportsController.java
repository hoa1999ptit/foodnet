/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import static foodnet.foodnetclient.gui.DynamicController.context;
import foodnet.foodnetclient.http.RaportRequest;
import foodnet.foodnetclient.http.RequestException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class RaportsController extends DynamicController implements Initializable {

    @FXML
    private ListView<RaportListItem> raportsListView;

    /**
     * Initializes t he controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void init() {
        initListView();
    }
    
    private void initListView() {
        ObservableList<RaportListItem> lst = raportsListView.getItems();
        lst.add(new RaportListItem("All Businesses", RaportRequest.ALL_BUSINESSES));
        lst.add(new RaportListItem("All Posts", RaportRequest.ALL_POSTS));
        lst.add(new RaportListItem("All Feedbacks", RaportRequest.ALL_FEEDBACKS));
        lst.add(new RaportListItem("All Products", RaportRequest.ALL_PRODUCTS));
        lst.add(new RaportListItem("All Users", RaportRequest.ALL_USERS));
    }
    
    private void saveRaport(String raportName) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Raport");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = fc.showSaveDialog(((Node)root).getScene().getWindow());
        if (file != null) {
            try {
                raportRequest.getRaport(raportName, file);
            } catch (RequestException ex) {
                Alerts.info("Could not save raport: " + ex.getMessage());
            }
        }
    }
    
    private class RaportListItem extends BorderPane {
        
        private String label;
        private String raportName;
        private Button saveBtn;
        
        public RaportListItem(String label, String raportName) {
            this.label = label;
            this.raportName = raportName;
            saveBtn = new Button("Save");
            saveBtn.setOnAction((event) -> {
                    saveRaport(raportName);
                    Alerts.info("Raport saved successfully!");
            });
                        
            this.setLeft(new Label(this.label));
            this.setRight(saveBtn);
        }
        
        private String getRaportName() {
            return raportName;
        }
        
    }
    
}
