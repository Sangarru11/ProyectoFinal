package com.github.Sangarru11.ProyectoFinal.model.entity;

import java.util.List;
import java.util.Objects;

public class Employee {
    private int idEmployee;
    private List<Repairs> _repairs;
    private String name;
    private String DNI;
    private String password;
    private boolean isAdmin;

    public Employee() {
    }

    public Employee(int idEmployee, List<Repairs> _repairs, String name, String DNI, String password, boolean isAdmin) {
        this.idEmployee = idEmployee;
        this._repairs = _repairs;
        this.name = name;
        this.DNI = DNI;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return isAdmin == employee.isAdmin && Objects.equals(idEmployee, employee.idEmployee) && Objects.equals(_repairs, employee._repairs) && Objects.equals(name, employee.name) && Objects.equals(DNI, employee.DNI) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, _repairs, name, DNI, password, isAdmin);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee='" + idEmployee + '\'' +
                ", _repairs=" + _repairs +
                ", name='" + name + '\'' +
                ", DNI='" + DNI + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
