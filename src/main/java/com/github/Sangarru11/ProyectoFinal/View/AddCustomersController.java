package com.github.Sangarru11.ProyectoFinal.View;

import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.DAO.CustomersDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomersController extends Controller implements Initializable {
    @FXML
    private TextField txtNameCustomers;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtDNI;
    @FXML
    private TextField txtPlateNumber;
    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public static void changeScene(Scenes scene,Object data) throws IOException {
        View view = MainController.loadFXML(scene);
        Scene _scene = new Scene(view.scene, 1075, 720);
        App.currentController = view.controller;
        App.currentController.onOpen(data);
        App.stage.setScene(_scene);
        App.stage.show();
    }
    @FXML
    public void addCustomers() throws IOException {
        Customers newCustomer = new Customers();
        String NameCustomers = txtNameCustomers.getText();
        newCustomer.setName(NameCustomers);
        String PhoneNumber = txtPhoneNumber.getText();
        newCustomer.setPhoneNumber(PhoneNumber);
        String DNI = txtDNI.getText();
        newCustomer.setDNI(DNI);
        String PlateNumber = txtPlateNumber.getText();
        newCustomer.setPlateNumber(PlateNumber);

        Customers customers = CustomersDAO.build().findByDNI(DNI);
        if (customers != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El cliente ya existe en la base de datos.");
            alert.show();
            return;
        }
        CustomersDAO.build().save(newCustomer);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("El cliente se ha añadido correctamente.");
        alert.show();
    }
    @FXML
    public void changeAdminPanelController() throws IOException{
        changeScene(Scenes.AdminPanel,null);
    }
}
