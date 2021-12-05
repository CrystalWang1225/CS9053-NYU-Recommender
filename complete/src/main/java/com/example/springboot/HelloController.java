package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//jsoup
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/scrape")
	public String scrape() throws  Exception{
		final Document document = Jsoup.connect("https://www.imdb.com/chart/top/").get();
		for (Element row:document.select("table.chart.full-width tr")){
			final String title= row.select(".titleColumn").text();
			final String rating = row.select(".imdbRating").text();
			System.out.println(title + "+ --> rating: "+ rating);
		}
		return "scraping";
	}

}
