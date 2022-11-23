package com.sep3yg9.assignment2.model.dbentities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable public class TocentryId implements Serializable
{
  private static final long serialVersionUID = 7406014743690222461L;
  @Column(name = "idproduct") private Integer idproduct;

  @Column(name = "idpart") private Integer idpart;

  public Integer getIdproduct()
  {
    return idproduct;
  }

  public void setIdproduct(Integer idproduct)
  {
    this.idproduct = idproduct;
  }

  public Integer getIdpart()
  {
    return idpart;
  }

  public void setIdpart(Integer idpart)
  {
    this.idpart = idpart;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
      return false;
    TocentryId entity = (TocentryId) o;
    return Objects.equals(this.idproduct, entity.idproduct) && Objects.equals(
        this.idpart, entity.idpart);
  }

  @Override public int hashCode()
  {
    return Objects.hash(idproduct, idpart);
  }

}