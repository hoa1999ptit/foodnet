/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class Alerts {
    public static void info(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.showAndWait();
    }
    
    public static void info(Exception ex) {
        info(ex.getMessage());
    }
    
    public static boolean confirm() {
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
