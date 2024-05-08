package com.github.Sangarru11.ProyectoFinal.model.DAO;

import com.github.Sangarru11.ProyectoFinal.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAO implements DAO<Employee,String> {
    private static final String FINDBYDNI = "SELECT e.idEmployee, e.dni, e.name, e.password,e.Admin FROM employees AS e WHERE dni=?";
    private static final String FINDBYID = "SELECT e.idEmployee, e.dni, e.name, e.password ,e.Admin FROM employees AS e WHERE e.idEmployee = ?";
    private static final String FINDBYNAME = "SELECT e.IdEmployee, e.dni, e.name, e.Password, e.Admin FROM employees AS e WHERE e.name = ?";
    private static final String REPAIRS_EMPLOYEES = "SELECT e.idEmployee, r.idRepair FROM employees AS e FROM repairs AS r FROM repairsemployess AS re WHERE re.Socorro = ?";
    private static final String INSERT = "INSERT INTO employees (dni, name, password, plateNumber) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE employees SET name=? WHERE idEmployee=?";
    private static final String DELETE = "DELETE FROM employees WHERE idEmployee=?";
    private static final String ADMIN = "SELECT e.idEmployee, e.dni, e.name, e.password, e.Admin FROM employees AS e WHERE e.Admin=?";

    private Connection connection;
    public EmployeeDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    @Override
    public Employee save(Employee entity) {
        Employee result = entity;
        if (entity != null) {
            String dni = entity.getDNI();
            if (dni != null) {
                Employee isInDataBase = findByDNI(dni);
                if (isInDataBase == null) {
                    try (PreparedStatement pst = connection.prepareStatement(INSERT)) {
                        pst.setString(1, dni);
                        pst.setString(2, entity.getName());
                        pst.setString(3, entity.getPassword());
                        pst.setBoolean(4, entity.isAdmin());
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement pst = connection.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getName());
                        pst.setString(2, entity.getIdEmployee());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Employee delete(Employee entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setString(1, entity.getIdEmployee());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
    @Override
    public Employee adminManage(Employee entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(ADMIN)) {
                pst.setBoolean(1, entity.isAdmin());
                pst.executeUpdate();
                entity.setAdmin(!entity.isAdmin());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public Employee findById(String key) {
        Employee result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Employee c = new Employee();
                    c.setIdEmployee(res.getString("idEmployee"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPassword(res.getString("password"));
                    result = c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Repairs> findbyAll() {
        return null;
    }

    @Override
    public Employee findByDate(String key) {
        return null;
    }

    @Override
    public Employee findByDNI(String key) {
        Employee result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYDNI)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Employee c = new Employee();
                    c.setIdEmployee(res.getString("idEmployee"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPassword(res.getString("password"));
                    result = c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Employee findByName(String key) {
        Employee result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYNAME)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Employee c = new Employee();
                    c.setIdEmployee(res.getString("idEmployee"));
                    c.setDNI(res.getString("dni"));
                    c.setName(res.getString("name"));
                    c.setPassword(res.getString("password"));
                    c.setAdmin(res.getBoolean("Admin"));
                    result = c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }

    public static EmployeeDAO build() {
        return new EmployeeDAO();
    }
}
