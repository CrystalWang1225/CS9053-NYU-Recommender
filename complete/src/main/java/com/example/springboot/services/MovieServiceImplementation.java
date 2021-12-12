package com.example.springboot.services;

import com.example.springboot.Movie;
import com.example.springboot.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieService")
public class MovieServiceImplementation implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Iterable<Movie> findAll(){
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchByRating(double min, double max) {
        return movieRepository.searchByRating(min, max);
    }

    @Override
    public List<Movie> findByName(String name) {
        return  movieRepository.findByName(name);
    }

    @Override
    public Movie findById(long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void delete(long id) {
        try{
            movieRepository.deleteById(id);}
        catch(Exception e){
            System.out.println("Error in deletingById");
            e.printStackTrace();
        }
    }

}
