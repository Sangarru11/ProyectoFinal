package com.github.Sangarru11.ProyectoFinal.View;
import com.github.Sangarru11.ProyectoFinal.App;
import com.github.Sangarru11.ProyectoFinal.model.DAO.CustomersDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainController extends Controller implements Initializable {

    @FXML
    private TableColumn<Customers,String> columnPassword;
    @FXML
    private TableColumn<Customers,String> columnName;

    private Controller centerController;

    private ObservableList<Customers> customers;
    @FXML
    private BorderPane borderPane;

    @Override
    public void onOpen(Object input) throws IOException {
        changeScene(Scenes.MAIN,null);
    }


    public void changeScene(Scenes scene,Object data) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    public void saveAuthor(Customers newAuthor){
        CustomersDAO.build().save(newAuthor);
        this.customers.add(newAuthor);

    }

    public void openModal(Scenes scene, String title,Controller parent, Object data) throws IOException {
        View view = loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        stage.setScene(_scene);
        view.controller.onOpen(parent);
        stage.showAndWait();
        //podríamos leer que ha devuelto...

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene=p;
        view.controller=c;
        return view;
    }

    @FXML
    private void closeApp(){
        System.exit(0);
    }

    @FXML
    private void goToAbout() throws IOException {
        System.out.println(Scenes.ABOUT);
        changeScene(Scenes.ABOUT,null);
    }

    @FXML
    private void addCustomers() throws IOException {

    }
}
