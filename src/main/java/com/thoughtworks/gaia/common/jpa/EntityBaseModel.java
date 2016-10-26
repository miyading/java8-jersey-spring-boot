package com.thoughtworks.gaia.common.jpa;

import com.thoughtworks.gaia.A_1_N.model.A_1_NModel;
import org.joda.time.DateTime;

import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityBaseModel extends IdBaseModel {
  @Column(name = "time_created", nullable = false, updatable = false)
  private Date timeCreated;
  
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "A_id")
  private A_1_NModel a_1_NModel;
  
  public EntityBaseModel() {
    timeCreated = DateTime.now().toDate();
  }
  
  public Date getTimeCreated() {
    return timeCreated;
  }
  
  public void setTimeCreated(Date timeCreated) {
    this.timeCreated = timeCreated;
  }
  
  public A_1_NModel getA_1_NModel() {
    return a_1_NModel;
  }
  
  public void setA_1_NModel(A_1_NModel a_1_NModel) {
    this.a_1_NModel = a_1_NModel;
  }
}
