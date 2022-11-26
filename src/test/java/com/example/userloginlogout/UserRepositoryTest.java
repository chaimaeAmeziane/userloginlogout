package com.example.userloginlogout;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.userloginlogout.models.User;
import com.example.userloginlogout.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    // test to create a new user
    public void testCreateUser()
    {
        User user = new User();
        user.setEmail("test.user@gmail.com");
        user.setPassword("chaimae2022");
        user.setFirstName("chaimae");
        user.setLastName("ameziane");
        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class,savedUser.getId());
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }
    @Test
    public void testFindUserByEmail()
    {
        String email="chaimaeameziane8@gmail.com";
        User user = repo.findByEmail(email);
        assertThat(user).isNotNull();
        System.out.println(user.getEmail());
    }
}
