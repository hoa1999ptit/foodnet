/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.AuthEntity;
import foodnet.foodnetclient.entities.Biznesi;
import foodnet.foodnetclient.entities.CompactBusiness;
import foodnet.foodnetclient.entities.Favorite;
import static foodnet.foodnetclient.gui.DynamicController.request;
import foodnet.foodnetclient.http.ParametersGroup;
import foodnet.foodnetclient.http.Request;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class FoodnetsController extends DynamicController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private TableView<CompactBusiness> businessesTable;
    @FXML
    private TableColumn<CompactBusiness, String> emriColumn;
    @FXML
    private TableColumn<CompactBusiness, String> qytetiColumn;
    @FXML
    private ListView<BusinessListItem> favoritesListView;
    
    private String lastSearched;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
        initTableView();
        initListView();
    }
    
    private void initTableView() {
        businessesTable.setEditable(false);
        
        emriColumn.setCellValueFactory(
                new PropertyValueFactory<>("emri"));
        qytetiColumn.setCellValueFactory(
                new PropertyValueFactory<>("qyteti"));

        loadTableData(getBusinesses());
        
        businessesTable.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                context.setBusiness(
                        businessesTable.getSelectionModel().getSelectedItem());
                DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
            }
        });
    }
    
    private void loadTableData(ObservableList<CompactBusiness> data) {
        businessesTable.setItems(data);
        businessesTable.refresh();
    }
    
    private ObservableList<CompactBusiness> getBusinesses() {
        ObservableList<CompactBusiness> ol = FXCollections.observableArrayList();
        try {
            List<CompactBusiness> lst = request.getAll(
                    CompactBusiness.class, path("(businesses)/(compact)"), null);
            
            for (CompactBusiness cb: lst)
                if (cb.getBiznesiId() != null)
                    ol.add(cb);
        } catch (RequestException ex) {
            Alerts.info("Could not show businesses: " + ex.getMessage());
        }
        return ol;
    }
    

    
       
    private void initListView() {
        try {
            List<CompactBusiness> lst = request.getAll(
                    CompactBusiness.class, path("(businesses)/(users)/{userId}/(favorites)", context.getId()), null);
            
            ObservableList<BusinessListItem> favoriteBusinesses = favoritesListView.getItems();
            for (CompactBusiness cb: lst)
                if (cb.getBiznesiId() != null)
                    favoriteBusinesses.add(
                            new BusinessListItem(cb));
        } catch (RequestException ex) {
            Alerts.info("Could not retrieve businesses: " + ex.getMessage());
        }
    }

    @FXML
    private void onSearch(InputMethodEvent event) {
    }

    @FXML
    private void onSearchReleased(KeyEvent event) {
        String searchText = searchField.getText();
        if (searchText.equals(lastSearched))
            return;
        
        Thread th = new Thread(new SearchThread(searchText));
        th.start();
    }

    
    private class BusinessListItem extends BorderPane {
    
        private CompactBusiness business;
        private Button removeBtn;

        public BusinessListItem(CompactBusiness business) {
               
            this.business = business;
            removeBtn = new Button("Remove");
            removeBtn.setOnAction(event -> removeFromFavorites());
            
            initListItem();
            
            this.setLeft(new Label(this.business.getEmri()));
            this.setRight(removeBtn);
        }

        private void initListItem() {
            this.setOnMouseClicked((event) -> {
                if (event.getClickCount() == 2) {
                    context.setBusiness(
                        favoritesListView.getSelectionModel().getSelectedItem().getBusiness()
                    );
                    DynamicController.setToCenter(root, FXMLFiles.BUSINESS_INFO);
                }
            });
        }
        
        private void removeFromFavorites() {
            try {
                request.delete(
                        Favorite.class, 
                        path("(users)/{userId}/(businesses)/{bizId}/(favorites)", context.getId(), business.getBiznesiId())
                );
            } catch (RequestException ex) {
                Alerts.info("Could not remove business from favorites.");
                return;
            }
            favoritesListView.getItems().clear();
            initListView();
        }
        
        public CompactBusiness getBusiness() {
            return business;
        }
    }
    
    private class SearchThread implements Runnable {
        
        private String searchText;
        
        public SearchThread(String searchText) {
            this.searchText = searchText;
        }
        
        @Override
        public void run() {
            try {
                ParametersGroup pg = new ParametersGroup();
                pg.add("content", searchText);
                List<CompactBusiness> result = request.getAll(CompactBusiness.class, path("(search)"), pg);
            
                lastSearched = searchText;
                ObservableList<CompactBusiness> ol = FXCollections.observableArrayList();
                ol.addAll(result);
                loadTableData(ol);
            } catch (RequestException ex) {
                Alerts.info(ex);
            }
        }
    }
    
}
