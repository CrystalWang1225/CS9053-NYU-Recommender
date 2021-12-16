package com.example.springboot;

import java.util.Arrays;

//import org.springframework.boot.CommandLineRunner;
import com.example.springboot.services.MovieService;
import com.example.springboot.services.MovieServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootApplication
public class Application implements CommandLineRunner {

   @Autowired
   private MovieServiceImplementation movieService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----Initializing database");
        initializeDatabaseMovie();
        System.out.println("----finished initializing");
        System.out.println("You can start using the app now!");
    }

    private void initializeDatabaseMovie(){
        movieService.fetchIMDbMoviesAndSave();
    }

}
