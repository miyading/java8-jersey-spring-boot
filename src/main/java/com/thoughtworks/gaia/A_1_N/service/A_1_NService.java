package com.thoughtworks.gaia.A_1_N.service;

import com.thoughtworks.gaia.A_1_N.A_1_NMapper;
import com.thoughtworks.gaia.A_1_N.B_1_NMapper;
import com.thoughtworks.gaia.A_1_N.dao.A_1_NDao;
import com.thoughtworks.gaia.A_1_N.entity.A_B;
import com.thoughtworks.gaia.A_1_N.entity.B_1_N;
import com.thoughtworks.gaia.A_1_N.model.A_1_NModel;
import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class A_1_NService implements Loggable {
  @Autowired
  private A_1_NMapper a_1_NMapper;
  
  @Autowired
  private B_1_NMapper b_1_NMapper;
  
  @Autowired
  private A_1_NDao a_1_NDao;
  
  public A_B getA(Long aId) {
    A_1_NModel a_1_NModel = a_1_NDao.idEquals(aId).querySingle();
    
    if (a_1_NModel == null) {
      error("A not found with id: " + aId);
      throw new NotFoundException();
    }
    
    A_B a_b = a_1_NMapper.map(a_1_NModel, A_B.class);
    
    List<B_1_N> b_1_Ns = b_1_NMapper.mapList(a_1_NModel.getB_1_NModels(), B_1_N.class);
    a_b.setB_1_Ns(b_1_Ns);
    return a_b;
  }
  
  public List<A_B> getAll() {
    List<A_1_NModel> a_1_NModels = a_1_NDao.where().queryList();
  
    if (a_1_NModels == null) {
      error("A not found");
      throw new NotFoundException();
    }
    
    List<A_B> a_bs = a_1_NMapper.mapList(a_1_NModels, A_B.class);
    int i = 0;
    for(A_1_NModel a_1_NModel : a_1_NModels) {
      List<B_1_N> b_1_Ns = b_1_NMapper.mapList(a_1_NModel.getB_1_NModels(), B_1_N.class);
      a_bs.get(i).setB_1_Ns(b_1_Ns);
      i++;
    }
    
    return a_bs;
  }
  
  public List<A_B> deleteA(Long aId) {
    A_1_NModel a_1_NModelToDelete = a_1_NDao.idEquals(aId).querySingle();
  
    if (a_1_NModelToDelete == null) {
      error("A not found with id: " + aId);
      throw new NotFoundException();
    }
    
    a_1_NDao.remove(a_1_NModelToDelete);
  
    List<A_B> a_bs = this.getAll();
    return a_bs;
  }
}
