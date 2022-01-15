/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author vedran
 */
public class Movie {

    public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private int id;
    private String title;
    private String origTitle;
    private String link;
    private String description;
    private String picturePath;
    private LocalDateTime publishedDate;
    private LocalDateTime liveDate;
    private String Duration;
    private List<Actor> actors;
    private List<Director> directors;
    private List<Genre> genres;

    public Movie(int id, String title, String origTitle, String link, String description, String picturePath, LocalDateTime publishedDate, LocalDateTime liveDate, String duration, List<Actor> actors, List<Director> directors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.origTitle = origTitle;
        this.link = link;
        this.description = description;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.liveDate = liveDate;
        this.Duration = duration;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public Movie(String title, String link, String description, String picturePath, LocalDateTime publishedDate, LocalDateTime liveDate, String duration, List<Actor> actors, List<Director> directors, List<Genre> genres) {
        this.title = title;
        this.origTitle = origTitle;
        this.link = link;
        this.description = description;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.liveDate = liveDate;
        this.Duration = duration;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public Movie() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDateTime getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(LocalDateTime liveDate) {
        this.liveDate = liveDate;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {

        return id + " - " + title;

    }

    public String getOrigTitle() {
        return origTitle;
    }

    public void setOrigTitle(String origTitle) {
        this.origTitle = origTitle;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

}
