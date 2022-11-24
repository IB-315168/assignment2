package com.sep3yg9.assignment2.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "animal", schema = "sdj3_assignment") public class AnimalEntity
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "regnumber", nullable = false) private Integer id;

  @Column(name = "arrivedon", nullable = false) private LocalDate arrivedon;

  @Column(name = "weight", nullable = false) private Double weight;

  @Column(name = "origin", nullable = false, length = 100) private String origin;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public LocalDate getArrivedon()
  {
    return arrivedon;
  }

  public void setArrivedon(LocalDate arrivedon)
  {
    this.arrivedon = arrivedon;
  }

  public Double getWeight()
  {
    return weight;
  }

  public void setWeight(Double weight)
  {
    this.weight = weight;
  }

  public String getOrigin()
  {
    return origin;
  }

  public void setOrigin(String origin)
  {
    this.origin = origin;
  }

}