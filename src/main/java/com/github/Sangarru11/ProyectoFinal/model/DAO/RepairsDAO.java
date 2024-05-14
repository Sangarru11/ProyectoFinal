package com.github.Sangarru11.ProyectoFinal.model.DAO;

import com.github.Sangarru11.ProyectoFinal.model.Connection.ConnectionMariaDB;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairsDAO implements DAO<Repairs,String> {
    private static final String FINDBYID = "SELECT r.IdRepair, r.date, r.status, r.description, r.plateNumber FROM repairs AS r WHERE r.IdRepair = ?";
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
    public Employee adminManage(Employee entity) throws SQLException {
        return null;
    }

    @Override
    public Repairs findById(String key) {
        Repairs result = null;
        try (PreparedStatement pst = connection.prepareStatement(FINDBYID)) {
            pst.setString(1, key);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    RepairsLazy c = new RepairsLazy();
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
    public List<Repairs> findbyAll() {
        List<Repairs> result = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM repairs")) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    RepairsLazy r = new RepairsLazy();
                    r.setIdRepair(res.getString("idRepair"));
                    r.setDate(res.getString("date"));
                    r.setStatus(res.getString("status"));
                    r.setDescription(res.getString("description"));
                    r.setPlateNumber(res.getString("plateNumber"));
                    result.add(r);
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
                    RepairsLazy c = new RepairsLazy();
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
class RepairsLazy extends Repairs{
    private static final String FINDEMPLOYEESBYREPAIR = "SELECT e.* FROM repairs r,repair_employee re, employees e WHERE r.IdRepair=re.IdRepair AND re.IdEmployee=e.IdEmployee AND r.IdRepair=?";
    public RepairsLazy() {

    }
    public RepairsLazy(String idRepair, List<Employee> employees, String date, String status, String description, String plateNumber) {
        super(idRepair, employees, date, status, description, plateNumber);
    }
    @Override
        public List<Employee> getEmployees(){
            if(super.getEmployees()==null){
                Connection connection = ConnectionMariaDB.getConnection();
                List<Employee> result = new ArrayList<>();
                try (PreparedStatement pst = connection.prepareStatement(FINDEMPLOYEESBYREPAIR)) {
                    pst.setString(1, getIdRepair());
                    try (ResultSet res = pst.executeQuery()) {
                        while (res.next()) {
                            Employee e = new Employee();
                            e.setIdEmployee(res.getString("idEmployee"));
                            e.setDNI(res.getString("DNI"));
                            e.setName(res.getString("name"));
                            result.add(e);
                        }
                    }
                    super.setEmployees(result);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return super.getEmployees();
        }
}
