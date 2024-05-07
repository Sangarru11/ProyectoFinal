package com.github.Sangarru11.ProyectoFinal.model.DAO;

import com.github.Sangarru11.ProyectoFinal.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.ProyectoFinal.model.entity.Mechanic;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MechanicDAO implements DAO<Mechanic,String> {
    private static final String FINDBYID = "SELECT m.idMechanic, m.name, m.direction FROM mechanics AS m WHERE m.idMechanic = ?";

    private static final String FINDBYNAME = "SELECT m.name, m.direction FROM mechanics AS m WHERE m.name = ?";
    private static final String INSERT = "INSERT INTO mechanics (name, direction) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE mechanics SET name=? WHERE idMechanic=?";
    private static final String DELETE = "DELETE FROM mechanics WHERE idMechanic=?";
    private Connection connection;
    public MechanicDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    @Override
    public Mechanic save(Mechanic entity) {
        Mechanic result = null;
        if (entity != null) {
            String name = entity.getName();
            if (name != null) {
                Mechanic isInDataBase = findByName(name);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, entity.getName());
                        pst.setString(2, entity.getDirection());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getName());
                        pst.setString(2, entity.getIdMechanic());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Mechanic delete(Mechanic entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setString(1, entity.getIdMechanic());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public Mechanic findById(String key) {
        Mechanic result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Mechanic c = new Mechanic();
                    c.setIdMechanic(res.getString("idMechanic"));
                    c.setName(res.getString("name"));
                    c.setDirection(res.getString("direction"));
                    result = c;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Repairs> findbyAll() {
        return null;
    }

    @Override
    public Mechanic findByDate(String key) {
        return null;
    }

    @Override
    public Mechanic findByDNI(String key) {
        return null;
    }

    @Override
    public Mechanic findByName(String key) {
        Mechanic result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYNAME)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Mechanic c = new Mechanic();
                    c.setIdMechanic(res.getString("idMechanic"));
                    c.setName(res.getString("name"));
                    c.setDirection(res.getString("direction"));
                    result = c;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }
    public static MechanicDAO build(){
        return new MechanicDAO();
    }
}
