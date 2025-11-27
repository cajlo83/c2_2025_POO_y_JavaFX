package c2_2025_clase22_practica7;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Usuario
 */
public class BuscadorContactosController implements Initializable {

    // ******* atributos ******* 
    private ObservableList<Contacto> listaContactos; //observablelist hace que este sincronizada la lista visual con la lista en memoria
    private ObservableList<Contacto> listaFiltrada; //observablelist hace que este sincronizada la lista visual con la lista en memoria
    private Archivo archivo = new Archivo(".\\BuscadorContactosArchivo.dat");

    
    
    
    
    
    
    // ******* objetos desde fxml ******* 
    @FXML
    private Label lblBusqueda;

    @FXML
    private Label lblAgregar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtBusqueda;

    @FXML
    private TableView<Contacto> tvLista;

    @FXML
    private TableColumn<Contacto, String> colNombre;

    @FXML
    private TableColumn<Contacto, String> colApellido;

    @FXML
    private TableColumn<Contacto, Integer> colTelefono;

    
    
    
    
    // ******* inicializacion ******* 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //inicializar lista filtrada
        listaFiltrada = observableArrayList();
        
        //configuracion de columnas para que sepan que parte del objeto
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        //cargar archivo e inicializar listacontactos como observable
        try {
            Object cargado = archivo.cargar();

            if (cargado != null) {
                listaContactos = observableArrayList((List<Contacto>) cargado);
            } else {
                listaContactos = observableArrayList();
            }

        } catch (Exception ex) {
            listaContactos = observableArrayList();
            lblBusqueda.setText("Error cargando archivo: " + ex.getMessage());
        }

        // Conectar lista con TableView
        tvLista.setItems(listaContactos);
    }

    
    
    
    
    // ******* funciones ******* 
    private boolean validacionesOK() {
        String nombreTexto = txtNombre.getText();
        String apellidoTexto = txtApellido.getText();
        String telefonoTexto = txtTelefono.getText();

        // nombre
        if (nombreTexto == null || nombreTexto.isBlank()) {
            lblAgregar.setText("Nombre incorrecto");
            return false;
        }

        // apellido
        if (apellidoTexto == null || apellidoTexto.isBlank()) {
            lblAgregar.setText("Apellido incorrecto");
            return false;
        }

        // txtTelefono numerico
        Integer telefono;
        try {
            telefono = Integer.parseInt(telefonoTexto);
        } catch (NumberFormatException e) {
            lblAgregar.setText("El telefono debe ser un número");
            return false;
        }

        return true;
    }

    @FXML
    private void agregarContacto() {

        // validaciones
        if ( validacionesOK() ) {

            // crear objeto 
            Contacto p = new Contacto(txtNombre.getText(), txtApellido.getText(), Integer.parseInt(txtTelefono.getText()));
            //agregar a la lista
            listaContactos.add(p);

            // Guardar
            try {
                archivo.guardar(new ArrayList<>(listaContactos));
                lblAgregar.setText("contacto agregado correctamente");
                VentanaParaAvisos.mostrar("se actualizo el archivo", 200, 80);
            } catch (Exception ex) {
                lblAgregar.setText("Error al guardar el archivo");
                VentanaParaAvisos.mostrar("Error al guardar el archivo", 200, 80);
            }

        }else{
                VentanaParaAvisos.mostrar("revisar campos", 200, 80);
            
        }

    }
    
    // se va a ejecutar cada vez que se levante una tecla mientras se hace foco en txtBusqueda
    @FXML
    private void filtrar(){
        String buscado = txtBusqueda.getText().toLowerCase();
        
        listaFiltrada.clear();
        if (buscado.isBlank()){ //texto vacio => se muestra lista guardada
            tvLista.setItems(listaContactos);
            
        }else{ //texto no vacio => se muestra lista filtrada
            for ( Contacto c : listaContactos){
                if ( c.getApellido().toLowerCase().contains(buscado) ){
                    listaFiltrada.add(c);
                }
            }
            
            tvLista.setItems(listaFiltrada);
            
            if( listaFiltrada.isEmpty() ){ //lista filtrada vacia => activar label
                lblBusqueda.setText("no hay coincidencias");
            }else{ //lista filtrada no vacia => vaciar label
                lblBusqueda.setText("");
            }
            
            
        }
    }
    

}
