module com.github.Sangarru11.ProyectoFinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;

    opens com.github.Sangarru11.ProyectoFinal to javafx.fxml;
    opens com.github.Sangarru11.ProyectoFinal.View to javafx.fxml,java.xml.bind;

    exports com.github.Sangarru11.ProyectoFinal;
    exports com.github.Sangarru11.ProyectoFinal.View;
}
