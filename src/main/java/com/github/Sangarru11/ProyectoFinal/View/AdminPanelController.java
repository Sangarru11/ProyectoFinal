package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.DAO.CustomersDAO;
import com.github.Sangarru11.ProyectoFinal.model.DAO.EmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.DAO.RepairsDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
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
    private TableColumn<Repairs, String> columnIdMechanic;
    @FXML
    private TableColumn<Repairs, String> columnPlateNumber;
    private ObservableList<Repairs> repairs;
    @Override
    public void onOpen(Object input) throws IOException {
        List<Repairs> repairs = RepairsDAO.build().findbyAll();
        this.repairs = FXCollections.observableArrayList(repairs);
        tableView.setItems(this.repairs);
    }

    @Override
    public void onClose(Object output) {

    }
    public static void changeScene(Scenes scene,Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 790, 690);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);

        tableView.setRowFactory(tv -> {
            TableRow<Repairs> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Repairs repair = tableView.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 3 && (! row.isEmpty()) ) {
                    Repairs repairs = row.getItem();
                    try {
                        changeScene(Scenes.MechanicInfo,repair.getEmployees());//panel que al clicar en la tabla cambia
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
            return row ;
        });
        columnIDRepair.setCellValueFactory(repairs-> new SimpleStringProperty(repairs.getValue().getIdRepair()));
        columnIDRepair.setCellFactory(TextFieldTableCell.forTableColumn());
        columnIDRepair.setOnEditCommit(event -> {
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
        columnDescription.setCellValueFactory(repairs-> new SimpleStringProperty(repairs.getValue().getDescription()));
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
        columnRepairDate.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getDate()));
        columnRepairDate.setCellFactory(TextFieldTableCell.forTableColumn());
        columnRepairDate.setOnEditCommit(event -> {
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
        columnRepairState.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getStatus()));
        columnRepairState.setCellFactory(TextFieldTableCell.forTableColumn());
        columnRepairState.setOnEditCommit(event -> {
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
        columnPlateNumber.setCellValueFactory(repairs -> new SimpleStringProperty(repairs.getValue().getPlateNumber()));
        columnPlateNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPlateNumber.setOnEditCommit(event -> {
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

    @FXML
    public void ReturnToLogin() throws IOException {
        changeScene(Scenes.MAIN,null);
    }
    @FXML
    public void ChangeAddCustomer() throws IOException{
        changeScene(Scenes.AddCustomersPanel,null);
    }
}
