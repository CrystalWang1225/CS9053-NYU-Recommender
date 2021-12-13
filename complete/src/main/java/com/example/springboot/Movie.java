package com.example.springboot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
  //  private String genre;
    private Double rating;

    protected Movie() {}

    public Movie(String name, Double rating) {
        this.name   = name;
        this.rating = rating;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the message
     */
    public Double getRating() {
        return rating;
    }

    /**
     * @param rating the message to set
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String toString(){
        return "Name: "+ this.getName() + " Rating: "+ this.getRating();
    }
}