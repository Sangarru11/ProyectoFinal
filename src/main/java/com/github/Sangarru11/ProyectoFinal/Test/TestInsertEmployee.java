package com.github.Sangarru11.ProyectoFinal.Test;

import com.github.Sangarru11.ProyectoFinal.model.DAO.EmployeeDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;

public class TestInsertEmployee {

    public static void main(String[] args) {

        Employee employee = new Employee();
        employee.setIdEmployee(1);
        employee.setDNI("75829349X");
        employee.setName("Pepillo Fernandez");
        employee.setPassword("2222");
        employee.setAdmin(false);

        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.save(employee);
    }
}
