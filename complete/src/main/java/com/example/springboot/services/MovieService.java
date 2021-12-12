package com.example.springboot.services;

import com.example.springboot.Movie;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieService {

    public Iterable<Movie> findAll();
    public List<Movie> searchByRating( double min,  double max);

    public List<Movie> findByName(String name);

    public Movie findById(long id);

    public Movie save(Movie movie);
    public void delete(long id);
}


