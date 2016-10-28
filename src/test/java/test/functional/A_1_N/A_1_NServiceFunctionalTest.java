package test.functional.A_1_N;

import com.thoughtworks.gaia.A_1_N.dao.A_1_NDao;
import com.thoughtworks.gaia.A_1_N.entity.A_B;
import com.thoughtworks.gaia.A_1_N.entity.B_1_N;
import com.thoughtworks.gaia.A_1_N.model.A_1_NModel;
import com.thoughtworks.gaia.A_1_N.service.A_1_NService;
import com.thoughtworks.gaia.A_1_N.model.B_1_NModel;
import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class A_1_NServiceFunctionalTest {
  @Autowired
  private A_1_NService a_1_NService;
  
  @Autowired
  private A_1_NDao a_1_NDao;
  
  private A_1_NModel a_1_NModel;
  private A_1_NModel a_1_NModel2;
  private B_1_NModel b_1_NModel;
  private B_1_NModel b_1_NModel2;
  
  @Before
  public void setUp() throws Exception {
    a_1_NModel = dummyA_1_NModel("A_1_N");
    a_1_NModel2 = dummyA_1_NModel("A_1_N2");
  
    b_1_NModel = dummyB_1_NModel(a_1_NModel, "bb1");
    b_1_NModel2 = dummyB_1_NModel(a_1_NModel2, "bb2");
  
    a_1_NModel.getB_1_NModels().add(b_1_NModel);
    a_1_NModel2.getB_1_NModels().add(b_1_NModel2);
  
    a_1_NDao.save(a_1_NModel);
    a_1_NDao.save(a_1_NModel2);
  }
  
  @After
  public void tearDown() throws Exception {
    List<A_1_NModel> a_1_NModels = a_1_NDao.where().queryList();
    if(a_1_NModels.contains(a_1_NModel)) {
      a_1_NDao.remove(a_1_NModel);
    }
    
    if(a_1_NModels.contains(a_1_NModel2)) {
      a_1_NDao.remove(a_1_NModel2);
    }
  }
  
  @Test
  public void should_get_a_and_b_with_id() throws Exception {
    Long aId = a_1_NModel.getId();
    A_B a_b = a_1_NService.getA(aId);
  
    List<B_1_N> b_1_NsResult = new ArrayList<>();
    B_1_N b_1_N = new B_1_N();
    b_1_N.setId(1L);
    b_1_N.setName("bb1");
    b_1_NsResult.add(b_1_N);
    
    assertThat(a_b.getId()).isEqualTo(aId);
    assertThat(a_b.getName()).isEqualTo("A_1_N");
    assertThat(a_b.getB_1_NModels().get(0).getId()).isEqualTo(b_1_NsResult.get(0).getId());
    assertThat(a_b.getB_1_NModels().get(0).getName()).isEqualTo(b_1_NsResult.get(0).getName());
  }
  
  private B_1_NModel dummyB_1_NModel(A_1_NModel a_1_NModel, String name) {
    B_1_NModel b_1_NModel = new B_1_NModel();
    b_1_NModel.setName(name);
    b_1_NModel.setA_1_NModel(a_1_NModel);
    return b_1_NModel;
  }
  
  private A_1_NModel dummyA_1_NModel(String name) {
    A_1_NModel a_1_NModel = new A_1_NModel();
    a_1_NModel.setName(name);
    return a_1_NModel;
  }
  
  @Test
  public void should_get_all_info() throws Exception {
    List<A_B> a_bs = a_1_NService.getAll();
    
    assertThat(a_bs.size()).isEqualTo(2);
    assertThat(a_bs.get(0).getName()).isEqualTo("A_1_N");
    assertThat(a_bs.get(1).getName()).isEqualTo("A_1_N2");
  
  }
  
  @Test
  public void should_delete_a_and_b_with_id() throws Exception {
    Long aId = a_1_NModel.getId();
    List<A_B> a_bs = a_1_NService.deleteA(aId);
    
    assertThat(a_bs.size()).isEqualTo(1);
  }
  
  @Test(expected = NotFoundException.class)
  public void should_throw_exception_when_not_found() {
    a_1_NService.getA(-1L);
  }
}
