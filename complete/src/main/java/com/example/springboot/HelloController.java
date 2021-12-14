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

	@Autowired
	private MovieServiceImplementation movieService;

	@GetMapping("/")
	public List<Movie> index() {
		System.out.println("Greetings from Spring Boot!");
		return (List<Movie>) movieService.findAll();

	}

	@GetMapping("/search/{name}")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getByName(@PathVariable String name) {
		//example request: http://localhost:8080/search/Encanto
		return movieService.findByName(name);
			//	.orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@GetMapping("/search")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getByRating(@RequestParam String min, @RequestParam String max) {
		// example request : http://localhost:8080/search?min=7.0&max=9.0
		return movieService.searchByRating(Double.parseDouble(min),Double.parseDouble(max));
	}

	/*@GetMapping("/search")
	@CrossOrigin(origins = "http://localhost:3000")
	List<Movie>  getByGenre(@RequestParam String comedy, @RequestParam String sci_fi) {
		// example request : http://localhost:8080/search?min=7.0&max=9.0
		return null;//movieService.searchByRating(Double.parseDouble(min),Double.parseDouble(max));
	}*/

}
