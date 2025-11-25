
package c2_2025_clase21_guardarpersona;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class C2_2025_clase21_GuardarPersona extends Application {

    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuardarPersonaFX.fxml"));
        Parent root = loader.load();

        //  Obtener el controller
        GuardarPersonaController controller = loader.getController();

        //  Llamar cargarPersona
        controller.iniciar();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
