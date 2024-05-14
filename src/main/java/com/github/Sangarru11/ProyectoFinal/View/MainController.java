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

    public static void changeScene(Scenes scene,Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 1075, 720);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }

    @Override
    public void onClose(Object output) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene=p;
        view.controller=c;
        return view;
    }

    @FXML
    private void closeApp(){
        System.exit(0);
    }

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
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Contraseña Invalida");
                alert.show();
            }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nombre de usuario invalido");
                alert.show();
        }
    }
    @FXML
    public void Register() throws IOException {
        changeScene(Scenes.REGISTERPANEL, null);
    }
}
