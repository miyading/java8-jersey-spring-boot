package com.thoughtworks.gaia.common.jpa;

import com.thoughtworks.gaia.A_1_N.model.A_1_NModel;

import javax.persistence.*;

@MappedSuperclass
public abstract class ABEntityBaseModel extends IdBaseModel {
  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, optional=false)
  @JoinColumn(name = "A_id")
  private A_1_NModel a_1_NModel;
  
  public A_1_NModel getA_1_NModel() {
    return a_1_NModel;
  }
  
  public void setA_1_NModel(A_1_NModel a_1_NModel) {
    this.a_1_NModel = a_1_NModel;
  }
}
