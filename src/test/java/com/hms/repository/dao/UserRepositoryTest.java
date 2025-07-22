package com.hms.repository.dao;


import com.hms.repository.dmo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUser() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setFirstName("test1");
        user.setLastName("lastName");
        user.setPassword("test");

        User savedUser = repo.save(user);

        User existingUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existingUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail() {
        User user = repo.findUserByEmail("test@test.com");
        assertThat(user.getEmail()).isEqualTo("test@test.com");
    }
}
