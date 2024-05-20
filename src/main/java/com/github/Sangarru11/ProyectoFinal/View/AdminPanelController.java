package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.DAO.RepairsDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPanelController extends Controller implements Initializable {
    @FXML
    private TableView<Repairs> tableView;
    @FXML
    private TableColumn<Repairs, String> columnIDRepair;
    @FXML
    private TableColumn<Repairs, String> columnDescription;
    @FXML
    private TableColumn<Repairs, String> columnRepairDate;
    @FXML
    private TableColumn<Repairs, String> columnRepairState;
    @FXML
    private TableColumn<Repairs, String> columnPlateNumber;
    private ObservableList<Repairs> repairs;
    @Override
    public void onOpen(Object input) throws IOException {
        // Método que se ejecuta al abrir la ventana.
        // Se cargan todas las reparaciones de la base de datos y se establecen en la TableView.
        List<Repairs> repairs = RepairsDAO.build().findbyAll();
        this.repairs = FXCollections.observableArrayList(repairs);
        tableView.setItems(this.repairs);
    }

    @Override
    public void onClose(Object output) {
        // Método para manejar acciones cuando se cierra la ventana.
        // No se realiza ninguna acción específica con el parámetro de salida en este método.
    }

    /**
     * Método para cambiar la escena actual.
     * @param scene La nueva escena a la que se cambiará.
     * @param data Los datos que se pasarán a la nueva escena.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public static void changeScene(Scenes scene, Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 790, 690);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }

    /**
     * Método para inicializar la ventana.
     * Se configura la TableView para permitir la edición y se establecen las columnas.
     * @param url La URL utilizada para resolver rutas relativas para el objeto raíz, o null si no se ha proporcionado ninguna URL.
     * @param resourceBundle El recurso que se utiliza para localizar el objeto raíz, o null si no se ha proporcionado ningún recurso.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);

        tableView.setRowFactory(tv -> {
            TableRow<Repairs> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Repairs repair = tableView.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 3 && (!row.isEmpty())) {
                    Repairs repairs = row.getItem();
                    try {
                        changeScene(Scenes.MechanicInfo, repair.getEmployees());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });

        // Configura la columna de descripción
        columnDescription.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getDescription()));
        columnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDescription.setOnEditCommit(event -> {
            if (event.getNewValue().equals(event.getOldValue())) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Repairs repairs = event.getRowValue();
                repairs.setDescription(event.getNewValue());
                RepairsDAO.build().save(repairs);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Te has pasado del límite de caracteres.");
                alert.show();
            }
        });

        // Configura la columna de ID de reparación
        columnIDRepair.setCellValueFactory(repairs -> new SimpleIntegerProperty(repairs.getValue().getIdRepair()).asObject().asString());
        columnIDRepair.setCellFactory(TextFieldTableCell.forTableColumn());

        // Configura la columna de fecha de reparación
        columnRepairDate.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getDate()));
        columnRepairDate.setCellFactory(TextFieldTableCell.forTableColumn());
        columnRepairDate.setOnEditCommit(event -> {
            if (event.getNewValue().equals(event.getOldValue())) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Repairs repairs = event.getRowValue();
                repairs.setDate(event.getNewValue());
                RepairsDAO.build().save(repairs);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Te has pasado del límite de caracteres.");
                alert.show();
            }
        });

        // Configura la columna de estado de reparación
        columnRepairState.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getStatus()));
        columnRepairState.setCellFactory(TextFieldTableCell.forTableColumn());
        columnRepairState.setOnEditCommit(event -> {
            if (event.getNewValue().equals(event.getOldValue())) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Repairs repairs = event.getRowValue();
                repairs.setStatus(event.getNewValue());
                RepairsDAO.build().save(repairs);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Te has pasado del límite de caracteres.");
                alert.show();
            }
        });

        // Configura la columna de número de placa
        columnPlateNumber.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getPlateNumber()));
        columnPlateNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPlateNumber.setOnEditCommit(event -> {
            if (event.getNewValue().equals(event.getOldValue())) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Repairs repairs = event.getRowValue();
                repairs.setPlateNumber(event.getNewValue());
                RepairsDAO.build().save(repairs);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Te has pasado del límite de caracteres.");
                alert.show();
            }
        });
    }

    /**
     * Método para eliminar una reparación seleccionada en la TableView.
     * @throws SQLException Si ocurre un error en la eliminación de la reparación.
     */
    @FXML
    public void deleteRepair() throws SQLException {
        Repairs repair = tableView.getSelectionModel().getSelectedItem();
        if (repair != null) {
            repairs.remove(repair);
            RepairsDAO.build().delete(repair);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona una reparación para eliminar.");
            alert.show();
        }
    }

    /**
     * Método para cambiar a la escena de creación de reparaciones.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void CreateRepair() throws IOException {
        changeScene(Scenes.CreateRepair, null);
    }

    /**
     * Método para cambiar a la escena del panel de administración.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void setAdmin() throws IOException {
        changeScene(Scenes.AdminController, null);
    }

    /**
     * Método para volver a la escena de inicio de sesión.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void ReturnToLogin() throws IOException {
        changeScene(Scenes.MAIN, null);
    }

    /**
     * Método para cambiar a la escena de la lista de clientes.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void ChangeAddCustomer() throws IOException {
        changeScene(Scenes.CustomersListController, null);
    }

    /**
     * Método para cambiar a la escena de asignación de reparaciones.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void ChangeAssingRepairs() throws IOException {
        changeScene(Scenes.AssingRepairs, null);
    }
}