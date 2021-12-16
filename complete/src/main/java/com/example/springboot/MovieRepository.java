package com.example.springboot;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("movieRepository")
public interface MovieRepository extends CrudRepository<Movie, Long> {
    ///Interface for generic CRUD operations on a repository for Movie type.
    // implements interactions with database

    @Query("from Movie where rating >= :min and rating <= :max")
    public List<Movie> searchByRating(@Param("min") double min, @Param("max") double max);

    @Query("from Movie where is_comedy = true")
    public List<Movie> getComedy();

    @Query("from Movie where is_scifi = true ")
    public List<Movie> getScifi();

    @Query("from Movie where  is_horror = true" )
    public List<Movie> getHorror();

    @Query("from Movie where  is_romance = true" )
    public List<Movie> getRomance();

    @Query("from Movie where  is_action = true" )
    public List<Movie> getAction();

    @Query("from Movie where  is_thriller = true" )
    public List<Movie> getThriller();

    @Query("from Movie where  is_drama = true" )
    public List<Movie> getDrama();

    @Query("from Movie where  is_mystery = true" )
    public List<Movie> getMystery();

    @Query("from Movie where  is_crime = true" )
    public List<Movie> getCrime();




    List<Movie> findByName(String name);

    Movie findById(long id);
}