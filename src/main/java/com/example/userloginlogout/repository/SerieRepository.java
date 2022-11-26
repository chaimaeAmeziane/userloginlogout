package com.example.userloginlogout.repository;

import com.example.userloginlogout.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie,Integer> {
    @Query( value = "SELECT * from serie where user_id = :id",
            nativeQuery = true)
    public List<Serie> findSeriesByUser(@Param("id") Integer id);
}
