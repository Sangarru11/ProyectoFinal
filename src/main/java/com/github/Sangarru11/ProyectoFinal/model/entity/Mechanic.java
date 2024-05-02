package com.github.Sangarru11.ProyectoFinal.model.entity;

import java.util.Objects;

public class Mechanic {
    private String idMechanic;
    private String name;
    private String Direction;

    public Mechanic() {
    }

    public Mechanic(String idMechanic, String name, String direction) {
        this.idMechanic = idMechanic;
        this.name = name;
        Direction = direction;
    }

    public String getIdMechanic() {
        return idMechanic;
    }

    public void setIdMechanic(String idMechanic) {
        this.idMechanic = idMechanic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (this == object) isEquals = true;
        if (object == null || getClass() != object.getClass()) isEquals = false;
        Mechanic mechanic = (Mechanic) object;
        isEquals = Objects.equals(idMechanic, mechanic.idMechanic) && Objects.equals(name, mechanic.name) && Objects.equals(Direction, mechanic.Direction);
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMechanic, name, Direction);
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "idMechanic='" + idMechanic + '\'' +
                ", name='" + name + '\'' +
                ", Direction='" + Direction + '\'' +
                '}';
    }
}
