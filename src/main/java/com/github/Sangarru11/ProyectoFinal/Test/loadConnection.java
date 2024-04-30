package com.github.Sangarru11.ProyectoFinal.Test;

import com.github.Sangarru11.ProyectoFinal.Utils.XMLManager;
import com.github.Sangarru11.ProyectoFinal.model.Connection.ConnectionProperties;

public class loadConnection {
    public static void main(String[] args) {
        ConnectionProperties c = XMLManager.readXML(new ConnectionProperties(),"connection.xml");
        System.out.println(c);
    }
}
