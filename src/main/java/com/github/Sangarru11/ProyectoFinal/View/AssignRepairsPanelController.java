package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.DAO.CustomersDAO;
import com.github.Sangarru11.ProyectoFinal.model.DAO.EmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.DAO.RepairEmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.DAO.RepairsDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AssignRepairsPanelController extends Controller implements Initializable {

    @FXML
    private ComboBox<String> RepairsComboBox;
    @FXML
    private ComboBox<String> CustomersComboBox;


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
        List<Repairs> repairs = RepairsDAO.build().findbyAll();
        List<String> RepairsID = repairs.stream().map(repair -> String.valueOf(repair.getIdRepair())).collect(Collectors.toList());
        RepairsComboBox.setItems(FXCollections.observableArrayList(RepairsID));
        List<Employee> employees = EmployeeDAO.build().findbyAll();
        List<String> CustomersNames = employees.stream().map(Employee::getName).collect(Collectors.toList());
        CustomersComboBox.setItems(FXCollections.observableArrayList(CustomersNames));
    }
    @FXML
    private void AssignRepairs() throws IOException {
        String IdRepair= RepairsComboBox.getValue();
        String EmployeeName = CustomersComboBox.getValue();
        Repairs repairs = RepairsDAO.build().findById(IdRepair);
        Employee employee = EmployeeDAO.build().findByName(EmployeeName);

        if (repairs != null && employee != null) {
            int idRepair = repairs.getIdRepair();
            int idEmployee = employee.getIdEmployee();
            RepairEmployeeDAO repairEmployeeDAO = new RepairEmployeeDAO();
            repairEmployeeDAO.assignEmployeeToRepair(idRepair, idEmployee);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No se ha seleccionado nada en la base de datos");
            alert.show();
        }
    }

    @FXML
    public void BackAdminPanel() throws IOException{
        changeScene(Scenes.AdminPanel,null);
    }
}