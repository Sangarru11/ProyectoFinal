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
    @FXML
    public void CreateRepair() throws IOException{
        String currentDate = LocalDate.now().toString();
        String status = "Sin empezar";
        String description = txtDescription.getText();
        String platenumber = txtPlateNumber.getText();
        RepairsDAO repairsDAO = RepairsDAO.build();
        Repairs newRepair = new Repairs();
        newRepair.setPlateNumber(platenumber);
        newRepair.setDescription(description);
        newRepair.setDate(currentDate);
        newRepair.setStatus(status);
        Repairs savedRepair = repairsDAO.save(newRepair);
        if (savedRepair != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Se ha creado una nueva reparación");
            alert.show();
        }
    }
    @FXML
    public void changeAdminPanelController() throws IOException{
        changeScene(Scenes.AdminPanel,null);
    }
}
