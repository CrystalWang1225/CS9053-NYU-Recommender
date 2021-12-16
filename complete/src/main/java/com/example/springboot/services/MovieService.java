package com.example.springboot.services;

import com.example.springboot.Movie;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieService {// interface that outlines the functionalities our Backend service provides

    public Iterable<Movie> findAll(); //find all movies
    public List<Movie> searchByRating( double min,  double max); //find movies by rating range

    public List<Movie> getComedy(); // get all comedy movies
    public List<Movie> getScifi(); // get all scifi movies
    public List<Movie> getRomance(); // get all romance movies
    public List<Movie> getHorror(); // get all horror movies
    public List<Movie> getAction(); // get all action movies
    public List<Movie> getDrama(); // get all drama movies
    public List<Movie> getMystery(); // get all mystery movies
    public List<Movie> getThriller(); // get all thriller movies
    public List<Movie> getCrime(); // get all crime movies


    public List<Movie> findByName(String name); //find movie by title

    public Movie findById(long id); //find movie by ID

    public Movie save(Movie movie); //save movie to database
    public void delete(long id); //delete movie from database
    public boolean fetchIMDbMoviesAndSave(); //scrape imdb website and save to db
}


