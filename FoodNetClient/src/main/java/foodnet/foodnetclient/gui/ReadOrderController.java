/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Adresa;
import foodnet.foodnetclient.entities.Kategoria;
import foodnet.foodnetclient.entities.Porosia;
import foodnet.foodnetclient.entities.PorosiaProdukti;
import foodnet.foodnetclient.entities.Produkti;
import foodnet.foodnetclient.entities.Useri;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class ReadOrderController extends DynamicController implements Initializable {

    @FXML
    private TableView<Produkti> ordersTable;
    @FXML
    private TableColumn<Produkti, String> nameColumn;
    @FXML
    private TableColumn<Produkti, String> priceColumn;
    @FXML
    private TableColumn<Produkti, Kategoria> categoryColumn;
    @FXML
    private Label fullnameLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label roadLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private TextField totalField;
    
    private Porosia order;
    private DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
        order = context.getOrder();
        initTable();
        initUserInfo();
    }
    
    private void initTable() {
        ordersTable.setEditable(false);
        
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("emri"));
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("cmimi"));
        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("emriKategoris"));
        
        List<Produkti> productList = new ArrayList<>();
        for (PorosiaProdukti pp: order.getPorosiaProduktiList())
            productList.add(pp.getProdukti());

        ObservableList<Produkti> ol = FXCollections.observableArrayList();
        ol.addAll(productList);
        ordersTable.setItems(ol);
        
        double total = 0;
        for (Produkti p: productList)
            total += p.getCmimi().doubleValue();
        
        totalField.setText(df.format(total));
    }
    
    private void initUserInfo() {
        Useri user = order.getUserId();
        fullnameLabel.setText(user.getEmri() + " " + user.getMbiemri());
        phoneNumberLabel.setText(user.getTelefoniList().get(0).getNumri());
        Adresa address = user.getAdresaList().get(0);
        cityLabel.setText(address.getQyteti());
        roadLabel.setText(address.getRruga());   
    }
    
    
}
