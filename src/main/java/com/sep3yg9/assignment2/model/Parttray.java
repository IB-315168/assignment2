package com.sep3yg9.assignment2.model;

import javax.persistence.*;

@Entity @Table(name = "parttray", schema = "sdj3_assignment") public class Parttray
{
  @GeneratedValue(strategy = GenerationType.IDENTITY) @EmbeddedId private ParttrayId id;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("trayId")
  private TrayEntity idtray;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("partId")
  private PartEntity idpart;

  public Parttray()
  {
  }

  public Parttray(TrayEntity idtray, PartEntity idpart)
  {
    this.idtray = idtray;
    this.idpart = idpart;
    this.id = new ParttrayId(idtray.getId(), idpart.getId());
  }

  public PartEntity getIdpart()
  {
    return idpart;
  }

  public void setIdpart(PartEntity idpart)
  {
    this.idpart = idpart;
  }

  public TrayEntity getIdtray()
  {
    return idtray;
  }

  public void setIdtray(TrayEntity idtray)
  {
    this.idtray = idtray;
  }
  //  public TrayEntity getTrayEntity()
//  {
//    return trayEntity;
//  }
//
//  public void setTrayEntity(TrayEntity trayEntity)
//  {
//    this.trayEntity = trayEntity;
//  }
//
//  public PartEntity getPartEntity()
//  {
//    return partEntity;
//  }
//
//  public void setPartEntity(PartEntity partEntity)
//  {
//    this.partEntity = partEntity;
//  }

  public ParttrayId getId()
  {
    return id;
  }

  public void setId(ParttrayId id)
  {
    this.id = id;
  }

}