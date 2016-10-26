package com.thoughtworks.gaia.A_1_N.entity;

import java.util.List;

public class A_B {
  private Long id;
  private String name;
  private List<B_1_N> b_1_Ns;
  
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
  
  public List<B_1_N> getB_1_Ns() {
    return b_1_Ns;
  }
  
  public void setB_1_Ns(List<B_1_N> b_1_Ns) {
    this.b_1_Ns = b_1_Ns;
  }
}
