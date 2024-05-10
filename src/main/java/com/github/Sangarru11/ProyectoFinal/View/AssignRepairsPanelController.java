package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AssignRepairsPanelController extends Controller implements Initializable {
    @FXML
    private TextField txtClient;
    @FXML
    private TextField txtMechanic;
    @FXML
    private MenuButton MenuButtonMechanic;
    @FXML
    private MenuButton MenuButtonClient;

    private ObservableList<Employee> employees;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void AssignRepairs() throws IOException{

    }
    @FXML
    public void BackAdminPanel() throws IOException{
        changeScene(Scenes.AdminPanel,null);
    }
}
