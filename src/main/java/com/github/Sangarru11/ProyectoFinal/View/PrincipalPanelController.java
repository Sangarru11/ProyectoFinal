package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.model.DAO.RepairsDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalPanelController extends Controller implements Initializable {
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
    private TableColumn<Repairs, String> columnIdMechanic;
    @FXML
    private TableColumn<Repairs, String> columnPlateNumber;
    private ObservableList<Repairs> repairs;
    @Override
    public void onOpen(Object input) throws IOException {
        List<Repairs> repairs = RepairsDAO.build().findbyAll();
        System.out.println(repairs);
        this.repairs = FXCollections.observableArrayList(repairs);
        tableView.setItems(this.repairs);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        columnIDRepair.setCellValueFactory(repairs-> new SimpleStringProperty(repairs.getValue().getIdRepair()));
        columnDescription.setCellValueFactory(repairs-> new SimpleStringProperty(repairs.getValue().getDescription()));
        columnRepairDate.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getDate()));
        columnRepairState.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getStatus()));
        columnPlateNumber.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getPlateNumber()));
        columnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDescription.setOnEditCommit(event -> {
            if(event.getNewValue()== event.getOldValue()){
                return;
            }
            if(event.getNewValue().length()<=60) {
                Repairs repairs = event.getRowValue();
                repairs.setDescription(event.getNewValue());
                RepairsDAO.build().save(repairs);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Te has pasao!!!!!");
                alert.show();
            }
        });
    }
}