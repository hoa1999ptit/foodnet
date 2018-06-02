/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.Adresa;
import foodnet.foodnetclient.entities.AuthEntity;
import foodnet.foodnetclient.entities.Biznesi;
import foodnet.foodnetclient.entities.Favorite;
import foodnet.foodnetclient.entities.Kategoria;
import foodnet.foodnetclient.entities.Post;
import foodnet.foodnetclient.entities.Produkti;
import static foodnet.foodnetclient.gui.DynamicController.request;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class BusinessInfoController extends DynamicController implements Initializable {

    @FXML
    private ListView<PostListItem> postsListView;
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
    private Label nameField;
    @FXML
    private Label oraHapjesField;
    @FXML
    private Label oraMbylljesField;
    @FXML
    private Label cityField;
    @FXML
    private Label roadField;
    @FXML
    private Label phoneNumberField;
    @FXML
    private Pane ownerPane;
    @FXML
    private Pane usersPane;
    
    private boolean isOwner;
    
    private Biznesi business;
    @FXML
    private Button favoritesButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @Override
    public void init() {
        loadBusiness();
        setBusinessInfo();
        
        initTable();
        initListView();
    }
    
    private void loadBusiness() {
        AuthEntity authEntity = context.getAuthEntity();
        Biznesi b = null;
        
        try {
             b = request.get(Biznesi.class, path("(businesses)/{}", context.getBusinessId()), null);
        } catch (RequestException ex) {
             Alerts.info(ex);
        }
                
        if (b != null) {
            context.setBusiness(b);
            business = b;
            System.out.println("BUSINESS IS NOT NULL");
        }
        
        if (authEntity.getType() == AuthEntity.BUSINESS && authEntity.getId() == context.getBusinessId()) {
            usersPane.setVisible(false);
            isOwner = true;
        } else {
            ownerPane.setVisible(false);
            checkFavorite();
        }
    }
    
    private void checkFavorite() {
        try {
            Favorite f = request.get(
                    Favorite.class, 
                    path("(users)/{}/(businesses)/{}/(favorites)", context.getId(), business.getBiznesId()), 
                    null
            );
            if (f != null && f.getBiznesId() != null && f.getBiznesId().equals(business.getBiznesId()))
                favoritesButton.setVisible(false);
        } catch (RequestException ex) {
            Alerts.info("Could not check for favorite!");
        }
    }
    
    private void setBusinessInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        nameField.setText(business.getEmri());
        oraHapjesField.setText(sdf.format(business.getOraHapes()));
        oraMbylljesField.setText(sdf.format(business.getOraMbylljes()));
        
        Adresa address = business.getAdresaList().get(0);
        cityField.setText(address.getQyteti());
        roadField.setText(address.getRruga());
        phoneNumberField.setText(business.getTelefoniList().get(0).getNumri());
    }
    
    private void initTable() {
        productsTable.setEditable(false);
        
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
            context.setProduct(productsTable.getSelectionModel().getSelectedItem());
            DynamicController.setToCenter(root, FXMLFiles.EDIT_PRODUCT);
        });
        
    }
    
    private void initListView() {
        ObservableList<PostListItem> ol = postsListView.getItems();
        for (Post post: business.getPostList())
            ol.add(new PostListItem(post));
    }

    private class PostListItem extends BorderPane {
    
        private Post post;
        private Button editBtn;

        public PostListItem(Post post) {
               
            this.post = post;
            if (isOwner) {
                editBtn = new Button("Edit");
                editBtn.setOnAction(event -> editPost());
                this.setRight(editBtn);
            }
            
            initListItem();
            
            this.setLeft(new Label(this.post.getTitle()));
        }

        private void initListItem() {
            this.setOnMouseClicked((event) -> {
                if (event.getClickCount() == 2) {
                    context.setPost(
                        postsListView.getSelectionModel().getSelectedItem().getPost()
                    );
                    DynamicController.setToCenter(root, FXMLFiles.READ_POST);
                }
            });
        }
        
        private void editPost() {
            context.setPost(post);
            DynamicController.setToCenter(root, FXMLFiles.EDIT_POST);
        }
        
        public Post getPost() {
            return post;
        }
    }

    @FXML
    private void onEditBusiness(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.EDIT_BUSINESS);
    }

    @FXML
    private void onDeleteBusiness(ActionEvent event) {
        if (Alerts.confirm()) {
            try {
                request.delete(Biznesi.class, path("(businesses)/{}", business.getBiznesId()));
                Alerts.info("Account deleted successfully!");
                // LOGIN HERE
            } catch (RequestException ex) {
                Alerts.info("Could not delete business: " + ex.getMessage());
            }
        }
    }
    
    @FXML
    private void onCreateProduct(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.CREATE_PRODUCT);
    }
    
    @FXML
    private void onCreateNewPost(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.CREATE_POST);
    }

    @FXML
    private void onOrder(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.CREATE_ORDER);
    }

    @FXML
    private void onWriteFeedback(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.BUSINESS_FEEDBACK);
    }
 
    @FXML
    private void onReadFeedback(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.ALL_BUSINESS_FEEDBACK);
    }
    
    @FXML
    private void onReadOrders(ActionEvent event) {
        DynamicController.setToCenter(root, FXMLFiles.ORDERS);
    }
    
    @FXML
    private void onAddFavorite(ActionEvent event) {
        try {
            request.create(
                Favorite.class,
                new Favorite(),
                path("(users)/{userId}/(businesses)/{bizId}/(favorites)", context.getId(), business.getBiznesId())
            );
            favoritesButton.setVisible(false);
            Alerts.info("Favorite added!");
        } catch (RequestException ex) {
            Alerts.info("Could not remove business from favorites.");
        }
    }


}
