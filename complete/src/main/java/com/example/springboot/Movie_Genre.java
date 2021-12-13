package com.example.springboot;

import javax.persistence.Entity;;
import javax.persistence.IdClass;
import javax.persistence.Id;

@Entity
@IdClass(CompositeKeyMovieGenre.class)
public class Movie_Genre {
    //two primary keys
    @Id
    private Integer movie_id;
    @Id
    private String genre;
    protected Movie_Genre() {}

    public Movie_Genre(Integer movie_id, String genre) {
        this.movie_id   = movie_id;
        this.genre = genre;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return movie_id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.movie_id = id;
    }


    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }




}