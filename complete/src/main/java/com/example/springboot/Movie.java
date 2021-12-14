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
    private Boolean is_comedy;
    private Boolean is_scifi;
    private Boolean is_romance;
    private Boolean is_horror;
    private Boolean is_action;
    private Boolean is_drama;
    private Boolean is_mystery;
    private Boolean is_thriller;
    private Boolean is_crime;

    protected Movie() {}

    public Movie(String name, Double rating, Boolean is_comedy, Boolean is_scifi,
                 Boolean is_horror, Boolean is_romance,  Boolean is_action,
                 Boolean is_thriller, Boolean is_drama, Boolean is_mystery, Boolean is_crime

    ) {
        this.name   = name;
        this.rating = rating;

        this.is_comedy= is_comedy;
        this.is_scifi= is_scifi;
        this.is_horror= is_horror;
        this.is_romance= is_romance;
        this.is_action= is_action;
        this.is_thriller= is_thriller;
        this.is_drama= is_drama;
        this.is_mystery= is_mystery;
        this.is_crime= is_crime;
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


    public Boolean getIs_comedy() {
        return is_comedy;
    }

    public Boolean getIs_scifi() {
        return is_scifi;
    }

    public Boolean getIs_romance() {
        return is_romance;
    }

    public Boolean getIs_horror() {
        return is_horror;
    }

    public Boolean getIs_action() {
        return is_action;
    }

    public Boolean getIs_drama() {
        return is_drama;
    }

    public Boolean getIs_mystery() {
        return is_mystery;
    }

    public Boolean getIs_thriller() {
        return is_thriller;
    }

    public Boolean getIs_crime() {
        return is_crime;
    }

    public String toString(){
        return "Name: "+ this.getName() + " Rating: "+ this.getRating();
    }

}