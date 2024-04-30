package com.github.Sangarru11.ProyectoFinal.Test;

import com.github.Sangarru11.ProyectoFinal.Utils.XMLManager;
import com.github.Sangarru11.ProyectoFinal.model.Connection.ConnectionProperties;

public class testConnection {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","taller","root","root");
        XMLManager.writeXML(c,"connection.xml");
    }
}
