package com.example.springboot.services;

import com.example.springboot.Movie;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieService {

    public Iterable<Movie> findAll();
    public List<Movie> searchByRating( double min,  double max);
    public List<Movie> getComedy();
    public List<Movie> getScifi();
    public List<Movie> getRomance();
    public List<Movie> getHorror();
    public List<Movie> getAction();
    public List<Movie> getDrama();
    public List<Movie> getMystery();
    public List<Movie> getThriller();
    public List<Movie> getCrime();


    public List<Movie> findByName(String name);

    public Movie findById(long id);

    public Movie save(Movie movie);
    public void delete(long id);
    public boolean fetchIMDbMoviesAndSave();
}


