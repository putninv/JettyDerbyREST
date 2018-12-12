package com.putnin.repository;

import com.putnin.model.User;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for UserRepository.
 *
 * @author putnin.v@gmail.com
 */
public class UserRepositoryTest {

    @Ignore
    @Test
    public void getUserTest() throws Exception {
        UserRepository repository = new UserRepository();
        User user = repository.getUserByPhone("+79779082837");

        assertThat(user.getId(), is(new Long(456456)));
    }
}
