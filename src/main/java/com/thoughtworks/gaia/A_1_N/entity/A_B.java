package com.thoughtworks.gaia.A_1_N.entity;

import com.thoughtworks.gaia.A_1_N.model.B_1_NModel;

import java.util.List;

public class A_B {
  private Long id;
  private String name;
  private List<B_1_NModel> b_1_NModels;
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
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
