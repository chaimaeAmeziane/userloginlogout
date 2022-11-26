package com.example.userloginlogout.repository;

import com.example.userloginlogout.models.Serie;
import com.example.userloginlogout.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    // Long refers to the ID tag in the User class.
    @Query("SELECT u from User u where u.email=?1")
    User findByEmail(String email);

    @Query( value = "SELECT * from users where id = :id",
            nativeQuery = true)
    User findUserById(@Param("id") Integer id);
}
