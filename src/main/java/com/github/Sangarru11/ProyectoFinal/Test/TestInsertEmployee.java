package com.github.Sangarru11.ProyectoFinal.Test;

import com.github.Sangarru11.ProyectoFinal.model.DAO.EmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;

public class TestInsertEmployee {

    public static void main(String[] args) {

        Employee employee = new Employee();
        employee.setIdEmployee("1");
        employee.setDNI("12345678A");
        employee.setName("Juan Perez");
        employee.setPassword("1234");
        employee.setPlateNumber("123ABC");

        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.save(employee);
    }
}
