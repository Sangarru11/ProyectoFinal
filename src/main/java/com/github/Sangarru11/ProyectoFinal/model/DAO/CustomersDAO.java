package com.github.Sangarru11.ProyectoFinal.model.DAO;

import com.github.Sangarru11.ProyectoFinal.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.ProyectoFinal.model.entity.Customers;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersDAO implements DAO<Customers,String> {
    private static final String FINDBYDNI = "SELECT c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber FROM customers AS c WHERE dni=?";
    private static final String FINDBYID = "SELECT c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber FROM customers AS c WHERE c.idCustomer = ?";
    private static final String FINDBYNAME = "SELECT c.idCustomer, c.dni, c.name, c.phoneNumber, c.plateNumber FROM customers WHERE name = ?";
    private static final String INSERT = "INSERT INTO customers (dni, name, phoneNumber, plateNumber) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE customers SET name=? WHERE idCustomer=?";
    private static final String DELETE = "DELETE FROM customers WHERE idCustomer=?";

    private Connection connection;

    public CustomersDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    @Override
    public Customers save(Customers entity) {
        Customers result = entity;
        if (entity != null) {
            String dni = entity.getDNI();
            if (dni != null) {
                Customers isInDataBase = findByDNI(dni);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, dni);
                        pst.setString(2, entity.getName());
                        pst.setString(3, entity.getPhoneNumber());
                        pst.setString(4, entity.getPlateNumber());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    }else{
                        try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                            pst.setString(1, entity.getName());
                            pst.setString(2, entity.getIdCustomer());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        return result;
    }

    @Override
    public Customers delete(Customers entity) {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setString(1, entity.getIdCustomer());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
    @Override
    public Customers findById(String key) {
        Customers result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)) {
            pst.setString(1, key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    Customers c = new Customers();
                    c.setIdCustomer(res.getString("idCustomer"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPhoneNumber(res.getString("phoneNumber"));
                    c.setPlateNumber(res.getString("plateNumber"));
                    result = c;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Repairs findByDate(String key) {
        return null;
    }

    @Override
    public Customers findByDNI(String key) {
        Customers result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYDNI)) {
            pst.setString(1, key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    Customers c = new Customers();
                    c.setIdCustomer(res.getString("idCustomer"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPhoneNumber(res.getString("phoneNumber"));
                    c.setPlateNumber(res.getString("plateNumber"));
                    result = c;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Customers findByName(String key) {
        Customers result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYNAME)) {
            pst.setString(1, key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    Customers c = new Customers();
                    c.setIdCustomer(res.getString("idCustomer"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPhoneNumber(res.getString("phoneNumber"));
                    c.setPlateNumber(res.getString("plateNumber"));
                    result = c;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public void close() throws IOException {
    }

    public static CustomersDAO build() {
        return new CustomersDAO();
    }
}
