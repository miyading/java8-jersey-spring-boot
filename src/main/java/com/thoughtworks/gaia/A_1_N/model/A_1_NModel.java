package com.thoughtworks.gaia.A_1_N.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "A_1_N")
public class A_1_NModel extends IdBaseModel {
  @Column(name = "name", nullable = false, length = 64)
  private String name;
  
  @OneToMany(mappedBy = "a_1_NModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<B_1_NModel> b_1_NModels = new ArrayList<>();
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<B_1_NModel> getB_1_NModels() {
    return b_1_NModels;
  }
  
  public void setB_1_NModels(List<B_1_NModel> b_1_NModels) {
    this.b_1_NModels = b_1_NModels;
  }
}
