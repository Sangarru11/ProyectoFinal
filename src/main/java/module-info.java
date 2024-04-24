module com.github.Sangarru11.ProyectoFinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;

    opens com.github.Sangarru11.ProyectoFinal to javafx.fxml;
    exports com.github.Sangarru11.ProyectoFinal;
}
