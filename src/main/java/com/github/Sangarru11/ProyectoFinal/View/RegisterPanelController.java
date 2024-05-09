package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.DAO.EmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.jar.Attributes;

public class RegisterPanelController extends Controller implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtDNI;

    @Override
    public void onOpen(Object input) throws IOException {

    }
    public static void changeScene(Scenes scene,Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 640, 480);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }
    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void Register() throws IOException {
        Employee newEmployee = new Employee();
        String name = txtUsername.getText();
        newEmployee.setName(name);
        String password = txtPassword.getText();
        newEmployee.setPassword(password);
        String DNI = txtDNI.getText();
        newEmployee.setDNI(DNI);

        Employee employee = EmployeeDAO.build().findByName(name);
        if (employee != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El empleado ya existe en la base de datos");
            alert.show();
            return;
        }
        EmployeeDAO.build().save(newEmployee);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("El empleado se ha añadido correctamente");
        alert.show();
    }
    @FXML
    public void ReturnToLogin() throws IOException {
        changeScene(Scenes.MAIN,null);
    }
}
