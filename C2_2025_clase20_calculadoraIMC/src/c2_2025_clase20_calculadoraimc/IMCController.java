
package c2_2025_clase20_calculadoraimc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class IMCController {
    
     @FXML
     private TextField txtPeso;
     @FXML
     private TextField txtAltura;
     @FXML
     private Label lblResultado;
     @FXML
     private void calcular(){
         try {
             double a = Double.parseDouble(txtPeso.getText());
             double b = Double.parseDouble(txtAltura.getText());
             double calculo = a / (b*b);
             lblResultado.setText("tu IMC es : "+ calculo + "\nnecesita mas ensayo y error para agregar las categorias\nmis medidas resultan en 38 y tengo sobrepeso, si te sirve");
         } catch (NumberFormatException e){
             lblResultado.setText("ingresar solo numeros");
         }
     }
    
}
