/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.Context;
import foodnet.foodnetclient.http.RaportRequest;
import foodnet.foodnetclient.http.Request;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Kushtrim Hajrizi
 */
public abstract class DynamicController {
    
    protected static Request request;
    protected static RaportRequest raportRequest;
    protected static Context context;
    
    protected BorderPane root;
    
    static {
        request = new Request();
        raportRequest = new RaportRequest(request);
        context = new Context();
    }
    
    public void setRoot(BorderPane root) {
        this.root = root;
    }
    
    public void init() {}
    
    private static Pane loadFXML(BorderPane root, String fxmlName) {
        FXMLLoader loader = new FXMLLoader(fxmlName.getClass().getResource(fxmlName));
        try {
            Pane pane = (Pane) loader.load();
            DynamicController dc = (DynamicController) loader.getController();
            dc.setRoot(root);
            dc.init();
            return pane;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void setToCenter(BorderPane root, String fxmlName) {
        root.setCenter(loadFXML(root, fxmlName));
    }
    
    public static void setToLeft(BorderPane root, String fxmlName) {
        root.setLeft(loadFXML(root, fxmlName));
    }
    
    public static void setToRight(BorderPane root, String fxmlName) {
        root.setRight(loadFXML(root, fxmlName));
    }
    
}
