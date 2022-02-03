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
    
    /**
     * ******* GENERAL ********
     */
    
    void clearDatabase() throws Exception;

    /**
     * ******* ACCOUNT ********
     */
    
    int checkAccountData(String username, String pass) throws Exception;
    
    /**
     * ******* MOVIE ********
     */
    Optional<Movie> selectMovie(int id) throws Exception;

    List<Movie> selectMovies() throws Exception;

    int createMovie(Movie movie) throws Exception;

    void createMovies(List<Movie> movies) throws Exception;

    void updateMovie(int id, Movie data) throws Exception;

    void deleteMovie(int id) throws Exception;
    
    void deleteAllMovieCon(int movieId) throws Exception;

    /**
     * ******* DIRECTOR ********
     */
    Optional<Director> selectDirector(int id) throws Exception;

    List<Director> selectDirectors() throws Exception;

    int createDirector(Director director) throws Exception;

    void createDirectors(List<Director> directors) throws Exception;

    void updateDirector(int id, Director director) throws Exception;

    void deleteDirector(int id) throws Exception;

    /**
     * ******* ACTOR ********
     */
    Optional<Actor> selectActor(int id) throws Exception;

    List<Actor> selectActors() throws Exception;

    int createActor(Actor actor) throws Exception;

    void createActors(List<Actor> actors) throws Exception;

    void updateActor(int id, Actor data) throws Exception;

    void deleteActor(int id) throws Exception;

    /**
     * ******* GENRE ********
     */
    Optional<Genre> selectGenre(int id) throws Exception;

    List<Genre> selectGenres() throws Exception;

    int createGenre(Genre genre) throws Exception;

    void createGenres(List<Genre> genres) throws Exception;

    void updateGenre(int id, Genre data) throws Exception;

    void deleteGenre(int id) throws Exception;

    /**
     * ******* DIRECTOR -> MOVIE ********
     */
    //Optional<Genre> selectDirectorMovie(int id) throws Exception;
    List<Director> selectMovieDirectors(Movie movie) throws Exception;

    int createMovieDirectorConn(Director director, Movie movie) throws Exception;

    //void createMovieDirectors(List<Director> directors, Movie movie) throws Exception;

    void deleteMovieDirectorConn(int movieId, int directorId) throws Exception;
    
    void deleteAllDrectorCon(int directorId) throws Exception;

    /**
     * ******* ACTOR -> MOVIE ********
     */
    List<Actor> selectActorsMovie(Movie movie) throws Exception;

    int createMovieActorConn(Actor actor, Movie movie) throws Exception;

    //void createMovieActors(List<Actor> actors, Movie movie) throws Exception;

    void deleteMovieActorConn(int movieId, int actorId) throws Exception;
    void deleteAllActorCon(int actorId) throws Exception;

    /**
     * ******* GENRE -> MOVIE ********
     */
    List<Genre> selectMovieGenres(Movie movie) throws Exception;

    int createMovieGenreConn(Genre genre, Movie movie) throws Exception;

    //void createMovieGenres(List<Genre> genres, Movie movie) throws Exception;

    void deleteMovieGenreConn(int movieId, int genreId) throws Exception;
    void deleteAllGenreCon(int genreId) throws Exception;
}
