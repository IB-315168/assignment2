package com.sep3yg9.assignment2.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class Animal
{
  private long regNumber;
  private Date arrivedOn;
  private double weight;
  private String origin;
  public Animal(long regNumber, double weight, String origin) {
    this.regNumber = regNumber;
    this.weight = weight;
    this.origin = origin;

    arrivedOn = Date.valueOf(LocalDate.now());
  }

  public long getRegNumber()
  {
    return regNumber;
  }

  public Date getArrivedOn()
  {
    return arrivedOn;
  }
  public void setArrivedOn(Date arrivedOn)
  {
    this.arrivedOn = arrivedOn;
  }

  public double getWeight()
  {
    return weight;
  }

  public void setWeight(double weight)
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
