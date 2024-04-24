package com.github.Sangarru11.ProyectoFinal.model.DAO;

import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;

public class CustomersDAO implements DAO<Customers,String> {
private static final String FINDBYID = "SELECT * FROM customers WHERE idCustomer = ?";
}
