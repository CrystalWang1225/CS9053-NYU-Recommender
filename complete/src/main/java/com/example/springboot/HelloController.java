package com.example.springboot;

import com.example.springboot.services.MovieServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
public class HelloController {
	//controller to connect backend with frontend. this controller implements APIs to bring functionality to frontend


	@Autowired
	private MovieServiceImplementation movieService; //interface to interact with backend

	@GetMapping("/getAll") //API to get all movies
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Movie> index() {
		System.out.println("Greetings from Spring Boot!");
		return (List<Movie>) movieService.findAll();

	}

	@GetMapping("/search/{name}") //API to find movies by title
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getByName(@PathVariable String name) {
		//example request: http://localhost:8080/search/Encanto
		return movieService.findByName(name);

	}

	@GetMapping("/search") //API to find movies by rating range
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getByRating(@RequestParam String min, @RequestParam String max) {
		// example request : http://localhost:8080/search?min=7.0&max=9.0
		return movieService.searchByRating(Double.parseDouble(min),Double.parseDouble(max));
	}

	// below APIs are for get all movies that belong to a specific genre
	@GetMapping("/search/comedy")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getComedy() {
		return movieService.getComedy();
	}

	@GetMapping("/search/scifi")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getScifi() {
		return movieService.getScifi();
	}

	@GetMapping("/search/horror")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getHorror() {
		return movieService.getHorror();
	}

	@GetMapping("/search/romance")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getRomance() {
		return movieService.getRomance();
	}

	@GetMapping("/search/action")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getAction() {
		return movieService.getAction();
	}

	@GetMapping("/search/thriller")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getThriller() {
		return movieService.getThriller();
	}

	@GetMapping("/search/drama")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getDrama() {
		return movieService.getDrama();
	}

	@GetMapping("/search/mystery")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getMystery() {
		return movieService.getMystery();
	}

	@GetMapping("/search/crime")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getCrime() {
		return movieService.getCrime();
	}

}
