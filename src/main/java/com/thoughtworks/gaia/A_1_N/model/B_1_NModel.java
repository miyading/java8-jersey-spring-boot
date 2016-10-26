package com.thoughtworks.gaia.A_1_N.model;


import com.thoughtworks.gaia.common.jpa.ABEntityBaseModel;

import javax.persistence.*;

@Entity
@Table(name = "B_1_N")
public class B_1_NModel extends ABEntityBaseModel {
  @Column(name = "name", nullable = false, length = 64)
  private String name;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
}
