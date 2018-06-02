/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Biznesi;
import foodnet.foodnetclient.entities.Kategoria;
import foodnet.foodnetclient.entities.Porosia;
import foodnet.foodnetclient.entities.PorosiaProdukti;
import foodnet.foodnetclient.entities.Produkti;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class CreateOrderController extends DynamicController implements Initializable {

    @FXML
    private TableView<Produkti> productsTable;
    @FXML
    private TableColumn<Produkti, String> nameColumn;
    @FXML
    private TableColumn<Produkti, String> descriptionColumn;
    @FXML
    private TableColumn<Produkti, BigDecimal> priceColumn;
    @FXML
    private TableColumn<Produkti, Kategoria> categoryColumn;
    @FXML
    private ListView<ProductListItem> ordersListView;
    @FXML
    private TextField totalField;
    
    private Biznesi business;
    
    private List<Produkti> orderedProducts;
    private DecimalFormat df;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
        df = new DecimalFormat("#.##"); 
        orderedProducts = new ArrayList<>();
        
        business = context.getBusiness();
        initTable();
    }
    
    private void updateOrdersListView() {
        ordersListView.getItems().clear();
        initListView();
        
        double total = 0;
        for (Produkti p: orderedProducts)
            total += p.getCmimi().doubleValue();
        totalField.setText(df.format(total));
    }
    
    private void initTable() {
        
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("emri"));
        descriptionColumn.setCellValueFactory(
                new PropertyValueFactory<>("pershkrimi"));
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("cmimi"));
        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("emriKategoris"));
        
        ObservableList<Produkti> ol = FXCollections.observableArrayList();
        ol.addAll(business.getProduktiList());
        productsTable.setItems(ol);
        
        productsTable.setOnMouseClicked((event) -> {
            orderedProducts.add(productsTable.getSelectionModel().getSelectedItem());
            updateOrdersListView();
        });
    }
     
    private void initListView() {
        ObservableList<ProductListItem> ol = ordersListView.getItems();
        for (Produkti product: orderedProducts)
            ol.add(new ProductListItem(product));
    }

    @FXML
    private void onSendOrder(ActionEvent event) {
        Porosia porosia = new Porosia();
        List<PorosiaProdukti> ppList = porosia.getPorosiaProduktiList();
        
        if (orderedProducts.isEmpty()) {
            Alerts.info("Empty cart!");
            return;
        }
        
        for (Produkti p: orderedProducts) {
            PorosiaProdukti pp = new PorosiaProdukti();
            pp.setProduktiId(p.getProduktId());
            ppList.add(pp);
        }
        
        try {
            request.create(
                    Porosia.class, 
                    porosia, 
                    path("(businesses)/{}/(users)/{}/(orders)", business.getBiznesId(), context.getId())
            );
            DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            Alerts.info("Order sent successfully!");
        } catch (RequestException ex) {
            Alerts.info(ex);
        }
    }
    
    private class ProductListItem extends BorderPane {
    
        private Produkti product;
        private Button removeBtn;

        public ProductListItem(Produkti product) {
               
            this.product = product;
            removeBtn = new Button("Remove");
            removeBtn.setOnAction(event -> removeProduct());
                        
            this.setLeft(new Label(this.product.getEmri()));
            this.setRight(removeBtn);
        }
        
        private void removeProduct() {
            orderedProducts.remove(product);
            updateOrdersListView();
        }
    }
    
}
