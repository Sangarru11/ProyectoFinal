package com.github.Sangarru11.ProyectoFinal.model.entity;

import java.util.Objects;

public class RepairEmployee {
    private int repairId;
    private int employeeId;

    public RepairEmployee() {
    }

    public RepairEmployee(int repairId, int employeeId) {
        this.repairId = repairId;
        this.employeeId = employeeId;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairEmployee that = (RepairEmployee) o;
        return repairId == that.repairId && employeeId == that.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(repairId, employeeId);
    }

    @Override
    public String toString() {
        return "RepairEmployee{" +
                "repairId=" + repairId +
                ", employeeId=" + employeeId +
                '}';
    }
}
