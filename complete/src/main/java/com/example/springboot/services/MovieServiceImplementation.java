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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("movieService")
public class MovieServiceImplementation implements MovieService{
    // imdb URL links for each genre
    final String imdb_comedy_link = "https://www.imdb.com/search/title/?genres=comedy&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=3396781f-d87f-4fac-8694-c56ce6f490fe&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr1_i_1";
    final String imdb_romance_link = "https://www.imdb.com/search/title/?genres=romance&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=e0da8c98-35e8-4ebd-8e86-e7d39c92730c&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-2&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr2_i_1";
    final String imdb_scifi_link = "https://www.imdb.com/search/title/?genres=sci-fi&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=3396781f-d87f-4fac-8694-c56ce6f490fe&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr1_i_2";
    final String imdb_action_link = "https://www.imdb.com/search/title/?genres=action&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=e0da8c98-35e8-4ebd-8e86-e7d39c92730c&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-2&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr2_i_2";
    final String imdb_drama_link = "https://www.imdb.com/search/title/?genres=drama&explore=title_type,genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=f1cf7b98-03fb-4a83-95f3-d833fdba0471&pf_rd_r=3SXPNY4EJJ8DB24QT8S3&pf_rd_s=center-3&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_pr3_i_1";
    //INDEX that maps genres to genre array which is used to initialize the
    final int COMEDY_INDEX = 0;
    final int SCIFI_INDEX = 1;
    final int HORROR_INDEX = 2;
    final int ROMANCE_INDEX = 3;
    final int ACTION_INDEX = 4;
    final int THRILLER_INDEX = 5;
    final int DRAMA_INDEX = 6;
    final int MYSTERY_INDEX = 7;
    final int CRIME_INDEX = 8;


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
        String[] genre_lst;
        HashSet<String> genre_set = new HashSet<String>();
        Boolean[] genre_array;
        Boolean is_comedy;Boolean is_scifi;Boolean is_horror;
        Boolean is_romance;Boolean is_action;Boolean is_thriller;
        Boolean is_drama; Boolean is_mystery;Boolean is_crime;
      //  Movie tmp;
        try {
            document_romance = Jsoup.connect(url_link).get();
            for (Element row:document_romance.getElementsByClass("lister-item-content")){
                title= row.select(".lister-item-header").select("a[href]").text();
                genre= row.select(".genre").text();
                //parse genres
                genre_lst = genre.split(",");
                System.out.println("genres:");
                for (int i =0; i<=genre_lst.length-1; i++ ){
                  //  System.out.println(genre_lst[i].trim().toLowerCase() );
                    // add the genre to set
                    genre_set.add(genre_lst[i].trim().toLowerCase() );
                }
                //generate genre array which will be used for new Movie initialization
                genre_array = generate_genre_array(genre_set);
                System.out.println("traversing set:");
                for (String gnr:genre_set){
                    System.out.println(gnr);
                }
                //initializing fields for movie object
                 is_comedy = genre_array[COMEDY_INDEX];
                 is_scifi = genre_array[SCIFI_INDEX];
                 is_horror = genre_array[HORROR_INDEX];
                 is_romance = genre_array[ROMANCE_INDEX];
                 is_action = genre_array[ACTION_INDEX];
                 is_thriller = genre_array[THRILLER_INDEX];
                 is_drama = genre_array[DRAMA_INDEX];
                 is_mystery = genre_array[MYSTERY_INDEX];
                 is_crime = genre_array[CRIME_INDEX];
                //
                rating_str= row.select(".ratings-imdb-rating").attr("data-value");
               //manually assigning a rating for the movies that dont have a rating for now
                if (rating_str.length() == 0){
                    rating_str = "5.0";
                }
                rating = Double.parseDouble(rating_str);
                System.out.println(title + " genre: " + genre + " rating: " + rating  );
                //save to database
                save( new Movie(title, rating, is_comedy, is_scifi, is_horror, is_romance, is_action, is_thriller,
                        is_drama, is_mystery, is_crime));

                System.out.println("Saved");
                genre_set.clear();//clear the set for next movie
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private Boolean[] generate_genre_array(HashSet<String> genre_set){
        Boolean[] genre_array = new Boolean[9];
        for(int  i=0; i<genre_array.length; i++){
            genre_array[i] = false;
        }
        //if the genre is present in the set, change the value in array at the
        // index assosicated with the genre
        if (genre_set.contains("comedy")){
            genre_array[COMEDY_INDEX] = true;
        }
        if (genre_set.contains("sci-fi")){
            genre_array[SCIFI_INDEX] = true;
        }
        if (genre_set.contains("horror")){
            genre_array[HORROR_INDEX] = true;
        }
        if (genre_set.contains("romance")){
            genre_array[ROMANCE_INDEX] = true;
        }
        if (genre_set.contains("action")){
            genre_array[ACTION_INDEX] = true;
        }
        if (genre_set.contains("thriller")){
            genre_array[THRILLER_INDEX] = true;
        }
        if (genre_set.contains("drama")){
            genre_array[DRAMA_INDEX] = true;
        }
        if (genre_set.contains("mystery")){
            genre_array[MYSTERY_INDEX] = true;
        }if (genre_set.contains("crime")){
            genre_array[CRIME_INDEX] = true;
        }


        return genre_array;
    }

}
