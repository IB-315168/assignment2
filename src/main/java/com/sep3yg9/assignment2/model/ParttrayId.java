package com.sep3yg9.assignment2.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable public class ParttrayId implements Serializable
{
  private static final long serialVersionUID = -7601669191388159780L;
  @Column(name = "idtray") private int trayId;

  @Column(name = "idpart") private int partId;

  public ParttrayId()
  {
  }

  public ParttrayId(int trayId, int partId)
  {
    this.trayId = trayId;
    this.partId = partId;
  }

  public int getTrayId()
  {
    return trayId;
  }

  public void setTrayId(int trayId)
  {
    this.trayId = trayId;
  }

  public int getPartId()
  {
    return partId;
  }

  public void setPartId(int partId)
  {
    this.partId = partId;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
      return false;
    ParttrayId entity = (ParttrayId) o;
    return Objects.equals(this.trayId, entity.trayId) && Objects.equals(
        this.partId, entity.partId);
  }

  @Override public int hashCode()
  {
    return Objects.hash(trayId, partId);
  }

}