package com.sep3yg9.assignment2.model.dbentities;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity @Table(name = "part", schema = "sdj3_assignment") public class PartEntity
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idpart", nullable = false) private Integer id;

  @ManyToOne(fetch = FetchType.LAZY) @OnDelete(action = OnDeleteAction.CASCADE) @JoinColumn(name = "animalid") private AnimalEntity animalid;

  @Column(name = "type", nullable = false, length = 100) private String type;

  @Column(name = "weight", nullable = false) private Double weight;

  @Column(name = "finished") private Boolean finished;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "idpart", cascade = CascadeType.ALL, orphanRemoval = true) private Set<Parttray> parttrays = new LinkedHashSet<>();

  public Set<Parttray> getParttrays()
  {
    return parttrays;
  }

  public void setParttrays(Set<Parttray> parttrays)
  {
    this.parttrays = parttrays;
  }

  public Boolean getFinished()
  {
    return finished;
  }

  public void setFinished(Boolean finished)
  {
    this.finished = finished;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public AnimalEntity getAnimalid()
  {
    return animalid;
  }

  public void setAnimalid(AnimalEntity animalid)
  {
    this.animalid = animalid;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public Double getWeight()
  {
    return weight;
  }

  public void setWeight(Double weight)
  {
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

}