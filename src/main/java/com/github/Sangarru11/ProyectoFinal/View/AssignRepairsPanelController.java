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

    }

    /**
     * Método para inicializar la ventana.
     * Se cargan todas las reparaciones y empleados de la base de datos y se establecen en los ComboBoxes correspondientes.
     * @param url La URL utilizada para resolver rutas relativas para el objeto raíz, o null si no se ha proporcionado ninguna URL.
     * @param resourceBundle El recurso que se utiliza para localizar el objeto raíz, o null si no se ha proporcionado ningún recurso.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Repairs> repairs = RepairsDAO.build().findbyAll();
        List<String> RepairsID = repairs.stream().map(repair -> String.valueOf(repair.getIdRepair())).collect(Collectors.toList());
        RepairsComboBox.setItems(FXCollections.observableArrayList(RepairsID));

        List<Employee> employees = EmployeeDAO.build().findbyAll();
        List<String> CustomersNames = employees.stream().map(Employee::getName).collect(Collectors.toList());
        CustomersComboBox.setItems(FXCollections.observableArrayList(CustomersNames));
    }

    /**
     * Método para asignar una reparación a un empleado.
     * Se seleccionan los valores de los ComboBoxes y se realiza la asignación en la base de datos.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    private void AssignRepairs() throws IOException {
        String IdRepair = RepairsComboBox.getValue();
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
            alert.setContentText("No se ha seleccionado nada en la base de datos.");
            alert.show();
        }
    }

    /**
     * Método para volver al panel de administración.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void BackAdminPanel() throws IOException {
        changeScene(Scenes.AdminPanel, null);
    }
}