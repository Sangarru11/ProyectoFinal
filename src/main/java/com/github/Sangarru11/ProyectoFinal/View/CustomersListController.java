package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.model.DAO.CustomersDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;
import javafx.beans.property.SimpleIntegerProperty;
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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.github.Sangarru11.ProyectoFinal.View.AddCustomersController.changeScene;

public class CustomersListController extends Controller implements Initializable {
    @FXML
    private TableView <Customers> tableView;
    @FXML
    private TableColumn<Customers, String> columnClientId;
    @FXML
    private TableColumn<Customers, String> columnClientName;
    @FXML
    private TableColumn<Customers, String> columnPhoneNumber;
    @FXML
    private TableColumn<Customers, String> columnDNI;
    @FXML
    private TableColumn<Customers, String> columnPlateNumber;
    private ObservableList<Customers> customers;
    @Override
    public void onOpen(Object input) throws IOException {
        List<Customers> customers = CustomersDAO.build().findbyAll();
        this.customers = FXCollections.observableArrayList(customers);
        tableView.setItems(this.customers);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        columnClientId.setCellValueFactory(customers -> new SimpleIntegerProperty(customers.getValue().getIdCustomer()).asObject().asString());
        columnClientId.setCellFactory(TextFieldTableCell.forTableColumn());
        columnClientName.setCellValueFactory(repairs-> new SimpleStringProperty(repairs.getValue().getName()));
        columnClientName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPhoneNumber.setCellValueFactory(repairs-> new SimpleStringProperty(repairs.getValue().getPhoneNumber()));
        columnPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDNI.setCellValueFactory(repairs-> new SimpleStringProperty(repairs.getValue().getDNI()));
        columnDNI.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPlateNumber.setCellValueFactory(repairs-> new SimpleStringProperty(repairs.getValue().getPlateNumber()));
        columnPlateNumber.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    @FXML
    public void DeleteCustomer(){
        Customers customer = tableView.getSelectionModel().getSelectedItem();
        if (customer != null) {
            customers.remove(customer);
            CustomersDAO.build().delete(customer);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona un cliente para eliminar.");
            alert.show();
        }
    }

    @FXML
    public void changeAdminPanelController() throws IOException{
        changeScene(Scenes.AdminPanel,null);
    }
}
