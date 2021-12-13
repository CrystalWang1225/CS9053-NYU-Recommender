package com.example.springboot;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("movieRepository")
public interface MovieRepository extends CrudRepository<Movie, Long> {
    @Query("from Movie where rating >= :min and rating <= :max")
    public List<Movie> searchByRating(@Param("min") double min, @Param("max") double max);

    List<Movie> findByName(String name);

    Movie findById(long id);
}