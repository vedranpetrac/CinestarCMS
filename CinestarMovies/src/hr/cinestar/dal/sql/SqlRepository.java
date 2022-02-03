/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.dal.sql;

import hr.cinestar.dal.Repository;
import hr.cinestar.model.Actor;
import hr.cinestar.model.Director;
import hr.cinestar.model.Genre;
import hr.cinestar.model.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Vedran
 */
public class SqlRepository implements Repository {

    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";

    private static final String CREATE_ACTOR = "{ CALL createActor (?,?,?) }";
    private static final String UPDATE_ACTOR = "{ CALL updateActor (?,?,?) }";
    private static final String DELETE_ACTOR = "{ CALL deleteActor (?) }";
    private static final String SELECT_ACTOR = "{ CALL selectActor (?) }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";

    private static final String CREATE_DIRECTOR = "{ CALL createDirector (?,?,?) }";
    private static final String UPDATE_DIRECTOR = "{ CALL updateDirector (?,?,?) }";
    private static final String DELETE_DIRECTOR = "{ CALL deleteDirector (?) }";
    private static final String SELECT_DIRECTOR = "{ CALL selectDirector (?) }";
    private static final String SELECT_DIRECTORS = "{ CALL selectDirectors }";

    private static final String ID_GENRE = "IDGenre";
    private static final String NAME_GENRE = "Name";
    private static final String CREATE_GENRE = "{ CALL createGenre (?,?) }";
    private static final String UPDATE_GENRE = "{ CALL updateGenre (?,?) }";
    private static final String DELETE_GENRE = "{ CALL deleteGenre (?) }";
    private static final String SELECT_GENRE = "{ CALL selectGenre (?) }";
    private static final String SELECT_GENRES = "{ CALL selectGenres }";

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt("IDMovie"),
                            rs.getString("Title"),
                            rs.getString("OriginalTitle"),
                            rs.getString("Description"),
                            rs.getInt("Duration"),
                            rs.getString("PicturePath")));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(new Movie(
                            rs.getInt("IDMovie"),
                            rs.getString("Title"),
                            rs.getString("OriginalTitle"),
                            rs.getString("Description"),
                            rs.getInt("Duration"),
                            rs.getString("PicturePath")));
                }
            }
        }
        return movies;
    }

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getOrigTitle());
            stmt.setString(3, movie.getDescription());
            stmt.setInt(4, movie.getDuration());
            stmt.setString(5, movie.getPicturePath());
            stmt.registerOutParameter(6, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(6);
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            for (Movie movie : movies) {
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getOrigTitle());
                stmt.setString(3, movie.getDescription());
                stmt.setInt(4, movie.getDuration());
                stmt.setString(5, movie.getPicturePath());
                stmt.registerOutParameter(6, Types.INTEGER);

                stmt.executeUpdate();

                movie.setId(stmt.getInt(6));
            }

        }
    }

    @Override
    public void updateMovie(int id, Movie movie) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            stmt.setInt(1, id);
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getOrigTitle());
            stmt.setString(4, movie.getDescription());
            stmt.setInt(5, movie.getDuration());
            stmt.setString(6, movie.getPicturePath());

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteMovie(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public Optional<Director> selectDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Director(
                            rs.getInt("IDDirector"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")));

                }
            }

        }

        return Optional.empty();
    }

    @Override
    public List<Director> selectDirectors() throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    directors.add(new Director(
                            rs.getInt("IDDirector"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")));

                }
            }

        }

        return directors;
    }

    @Override
    public int createDirector(Director director) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            stmt.setString(1, director.getFirstName());
            stmt.setString(2, director.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void createDirectors(List<Director> directors) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            for (Director director : directors) {
                stmt.setString(1, director.getFirstName());
                stmt.setString(2, director.getLastName());
                stmt.registerOutParameter(3, Types.INTEGER);

                stmt.executeUpdate();

                director.setId(stmt.getInt(3));
            }

        }
    }

    @Override
    public void updateDirector(int id, Director director) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR)) {
            stmt.setInt(1, id);
            stmt.setString(2, director.getFirstName());
            stmt.setString(3, director.getLastName());

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteDirector(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public Optional<Actor> selectActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTOR)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Actor(
                            rs.getInt("IDActor"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Actor> selectActors() throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new Actor(
                            rs.getInt("IDActor"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")));

                }
            }
        }
        return actors;
    }

    @Override
    public int createActor(Actor actor) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void createActors(List<Actor> actors) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            for (Actor actor : actors) {
                stmt.setString(1, actor.getFirstName());
                stmt.setString(2, actor.getLastName());
                stmt.registerOutParameter("Id", Types.INTEGER);
                stmt.executeUpdate();

                actor.setId(stmt.getInt("Id"));
            }

        }
    }

    @Override
    public void updateActor(int id, Actor actor) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {
            stmt.setInt(1, id);
            stmt.setString(2, actor.getFirstName());
            stmt.setString(3, actor.getLastName());

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteActor(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public Optional<Genre> selectGenre(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRE)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Genre(
                            rs.getInt(ID_GENRE),
                            rs.getString(NAME_GENRE)));
                }
            }

        }

        return Optional.empty();
    }

    @Override
    public List<Genre> selectGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRES)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    genres.add(new Genre(
                            rs.getInt("IDGenre"),
                            rs.getString("Name")));

                }
            }
        }
        return genres;
    }

    @Override
    public int createGenre(Genre genre) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {
            stmt.setString(1, genre.getName());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2);
        }

    }

    @Override
    public void createGenres(List<Genre> genres) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {
            for (Genre genre : genres) {
                stmt.setString(1, genre.getName());

                stmt.registerOutParameter(2, Types.INTEGER);

                stmt.executeUpdate();

                genre.setId(stmt.getInt(2));
            }

        }
    }

    @Override
    public void updateGenre(int id, Genre genre) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_GENRE)) {
            stmt.setInt(1, id);
            stmt.setString(2, genre.getName());

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteGenre(int id) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_GENRE)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public List<Director> selectMovieDirectors(Movie movie) throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL SelectMovieDirectors (?)}")) {

            stmt.setInt(1, movie.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    directors.add(new Director(
                            rs.getInt("IDDirector"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")));

                }
            }

        }

        return directors;
    }

    @Override
    public int createMovieDirectorConn(Director director, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL CreateMovieDirector (?,?,?)} ")) {
            stmt.setInt(1, movie.getId());
            stmt.setInt(2, director.getId());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void deleteMovieDirectorConn(int movieId, int directorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteMovieDirector (?,?) }")) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, directorId);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Actor> selectActorsMovie(Movie movie) throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL SelectMovieActors (?)}")) {
            stmt.setInt(1, movie.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new Actor(
                            rs.getInt("IDActor"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")));

                }
            }
        }
        return actors;
    }

    @Override
    public int createMovieActorConn(Actor actor, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL CreateMovieActor (?,?,?)} ")) {
            stmt.setInt(1, movie.getId());
            stmt.setInt(2, actor.getId());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void deleteMovieActorConn(int movieId, int actorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteMovieActor (?,?) }")) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, actorId);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Genre> selectMovieGenres(Movie movie) throws Exception {
        List<Genre> genres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL SelectMovieGenres (?)}")) {
            stmt.setInt(1, movie.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    genres.add(new Genre(
                            rs.getInt("IDGenre"),
                            rs.getString("Name")));

                }
            }
        }
        return genres;
    }

    @Override
    public int createMovieGenreConn(Genre genre, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL CreateMovieGenre (?,?,?) }")) {
            stmt.setInt(1, movie.getId());
            stmt.setInt(2, genre.getId());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void deleteMovieGenreConn(int movieId, int genreId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteMovieGenre (?,?) }")) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, genreId);

            stmt.executeUpdate();
        }
    }

    @Override
    public int checkAccountData(String username, String pass) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL checkAccountAuth (?,?,?) }")) {
            stmt.setString(1, username);
            stmt.setString(2, pass);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void clearDatabase() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL cleanDatabase }")) {
            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteAllMovieCon(int movieId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteMovieCon (?) }")) {

            stmt.setInt(1, movieId);

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteAllDrectorCon(int directorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteAllDiractorsCon (?) }")) {

            stmt.setInt(1, directorId);

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteAllActorCon(int actorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteAllActorsConn (?) }")) {

            stmt.setInt(1, actorId);

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteAllGenreCon(int genreId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteAllGenresCon (?) }")) {

            stmt.setInt(1, genreId);

            stmt.executeUpdate();

        }
    }

}
