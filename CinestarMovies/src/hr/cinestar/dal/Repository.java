/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.cinestar.dal;

import hr.cinestar.model.Actor;
import hr.cinestar.model.Director;
import hr.cinestar.model.Genre;
import hr.cinestar.model.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Vedran
 */
public interface Repository {
    /*
    int createArticle(Article article) throws Exception;
    void createArticles(List<Article> articles) throws Exception;
    void updateArticle(int id, Article data) throws Exception;
    void deleteArticle(int id) throws Exception;
    Optional<Article> selectArticle(int id) throws Exception;
    List<Article> selectArticles() throws Exception;   
    */
    
    /********* MOVIE *********/
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;   
    int createMovie(Movie movie);
    void createMovies(List<Movie> movies);
    void updateMovie(int id, Movie data);
    void deleteMovie(int id);
    
    /********* DIRECTOR *********/
    Optional<Director> selectDirector(int id) throws Exception;
    List<Director> selectDirectors() throws Exception;   
    int createDirector (Director movie);
    void createDirectors (List<Director> directors);
    void updateDirector(int id, Movie data);
    void deleteDirector(int id);
    
    /********* ACTOR *********/
    Optional<Actor> selectActor(int id) throws Exception;
    List<Actor> selectActors() throws Exception;   
    int createActor (Actor actor);
    void createActors (List<Actor> actors);
    void updateActor(int id, Actor data);
    void deleteActor(int id);
    
    /********* GENRE *********/
    Optional<Genre> selectGenre(int id) throws Exception;
    List<Genre> selectGenres() throws Exception;   
    int createGenre (Genre genre);
    void createGenres (List<Genre> genres);
    void updateGenre(int id, Genre data);
    void deleteGenre(int id);
    
    /********* DIRECTOR -> MOVIE *********/
    
    
    /********* ACTOR -> MOVIE *********/
    
    
     /********* GENRE -> MOVIE *********/
    
}
