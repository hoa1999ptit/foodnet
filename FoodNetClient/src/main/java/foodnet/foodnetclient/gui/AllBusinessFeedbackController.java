/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.BusinessFeedback;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class AllBusinessFeedbackController extends DynamicController implements Initializable {

    @FXML
    private TableView<BusinessFeedback> feedbackTable;
    @FXML
    private TableColumn<BusinessFeedback, String> subjectColumn;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label contentLabel;
    @FXML
    private Label rateLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Pane businessFeedbackPane;
    
    private boolean bfPaneVisible;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void init() {
        businessFeedbackPane.setVisible(false);
        initTable();
    }
    
    private void initTable() {
        subjectColumn.setCellValueFactory(
                new PropertyValueFactory<>("subjekti"));

        feedbackTable.setItems(getFeedback());
        
        feedbackTable.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                loadBusinessFeedback(feedbackTable.getSelectionModel().getSelectedItem());
                if (!bfPaneVisible)
                    businessFeedbackPane.setVisible(true);
            }
        });
    }
    
    private ObservableList<BusinessFeedback> getFeedback() {
        ObservableList<BusinessFeedback> ol = FXCollections.observableArrayList();
        try {
            List<BusinessFeedback> fbList = request.getAll(
                    BusinessFeedback.class, path("(businesses)/{}/(feedback)", context.getId()), null);
            for (BusinessFeedback bf: fbList)
                if (bf.getUserId() != null)
                    ol.add(bf);
            
        } catch (RequestException ex) {
            Alerts.info("Could not retrieve feedbacks right now.");
        }
        return ol;
    }
    
    private void loadBusinessFeedback(BusinessFeedback bf) {
        subjectLabel.setText(bf.getSubjekti());
        contentLabel.setText(bf.getContent());
        rateLabel.setText(bf.getRate().getMeaning());
        userLabel.setText(bf.getUserId().toString());
    }
}
