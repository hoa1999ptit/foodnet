package foodnet.foodnetclient;

import foodnet.foodnetclient.entities.Useri;
import foodnet.foodnetclient.gui.FXMLFiles;
import foodnet.foodnetclient.http.RaportRequest;
import foodnet.foodnetclient.http.Request;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    public static int WIDTH = 1280;
    public static int HEIGHT = 720;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXMLFiles.BASE));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setTitle("FoodNet");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
    private static Map<String, Object> createTestData() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("username", "UserOneChanged");
        mp.put("email", "UserOne@foodnet.org");
        mp.put("emri", "UserOneFS");
        mp.put("mbiemri", "UserOneLS");
        mp.put("passwordi", "password");
        mp.put("qyteti", "Prishtine");
        mp.put("numri", "+37745111111");
        return mp;
    }
    
    private static Map<String, Object> updateTestData() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("userId", 35);
        mp.put("username", "UserOneChanged");
        mp.put("email", "UserOne@foodnet.org");
        mp.put("emri", "UserOneFS");
        mp.put("mbiemri", "UserOneLS");
        mp.put("passwordi", "password");
        mp.put("qyteti", "Prishtine");
        mp.put("numri", "+37745111111");
        return mp;
    }

}
