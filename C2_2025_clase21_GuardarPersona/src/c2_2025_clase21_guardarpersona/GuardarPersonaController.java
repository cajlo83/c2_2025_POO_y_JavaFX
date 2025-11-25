/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2_2025_clase21_guardarpersona;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class GuardarPersonaController {

    private static boolean iniciado = false;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDNI;
    @FXML
    private Label lblEstado;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private void cerrar() {
        Stage stage = (Stage) rbMasculino.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void aceptar() {
        if (!validar()) {
            lblEstado.setText("Datos inválidos");
        } else {
            Persona p = new Persona(txtNombre.getText(), txtApellido.getText(), Integer.parseInt(txtDNI.getText()), obtenerGenero());

            guardarPersona(p);

            System.out.println("se guardo: " + p.toString());
            lblEstado.setText("se guardo: " + p.toString());

        }

    }

    //verifica si esta el archivo creado
    public void iniciar() {
        if (!iniciado) {
            // Si existe el archivo, cargarlo
            File archivo = new File("persona.dat");

            if (archivo.exists()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                    Persona persona = (Persona) ois.readObject();
                    ois.close();

                    // Precargar datos
                    txtNombre.setText(persona.getNombre());
                    txtApellido.setText(persona.getApellido());
                    txtDNI.setText(String.valueOf(persona.getDNI()));

                    if (persona.getGenero().equals("Masculino")) {
                        rbMasculino.setSelected(true);
                    } else if (persona.getGenero().equals("Femenino")) {
                        rbFemenino.setSelected(true);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            iniciado = true;
        }
    }

    private void guardarPersona(Persona p) {

        try {
            ArchivoPersona.guardar(p);
            System.out.println("Persona guardada.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String obtenerGenero() {
        String retorno;

        if (rbMasculino.isSelected()) {
            retorno = "Masculino";
        } else if (rbFemenino.isSelected()) {
            retorno = "Femenino";
        } else {
            retorno = "";
        }

        return retorno;
    }

    private boolean validar() {
        if (txtNombre.getText().isEmpty()) {
            return false;
        }
        if (txtApellido.getText().isEmpty()) {
            return false;
        }

        try {
            int dni = Integer.parseInt(txtDNI.getText());
            if (dni <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        if (obtenerGenero().isEmpty()) {
            return false;
        }

        return true;
    }

}
