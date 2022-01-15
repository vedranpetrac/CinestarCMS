/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.cinestar.model.Movie;
import hr.algebra.utils.FileUtils;
import hr.cinestar.model.Actor;
import hr.cinestar.model.Director;
import hr.cinestar.model.Genre;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Vedran
 */
public class MoviesParsers {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";
    private static final String europeanDatePattern = "dd.MM.yyyy";

    private static void handlePicture(Movie movie, String url) {

        try {
            String ext = url.substring(url.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }

            String pictureName = UUID.randomUUID() + ext;
            String path = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(url, path);

            movie.setPicturePath(path);
        } catch (IOException ex) {
            Logger.getLogger(MoviesParsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        ORIG_NAME("orignaziv"),
        DIRECTORS("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        GENRE("zanr"),
        COVER("plakat"),
        START_DATE("pocetak");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        public static Optional<TagType> from(String name) {

            for (TagType value : TagType.values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }

    }

    private MoviesParsers() {

    }

    public static List<Movie> parse() throws IOException, XMLStreamException {

        List<Movie> movies = new ArrayList<>();

        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);

        try (InputStream is = con.getInputStream()) {

            XMLEventReader reader = ParserFactory.createStaxParser(is);

            StartElement startElement = null;
            Movie movie = null;
            Optional<TagType> tagType = Optional.empty();

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();
                        if (tagType.isPresent()) {
                            switch (tagType.get()) {
                                case ITEM:
                                    movie = new Movie();
                                    movies.add(movie);
                                    break;
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUB_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                        movie.setPublishedDate(publishedDate);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDescription(data);
                                    }
                                    break;
                                case ORIG_NAME:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setOrigTitle(data);
                                    }
                                    break;
                                case DIRECTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        List<Director> directors = new ArrayList<>();

                                        String[] rawNames = data.split(",");

                                        for (String name : rawNames) {

                                            Director director = new Director();
                                            
                                            if (data.split("\\w+").length > 1) {
                                                director.setFirstName(name.substring(0, name.lastIndexOf(" ")));
                                                director.setLastName(name.substring(name.lastIndexOf(" ") + 1));
                                            } else {
                                                director.setFirstName(name);
                                            }
                                            directors.add(director);
                                        }
                                        
                                        movie.setDirectors(directors);
                                    }
                                    break;
                                case ACTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        List<Actor> actors = new ArrayList<>();

                                        String[] rawNames = data.split(",");

                                        for (String name : rawNames) {

                                            Actor actor = new Actor();
                                            
                                            if (data.split("\\w+").length > 1) {
                                                actor.setFirstName(name.substring(0, name.lastIndexOf(" ")));
                                                actor.setLastName(name.substring(name.lastIndexOf(" ") + 1));
                                            } else {
                                                actor.setFirstName(name);
                                            }
                                            actors.add(actor);
                                        }
                                        
                                        movie.setActors(actors);
                                    }
                                    break;
                                case DURATION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDuration(data);
                                    }
                                    break;
                                case GENRE:
                                    if (movie != null && !data.isEmpty()) {
                                        List<Genre> genres = new ArrayList<>();

                                        String[] rawNames = data.split(",");

                                        for (String name : rawNames) {

                                            Genre genre = new Genre();
                                            genre.setName(name);
                                            genres.add(genre);
                                        }
                                        
                                        movie.setGenres(genres);
                                    }
                                    break;
                                case COVER:
                                    /*
                                    if (movie != null && !data.isEmpty() && movie.getPicturePath() == null) {                                       
                                            handlePicture(movie, data);
                                    }*/
                                    break;
                                case START_DATE:
                                    /*
                                    if (movie != null && !data.isEmpty()) {
                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.ofPattern(europeanDatePattern));
                                        
                                        movie.setPublishedDate(publishedDate);
                                    }*/
                                    break;
                            }

                        }
                        break;
                }
            }
        }

        return movies;
    }

}
