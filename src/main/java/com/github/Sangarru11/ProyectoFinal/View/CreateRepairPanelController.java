package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.model.DAO.RepairsDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.github.Sangarru11.ProyectoFinal.View.AddCustomersController.changeScene;

public class CreateRepairPanelController extends Controller implements Initializable {
    @FXML
    private TextField txtPlateNumber;
    @FXML
    private TextField txtDescription;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Método para crear una nueva reparación.
     * Se recoge la información de los campos de texto y se guarda una nueva reparación en la base de datos.
     * Si la reparación ya existe, se muestra una alerta de error.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void CreateRepair() throws IOException {
        Repairs newRepair = new Repairs();
        String currentDate = LocalDate.now().toString();
        newRepair.setDate(currentDate);
        String status = "Sin empezar";
        newRepair.setStatus(status);

        String description = txtDescription.getText();
        newRepair.setDescription(description);

        String PlateNumber = txtPlateNumber.getText();
        newRepair.setPlateNumber(PlateNumber);

        Repairs repair = RepairsDAO.build().findByPlateNumber(PlateNumber);
        if (repair != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta reparación ya existe en la base de datos.");
            alert.show();
            return;
        }

        RepairsDAO.build().save(newRepair);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Se ha creado una nueva reparación.");
        alert.show();

        changeScene(Scenes.AddCustomersPanel, PlateNumber);
    }

    /**
     * Método para cambiar a la escena del panel de administración.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @FXML
    public void changeAdminPanelController() throws IOException {
        changeScene(Scenes.AdminPanel, null);
    }
}