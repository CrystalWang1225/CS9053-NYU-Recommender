package com.example.springboot.services;

import com.example.springboot.Movie;
import com.example.springboot.MovieRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.List;

@Service("movieService")
public class MovieServiceImplementation implements MovieService{
    // imdb URL links for each genre
    final String imdb_comedy_link = "https://www.imdb.com/search/title/?genres=comedy&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=3396781f-d87f-4fac-8694-c56ce6f490fe&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr1_i_1";
    final String imdb_romance_link = "https://www.imdb.com/search/title/?genres=romance&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=e0da8c98-35e8-4ebd-8e86-e7d39c92730c&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-2&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr2_i_1";
    final String imdb_scifi_link = "https://www.imdb.com/search/title/?genres=sci-fi&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=3396781f-d87f-4fac-8694-c56ce6f490fe&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr1_i_2";
    final String imdb_action_link = "https://www.imdb.com/search/title/?genres=action&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=e0da8c98-35e8-4ebd-8e86-e7d39c92730c&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-2&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr2_i_2";
    final String imdb_drama_link = "https://www.imdb.com/search/title/?genres=drama&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=f1cf7b98-03fb-4a83-95f3-d833fdba0471&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-3&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr3_i_1";


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
    private List<Movie> allMovies;
    private List<String>  allMovies_str;

    public boolean fetchIMDbMoviesAndSave(){
        //scraping each genre seperately and printing the results  out
        System.out.println("starting romance movies");
        scrape_url(imdb_comedy_link);
        System.out.println("-----------------------------------------------------------------------");
       /* scrape_url(imdb_romance_link);
        System.out.println("-----------------------------------------------------------------------");
        scrape_url(imdb_scifi_link);
        System.out.println("-----------------------------------------------------------------------");
        scrape_url(imdb_action_link);
        System.out.println("-----------------------------------------------------------------------");
        scrape_url(imdb_drama_link);
        System.out.println("-----------------------------------------------------------------------");*/
      //  System.out.println(allMovies_str==null);
     //   System.out.println(allMovies_str.size());
        //System.out.println(allMovies==null);
        return true;
    }

    public void scrape_url(String url_link)  {
        // given a imdb URL address that contains movies for specific genre,
        // this method scrapes the movies and prints out the result for each movie
        final Document document_romance;
        //global variables
        String title, genre, rating_str;
        double rating;
        Movie temp_movie;
        try {
            document_romance = Jsoup.connect(url_link).get();
            for (Element row:document_romance.getElementsByClass("lister-item-content")){
                title= row.select(".lister-item-header").select("a[href]").text();
                genre= row.select(".genre").text();
                rating_str= row.select(".ratings-imdb-rating").attr("data-value");
                rating = Double.parseDouble(rating_str);
                System.out.println(title + " genre: " + genre + " rating: " + rating  );
                //save to database
                save(new Movie(title, rating));
                System.out.println("Saved");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
