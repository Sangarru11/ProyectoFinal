package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.DAO.CustomersDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomersController extends Controller implements Initializable {
    @FXML
    private TextField txtNameCustomers;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtDNI;
    private String plateNumber;

    /**
     * Método que se ejecuta al abrir la ventana. Si el input es una cadena, se establece como el número de placa del cliente.
     * @param input El objeto de entrada que se pasa al abrir la ventana.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    public void onOpen(Object input) throws IOException {
        if (input instanceof String) {
            this.plateNumber = (String) input;
        }
    }

    /**
     * Método para manejar acciones cuando se cierra la ventana.
     * @param output El objeto de salida que se pasa al cerrar la ventana.
     */
    @Override
    public void onClose(Object output) {}

    /**
     * Método para inicializar la ventana.
     * @param url La URL utilizada para resolver rutas relativas para el objeto raíz, o null si no se ha proporcionado ninguna URL.
     * @param resourceBundle El recurso que se utiliza para localizar el objeto raíz, o null si no se ha proporcionado ningún recurso.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /**
     * Método para cambiar la escena actual.
     * @param scene La nueva escena a la que se cambiará.
     * @param data Los datos que se pasarán a la nueva escena.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public static void changeScene(Scenes scene, Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 1075, 720);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }

    /**
     * Método para añadir un nuevo cliente. Si el cliente ya existe en la base de datos, se muestra una alerta.
     * Si el cliente no existe, se guarda en la base de datos y se muestra una alerta de confirmación.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void addCustomers() throws IOException {
        Customers newCustomer = new Customers();
        String nameCustomers = txtNameCustomers.getText();
        newCustomer.setName(nameCustomers);
        String phoneNumber = txtPhoneNumber.getText();
        newCustomer.setPhoneNumber(phoneNumber);
        String dni = txtDNI.getText();
        newCustomer.setDNI(dni);
        newCustomer.setPlateNumber(plateNumber);

        Customers existingCustomer = CustomersDAO.build().findByDNI(dni);
        if (existingCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El cliente ya existe en la base de datos.");
            alert.show();
            return;
        }
        CustomersDAO.build().save(newCustomer);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("El cliente se ha añadido correctamente.");
        alert.show();
    }

    /**
     * Método para cambiar a la escena del panel de administración.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void changeAdminPanelController() throws IOException {
        changeScene(Scenes.AdminPanel, null);
    }
}
