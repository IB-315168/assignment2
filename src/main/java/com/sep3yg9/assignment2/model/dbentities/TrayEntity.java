package com.sep3yg9.assignment2.model.dbentities;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.model.PartEntity1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "tray", schema = "sdj3_assignment") public class TrayEntity
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idtray", nullable = false) private Integer id;

  @Column(name = "maxweight", nullable = false) private Double maxweight;

  @Column(name = "type", nullable = false, length = 100) private String type;

  @Column(name = "finished") private Boolean finished;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "idtray", cascade = CascadeType.ALL, orphanRemoval = true) private Set<Parttray> parttrays = new LinkedHashSet<>();

  public void addPart(PartEntity part) {
    Parttray parttray = new Parttray(this, part);

    parttrays.add(parttray);
    part.getParttrays().add(parttray);
  }

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

  public Double getMaxweight()
  {
    return maxweight;
  }

  public void setMaxweight(Double maxweight)
  {
    this.maxweight = maxweight;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public Tray convertToTray() {
    List<Part> trayParts = new ArrayList<>();
    for(Parttray part : parttrays) {
      trayParts.add(part.getIdpart().convertToPart());
    }

    Tray tray = Tray.newBuilder()
        .setId(id)
        .setMaxWeight(maxweight)
        .setType(type)
        .addAllParts(trayParts)
        .build();

    return tray;
  }
}