package com.github.Sangarru11.ProyectoFinal.Test;

import com.github.Sangarru11.ProyectoFinal.model.DAO.CustomersDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;

import java.util.ArrayList;

public class TestInsert {
    public static void main(String[] args) {
        Customers customer = new Customers();
        customer.setIdCustomer("1");
        customer.setDNI("12345678A");
        customer.setName("Juan Perez");
        customer.setPhoneNumber("123456789");
        customer.setPlateNumber("ABC1234");

        CustomersDAO customersDAO = new CustomersDAO();
        customersDAO.save(customer);
    }
}
