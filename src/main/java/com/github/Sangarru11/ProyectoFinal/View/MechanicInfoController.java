package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MechanicInfoController extends Controller implements Initializable {
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> columnIDMechanic;
    @FXML
    private TableColumn<Employee, String> columnDNIMechanic;
    @FXML
    private TableColumn<Employee, String> columnNameMechanic;
    private ObservableList<Employee> employees;
    @Override
    public void onOpen(Object input) throws IOException {
        List<Employee> employees = (List<Employee>)input;
        this.employees = FXCollections.observableArrayList(employees);
        tableView.setItems(this.employees);
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
        tableView.setEditable(true);
        columnIDMechanic.setCellValueFactory(employees -> new SimpleStringProperty(employees.getValue().getIdEmployee()));
        columnDNIMechanic.setCellValueFactory(employees -> new SimpleStringProperty(employees.getValue().getDNI()));
        columnNameMechanic.setCellValueFactory(employees -> new SimpleStringProperty(employees.getValue().getName()));
    }
    @FXML
    public void BackAdminPanel()throws IOException{
        changeScene(Scenes.AdminPanel,null);
    }
}