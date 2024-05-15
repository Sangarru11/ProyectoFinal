package com.github.Sangarru11.ProyectoFinal.model.DAO;

import com.github.Sangarru11.ProyectoFinal.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import com.github.Sangarru11.ProyectoFinal.model.entity.RepairEmployee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepairEmployeeDAO implements DAO<RepairEmployee,String> {
    private static final String INSERTEMPLOYEEREPAIRS = "INSERT INTO repair_employee (IdRepair, IdEmployee) VALUES (?, ?)";
    private Connection connection;

    public RepairEmployeeDAO() {
        connection = ConnectionMariaDB.getConnection();
    }
    public void assignEmployeeToRepair(int IdRepair, int IdEmployee) {
        try (PreparedStatement pst = connection.prepareStatement(INSERTEMPLOYEEREPAIRS)) {
            pst.setInt(1, IdRepair);
            pst.setInt(2, IdEmployee);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public RepairEmployee save(RepairEmployee entity) {
        return null;
    }

    @Override
    public RepairEmployee delete(RepairEmployee entity) throws SQLException {
        return null;
    }

    @Override
    public Employee adminManage(Employee entity) throws SQLException {
        return null;
    }

    @Override
    public RepairEmployee findById(String key) {
        return null;
    }

    @Override
    public List<RepairEmployee> findbyAll() {
        return null;
    }

    @Override
    public RepairEmployee findByDate(String key) {
        return null;
    }

    @Override
    public RepairEmployee findByDNI(String key) {
        return null;
    }

    @Override
    public RepairEmployee findByName(String key) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
    public static RepairEmployeeDAO build(){
        return new RepairEmployeeDAO();
    }
}
