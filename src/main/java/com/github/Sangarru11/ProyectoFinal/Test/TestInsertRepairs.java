package com.github.Sangarru11.ProyectoFinal.Test;

import com.github.Sangarru11.ProyectoFinal.model.DAO.RepairsDAO;
import com.github.Sangarru11.ProyectoFinal.model.entity.Repairs;

public class TestInsertRepairs {
    public static void main(String[] args) {

        Repairs repairs = new Repairs();
        repairs.setIdRepair(1);
        repairs.setPlateNumber("1234");
        repairs.setDescription("description");
        repairs.setDate("2022-12-12");
        repairs.setStatus("Sin empezar");

        RepairsDAO repairsDAO = new RepairsDAO();
        repairsDAO.save(repairs);
    }
}
