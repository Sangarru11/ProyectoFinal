package com.github.Sangarru11.ProyectoFinal.model.entity;

import java.util.Objects;

public class Repairs {

    private String idRepair;
    private String description;
    private String date;
    private String status;
    private String plateNumber;

    public Repairs() {
    }

    public Repairs(String idRepair, String description, String date, String status, String plateNumber) {
        this.idRepair = idRepair;
        this.description = description;
        this.date = date;
        this.status = status;
        this.plateNumber = plateNumber;
    }

    public String getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(String idRepair) {
        this.idRepair = idRepair;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repairs repairs = (Repairs) o;
        return Objects.equals(idRepair, repairs.idRepair) && Objects.equals(description, repairs.description) && Objects.equals(date, repairs.date) && Objects.equals(status, repairs.status) && Objects.equals(plateNumber, repairs.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepair, description, date, status, plateNumber);
    }

    @Override
    public String toString() {
        return "Repairs{" +
                "idRepair='" + idRepair + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
