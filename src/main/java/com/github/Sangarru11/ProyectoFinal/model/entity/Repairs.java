package com.github.Sangarru11.ProyectoFinal.model.entity;

import java.util.List;
import java.util.Objects;

public class Repairs {

    private int idRepair;
    private List<Employee> employees;
    private String date;
    private String status;
    private String description;
    private String plateNumber;

    public Repairs() {
    }

    public Repairs(int idRepair, List<Employee> employees, String date, String status, String description, String plateNumber) {
        this.idRepair = idRepair;
        this.employees = employees;
        this.date = date;
        this.status = status;
        this.description = description;
        this.plateNumber = plateNumber;
    }

    public int getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(int idRepair) {
        this.idRepair = idRepair;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Repairs repairs = (Repairs) object;
        isEquals = Objects.equals(idRepair, repairs.idRepair) && Objects.equals(date, repairs.date) && Objects.equals(status, repairs.status) && Objects.equals(description, repairs.description) && Objects.equals(plateNumber, repairs.plateNumber);
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepair, date, status, description, plateNumber);
    }

    @Override
    public String toString() {
        return "Repairs{" +
                "idRepair='" + idRepair + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}