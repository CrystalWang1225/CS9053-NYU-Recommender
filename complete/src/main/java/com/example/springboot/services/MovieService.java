package com.example.springboot.services;

import com.example.springboot.Movie;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieService {

    public Iterable<Movie> findAll();
    public List<Movie> searchByRating( double min,  double max);

    List<Movie> findByName(String name);

    Movie findById(long id);
}


