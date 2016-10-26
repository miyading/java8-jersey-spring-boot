package com.thoughtworks.gaia.A_1_N.dao;

import com.thoughtworks.gaia.A_1_N.model.A_1_NModel;
import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import org.springframework.stereotype.Component;

@Component
public class A_1_NDao extends BaseDaoWrapper<A_1_NModel> {
  public A_1_NDao() {
    super(A_1_NModel.class);
  }
}
