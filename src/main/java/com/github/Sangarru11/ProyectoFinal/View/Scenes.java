package com.github.Sangarru11.ProyectoFinal.View;

public enum Scenes {
    REGISTERPANEL("View/RegisterPanel.fxml"),
    MAIN("View/main.fxml"),
    PrinPanel("View/PrincipalPanel.fxml"),
    AddCustomersPanel("View/AddCustomers.fxml"),
    MechanicInfo("View/MechanicInfo.fxml"),
    AssingRepairs("View/AssingRepairs.fxml"),
    SelectCustomer("View/SelectCustomer.fxml"),
    SelectMechanic("View/SelectMechanic.fxml"),
    AdminPanel("View/AdminPanel.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }

}
