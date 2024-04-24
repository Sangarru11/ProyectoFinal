package com.github.Sangarru11.ProyectoFinal.model.entity;

import java.util.Objects;

public class Employee {
    private String idEmployee;
    private String name;
    private String DNI;

    public Employee() {
    }

    public Employee(String idEmployee, String name, String DNI) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.DNI = DNI;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(idEmployee, employee.idEmployee) && Objects.equals(name, employee.name) && Objects.equals(DNI, employee.DNI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, name, DNI);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee='" + idEmployee + '\'' +
                ", name='" + name + '\'' +
                ", DNI='" + DNI + '\'' +
                '}';
    }
}
