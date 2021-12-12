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
  // private MovieServiceImplementation movieService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
      //  demo1();
        demo2();
    }
    /*@Bean
   public CommandLineRunner demo(MovieRepository repository) {
        return (args) -> {
            demo1();
        };
    }*/
    private void demo2(){
     /*   movieService.save(new Movie("Taxi Driver", 9.0));
        movieService.save(new Movie("Batman", 2.5));*/
        System.out.println("----findAll");
        for (Movie mov:movieService.findAll() ){
            System.out.println(mov.toString());
        }
      /*  try{
        movieService.delete(1L);}
        catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }*/
        System.out.println("----findAll AFTER deletion");
        for (Movie mov:movieService.findAll() ){
            System.out.println(mov.toString());
        }
    }
    private void demo1(){
        System.out.println("----findAll");
        for (Movie mov:movieService.findAll() ){
            System.out.println(mov.toString());
        }
          System.out.println("----searchByRating");
        for (Movie mov:movieService.searchByRating(6.0, 7.0) ){
            System.out.println(mov.toString());
        }
      System.out.println("----findByName");
        for (Movie mov:movieService.findByName("Fight Club") ){
            System.out.println(mov.toString());
        }
        System.out.println("----findById");
        System.out.println( movieService.findById(1L).toString());

        /*
        // save a few movies
        repository.save(new Movie("Fight Club", 7.8));
        repository.save(new Movie("Titanic", 6.5));

        // fetch all moives
        System.out.println("Movies found with findAll():");
        System.out.println("-------------------------------");
        for (Movie movie : repository.findAll()) {
            System.out.println(movie.toString());
        }
        System.out.println("");

        // fetch an individual customer by ID
        Movie mv = repository.findById(1L);
        System.out.println("Movie found with findById(1L):");
        System.out.println("--------------------------------");
        System.out.println(mv.toString());
        System.out.println("");

        // fetch movies by name
        System.out.println("Movies found with findByLastName('Titanic'):");
        System.out.println("--------------------------------------------");
        repository.findByName("Titanic").forEach(mov -> {
            System.out.println(mov.toString());
        });
        // for (Customer bauer : repository.findByLastName("Bauer")) {
        //  log.info(bauer.toString());
        // }
        System.out.println("----");
    */
    }


/*	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}*/

}
