package test.functional.user;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.user.model.UserModel;
import com.thoughtworks.gaia.user.dao.UserDao;
import com.thoughtworks.gaia.user.service.UserService;
import com.thoughtworks.gaia.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class UserServiceFunctionalTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    public void should_get_user_with_id() throws Exception {
        UserModel userModel = dummyUserModel();
        userDao.save(userModel);
        Long userId = userModel.getId();

        User user = userService.getUser(userId);

        assertThat(user.getId()).isEqualTo(userId);
    }

    private UserModel dummyUserModel() {
        UserModel userModel = new UserModel();
        userModel.setName("name");
        userModel.setAge(18);
        return userModel;
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_not_found() {
        userService.getUser(-1L);
    }
}
