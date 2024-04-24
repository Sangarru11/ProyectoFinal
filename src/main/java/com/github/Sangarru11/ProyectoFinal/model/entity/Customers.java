package com.github.Sangarru11.ProyectoFinal.model.entity;

import java.util.Objects;

public class Customers {
    private String idCustomer;
    private String name;
    private String PhoneNumber;
    private String DNI;
    private String plateNumber;

    public Customers() {
    }

    public Customers(String idCustomer, String name, String phoneNumber, String DNI, String plateNumber) {
        this.idCustomer = idCustomer;
        this.name = name;
        PhoneNumber = phoneNumber;
        this.DNI = DNI;
        this.plateNumber = plateNumber;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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
        Customers customers = (Customers) o;
        return Objects.equals(idCustomer, customers.idCustomer) && Objects.equals(name, customers.name) && Objects.equals(PhoneNumber, customers.PhoneNumber) && Objects.equals(DNI, customers.DNI) && Objects.equals(plateNumber, customers.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, name, PhoneNumber, DNI, plateNumber);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "idCustomer='" + idCustomer + '\'' +
                ", name='" + name + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", DNI='" + DNI + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
