package com.github.Sangarru11.ProyectoFinal;

import com.github.Sangarru11.ProyectoFinal.View.Scenes;
import com.github.Sangarru11.ProyectoFinal.View.AppController;
import com.github.Sangarru11.ProyectoFinal.View.Controller;
import com.github.Sangarru11.ProyectoFinal.View.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static AppController currentController;

    //este el es primer método que se ejecuta al abrir la primera ventana
    @Override
    public void start(Stage stage) throws IOException {
        //view/layout.fxml
        View view = AppController.loadFXML(Scenes.ROOT);
        scene = new Scene(view.scene, 640, 480);
        currentController = (AppController) view.controller;
        currentController.onOpen(null);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        //  scene.setRoot(loadFXML(fxml));
    }


    public static void main(String[] args) {
        launch();
    }

}