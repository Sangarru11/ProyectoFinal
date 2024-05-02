package com.github.Sangarru11.ProyectoFinal.View;

public enum Scenes {
    ROOT("View/layout.fxml"),
    MAIN("View/main.fxml"),
    PrinPanel("View/PrincipalPanel.fxml"),
    FORMAUTHOR("View/formAuthor.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }

}
