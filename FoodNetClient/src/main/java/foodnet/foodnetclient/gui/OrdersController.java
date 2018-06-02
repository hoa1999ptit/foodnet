/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Porosia;
import foodnet.foodnetclient.entities.Produkti;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class OrdersController extends DynamicController implements Initializable {

    @FXML
    private TableView<Porosia> ordersTable;
    @FXML
    private TableColumn<Porosia, Integer> orderIdField;
    @FXML
    private TableColumn<Porosia, String> customerField;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
        initTable();
    }
    
    private void initTable() {
        
        orderIdField.setCellValueFactory(
                new PropertyValueFactory<>("porosiaId"));
        customerField.setCellValueFactory(
                new PropertyValueFactory<>("userId"));
        

        ordersTable.setItems(getPorosite());
        
        ordersTable.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                context.setOrder(ordersTable.getSelectionModel().getSelectedItem());
                DynamicController.setToRight(root, FXMLFiles.READ_ORDER);
            }
        });
    }
    
    private ObservableList<Porosia> getPorosite() {
        ObservableList<Porosia> ol = FXCollections.observableArrayList();
        List<Porosia> porosiaLst = null;
        try {
            porosiaLst = request.getAll(
                    Porosia.class, path("(businesses)/{}/(orders)", context.getId()), null);
        } catch (RequestException ex) {
            Alerts.info(ex);
        }
        
        for (Porosia p: porosiaLst)
            if (p.getUserId() != null)
                ol.add(p);

        return ol;
    }
    
}
