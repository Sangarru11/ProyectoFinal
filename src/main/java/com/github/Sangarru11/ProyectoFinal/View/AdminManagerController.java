package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.model.DAO.EmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminManagerController extends Controller implements Initializable {
    @FXML
    private ComboBox<String> EmployeeComboBox;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    /**
     * Método para inicializar la ventana.
     * Se carga la lista de empleados y se establece en el ComboBox.
     *
     * @param url            La URL utilizada para resolver rutas relativas para el objeto raíz, o null si no se ha proporcionado ninguna URL.
     * @param resourceBundle El recurso que se utiliza para localizar el objeto raíz, o null si no se ha proporcionado ningún recurso.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Employee> employees = EmployeeDAO.build().findbyAll();
        List<String> employeeNames = employees.stream().map(Employee::getName).collect(Collectors.toList());
        EmployeeComboBox.setItems(FXCollections.observableArrayList(employeeNames));
    }

    /**
     * Método para asignar un empleado como administrador.
     * Se selecciona el nombre del empleado del ComboBox, se busca en la base de datos y se actualiza su estado de administrador.
     */
    @FXML
    private void AssignAdmin() {
        String employeeName = EmployeeComboBox.getValue();
        EmployeeDAO employeeDAO = EmployeeDAO.build();
        Employee selectedEmployee = employeeDAO.findByName(employeeName);

        if (selectedEmployee != null) {
            selectedEmployee.setAdmin(true);
            try {
                employeeDAO.updateAdminStatus(selectedEmployee.getIdEmployee(), true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El empleado ahora es administrador");
                alert.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Método para remover el estado de administrador de un empleado.
     * Se selecciona el nombre del empleado del ComboBox, se busca en la base de datos y se actualiza su estado de administrador.
     */
    @FXML
    private void RemoveAdmin() {
        String employeeName = EmployeeComboBox.getValue();
        EmployeeDAO employeeDAO = EmployeeDAO.build();
        Employee selectedEmployee = employeeDAO.findByName(employeeName);

        if (selectedEmployee != null) {
            selectedEmployee.setAdmin(false);
            try {
                employeeDAO.updateAdminStatus(selectedEmployee.getIdEmployee(), false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El empleado ya no es administrador");
                alert.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Método para eliminar un empleado.
     * Se selecciona el nombre del empleado del ComboBox, se busca en la base de datos y se elimina.
     */
    @FXML
    private void RemoveEmployee() {
        String employeeName = EmployeeComboBox.getValue();
        EmployeeDAO employeeDAO = EmployeeDAO.build();
        Employee selectedEmployee = employeeDAO.findByName(employeeName);

        if (selectedEmployee != null) {
            try {
                employeeDAO.delete(selectedEmployee);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El empleado ha sido eliminado correctamente");
                alert.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Método para volver al panel de administración.
     *
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    private void ReturnAdminPanel() throws IOException {
        AssignRepairsPanelController.changeScene(Scenes.AdminPanel, null);
    }
}