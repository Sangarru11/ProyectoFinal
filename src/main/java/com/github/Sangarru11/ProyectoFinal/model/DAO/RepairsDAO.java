package com.github.Sangarru11.ProyectoFinal.model.DAO;

import com.github.Sangarru11.ProyectoFinal.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairsDAO implements DAO<Repairs,String> {
    private static final String FINDBYID = "SELECT r.idRepair, r.date, r.status, r.description, r.plateNumber FROM repairs AS r WHERE r.idRepair = ?";
    private static final String FINDBYDATE = "SELECT r.date, r.status, r.description, r.plateNumber FROM repairs AS r WHERE r.date = ?";
    private static final String INSERT = "INSERT INTO repairs (date, status, description, plateNumber) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE repairs SET date=?, status=?, description=?, plateNumber=? WHERE idRepair=?";
    private static final String DELETE = "DELETE FROM repairs WHERE idRepair=?";
    private Connection connection;
    public RepairsDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    @Override
    public Repairs save(Repairs entity) {
        Repairs result = entity;
        if (entity != null) {
            String date = entity.getDate();
            if (date != null) {
                Repairs isInDataBase = findByDate(date);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, entity.getDate());
                        pst.setString(2, entity.getStatus());
                        pst.setString(3, entity.getDescription());
                        pst.setString(4, entity.getPlateNumber());
                        pst.executeUpdate();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getDate());
                        pst.setString(2, entity.getStatus());
                        pst.setString(3, entity.getDescription());
                        pst.setString(4, entity.getPlateNumber());
                        pst.setString(5, entity.getIdRepair());
                        pst.executeUpdate();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Repairs delete(Repairs entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setString(1, entity.getIdRepair());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public Repairs findById(String key) {
        Repairs result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Repairs c = new Repairs();
                    c.setIdRepair(res.getString("idRepair"));
                    c.setDate(res.getString("date"));
                    c.setStatus(res.getString("status"));
                    c.setDescription(res.getString("description"));
                    c.setPlateNumber(res.getString("plateNumber"));
                    result = c;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Repairs findByDate(String key){
        Repairs result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYDATE)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Repairs c = new Repairs();
                    c.setDate(res.getString("date"));
                    c.setStatus(res.getString("status"));
                    c.setDescription(res.getString("description"));
                    c.setPlateNumber(res.getString("plateNumber"));
                    result = c;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Repairs findByDNI(String key) {
        return null;
    }

    @Override
    public Repairs findByName(String key) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
    public static RepairsDAO build(){
        return new RepairsDAO();
    }
}
