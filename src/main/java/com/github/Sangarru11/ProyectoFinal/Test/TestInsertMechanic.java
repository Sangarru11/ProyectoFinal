package com.github.Sangarru11.ProyectoFinal.Test;

import com.github.Sangarru11.ProyectoFinal.model.DAO.MechanicDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Employee;
import com.github.Sangarru11.ProyectoFinal.model.entity.Mechanic;

public class TestInsertMechanic {
    public static void main(String[] args) {
        Mechanic mechanic = new Mechanic();
        mechanic.setIdMechanic("1");
        mechanic.setName("Jorge");
        mechanic.setDirection("Calle 1");

        MechanicDAO mechanicDAO = new MechanicDAO();
        mechanicDAO.save(mechanic);
    }
}
