package com.sep3yg9.assignment2.model;

import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;

import javax.persistence.*;

@Entity @Table(name = "tocentry", schema = "sdj3_assignment") public class TocentryEntity
{
  @EmbeddedId private TocentryId id;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idproduct")
  private ProductEntity idproduct;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idpart")
  private PartEntity idpart;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idtray") private TrayEntity idtray;

  public TocentryEntity()
  {
    id = new TocentryId();
  }

  public TocentryId getId()
  {
    return id;
  }

  public void setId(TocentryId id)
  {
    this.id = id;
  }

  public TrayEntity getIdtray()
  {
    return idtray;
  }

  public ProductEntity getIdproduct()
  {
    return idproduct;
  }

  public void setIdproduct(ProductEntity idproduct)
  {
    this.idproduct = idproduct;
  }

  public PartEntity getIdpart()
  {
    return idpart;
  }

  public void setIdpart(PartEntity idpart)
  {
    this.idpart = idpart;
  }

  public void setIdtray(TrayEntity idtray)
  {
    this.idtray = idtray;
  }

  public PartTray convertToPartTray()
  {
    PartTray tray1 = PartTray.newBuilder().setTrayId(idtray.getId()).setPartId(idpart.getId())
        .build();
    return tray1;
  }

}