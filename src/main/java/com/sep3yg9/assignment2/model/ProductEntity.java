package com.sep3yg9.assignment2.model;

import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;
import com.sep3yg9.assignment2.grpc.protobuf.products.Product;
import com.sep3yg9.assignment2.model.PartEntity1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "product", schema = "sdj3_assignment") public class ProductEntity
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idproduct", nullable = false) private Integer id;

  @Column(name = "type", nullable = false, length = 200) private String type;

  @OneToMany(mappedBy = "idproduct") private Set<TocentryEntity> tocentries = new LinkedHashSet<>();

  @Column(name = "finished") private Boolean finished;

  public Boolean getFinished()
  {
    return finished;
  }

  public void setFinished(Boolean finished)
  {
    this.finished = finished;
  }

  public Set<TocentryEntity> getTocentries()
  {
    return tocentries;
  }

  public void setTocentries(Set<TocentryEntity> tocentries)
  {
    this.tocentries = tocentries;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }
  public Product convertToProduct() {
    List<PartTray> toc = new ArrayList<>();

    for(TocentryEntity entry : tocentries) {
      toc.add(entry.convertToPartTray());
    }

    Product product = Product.newBuilder()
                        .setId(id)
        .setProductType(type)
        .setFinished(finished)
        .addAllToc(toc)
        .build();

    return product;
  }
}