package com.sep3yg9.assignment2.model;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;

import java.util.Objects;

public class PartEntity {
    private long id;
    private long animal_id;
    private String type;
    private double weight;

    public PartEntity(long id, long animal_id, String type, double weight) {
        this.id = id;
        this.animal_id = animal_id;
        this.type = type;
        this.weight = weight;
    }

    public PartEntity(Part part) {
        this.id = part.getId();
        this.animal_id = part.getAnimalId();
        this.type = part.getType();
        this.weight = part.getWeight();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(long animal_id) {
        this.animal_id = animal_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Part convertToPart() {
        Part part = Part.newBuilder()
                .setId(id)
                .setAnimalId(id)
                .setType(type)
                .setWeight(weight)
                .build();
        return part;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PartEntity that = (PartEntity) o;
        return id == that.id && animal_id == that.animal_id
            && Double.compare(that.weight, weight) == 0 && Objects.equals(type,
            that.type);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, animal_id, type, weight);
    }
}
