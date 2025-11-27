package c2_2025_clase22_practica7;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VentanaRescataDatos {

    private Contacto resultado = null;

    public Contacto mostrar() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ingresar nuevo contacto");
        window.setMinWidth(350);

        Label lblNombre = new Label("Nombre:");
        Label lblApellido = new Label("Apellido:");
        Label lblTelefono = new Label("Teléfono:");

        TextField txtNombre = new TextField();
        TextField txtApellido = new TextField();
        TextField txtTelefono = new TextField();

        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");

        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");

        btnCancelar.setOnAction(e -> window.close());

        btnAceptar.setOnAction(e -> {
            String n = txtNombre.getText().trim();
            String a = txtApellido.getText().trim();
            String t = txtTelefono.getText().trim();

            if (n.isEmpty() || a.isEmpty() || t.isEmpty()) {
                lblError.setText("Debe completar todos los campos");
                return;
            }

            // si querés validación de teléfono: solo números
            if (!t.matches("\\d+")) {
                lblError.setText("El teléfono debe contener solo números");
                return;
            }

            resultado = new Contacto(n, a, t);
            window.close();
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(lblNombre, 0, 0);
        grid.add(txtNombre, 1, 0);

        grid.add(lblApellido, 0, 1);
        grid.add(txtApellido, 1, 1);

        grid.add(lblTelefono, 0, 2);
        grid.add(txtTelefono, 1, 2);

        grid.add(lblError, 1, 3);

        HBox botones = new HBox(10, btnAceptar, btnCancelar);
        grid.add(botones, 1, 4);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

        return resultado;
    }

    // ----------------------------
    // Clase modelo ejemplo
    // ----------------------------
    public static class Contacto {
        private String nombre;
        private String apellido;
        private String telefono;

        public Contacto(String nombre, String apellido, String telefono) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
        }

        public String getNombre() { return nombre; }
        public String getApellido() { return apellido; }
        public String getTelefono() { return telefono; }
    }
}
