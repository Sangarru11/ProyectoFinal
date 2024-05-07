package com.github.Sangarru11.ProyectoFinal.View;
import com.github.Sangarru11.ProyectoFinal.App;

import com.github.Sangarru11.ProyectoFinal.model.DAO.EmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.DAO.RepairsDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import com.github.Sangarru11.ProyectoFinal.model.entity.Mechanic;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainController extends Controller implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

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
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        System.out.println(url);
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
                System.out.println(App.stage);
                System.out.println(Scenes.PrinPanel);
                changeScene(Scenes.PrinPanel, null);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Password");
                alert.show();
            }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Username");
                alert.show();
        }
    }
}
