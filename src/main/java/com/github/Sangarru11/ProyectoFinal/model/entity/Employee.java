package com.github.Sangarru11.ProyectoFinal.model.entity;

import java.util.Objects;

public class Employee {
    private String idEmployee;
    private String name;
    private String DNI;
    private String password;
    private String plateNumber;

    public Employee() {

    }

    public Employee(String idEmployee, String name, String DNI, String password, String plateNumber) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.DNI = DNI;
        this.password = password;
        this.plateNumber = plateNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (this == object) isEquals = true;
        if (object == null || getClass() != object.getClass()) isEquals = false;
        Employee employee = (Employee) object;
        isEquals = Objects.equals(idEmployee, employee.idEmployee) && Objects.equals(name, employee.name) && Objects.equals(DNI, employee.DNI) && Objects.equals(password, employee.password) && Objects.equals(plateNumber, employee.plateNumber);
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, name, DNI, password, plateNumber);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee='" + idEmployee + '\'' +
                ", name='" + name + '\'' +
                ", DNI='" + DNI + '\'' +
                ", pasword='" + password + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
