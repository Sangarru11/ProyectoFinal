package com.github.Sangarru11.ProyectoFinal.View;
import com.github.Sangarru11.ProyectoFinal.App;

import com.github.Sangarru11.ProyectoFinal.model.DAO.EmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends Controller implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @Override
    public void onOpen(Object input) throws IOException {

    }

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

    @Override
    public void onClose(Object output) {
        // Método para manejar acciones cuando se cierra la ventana.
        // No se realiza ninguna acción específica con el parámetro de salida en este método.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Método para inicializar la ventana.
        // No se realiza ninguna acción específica durante la inicialización en este método.
    }

    /**
     * Método para cargar una vista de FXML.
     * @param scenes La escena que se cargará.
     * @return Un objeto View que contiene la escena y su controlador.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene = p;
        view.controller = c;
        return view;
    }

    /**
     * Método para cerrar la aplicación.
     */
    @FXML
    private void closeApp() {
        System.exit(0);
    }

    /**
     * Método para manejar el inicio de sesión.
     * Se valida el nombre de usuario y la contraseña y se cambia a la escena correspondiente.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void Login() throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        Employee employee = EmployeeDAO.build().findByName(username);

        if (employee != null) {
            if (password.equals(employee.getPassword())) {
                if (employee.isAdmin()) {
                    changeScene(Scenes.AdminPanel, null);
                } else {
                    changeScene(Scenes.PrinPanel, null);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Contraseña inválida.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nombre de usuario inválido.");
            alert.show();
        }
    }

    /**
     * Método para cambiar a la escena del registro de usuarios.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void Register() throws IOException {
        changeScene(Scenes.REGISTERPANEL, null);
    }
}
