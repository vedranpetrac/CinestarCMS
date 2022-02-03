/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author vedran
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title", "origTitle", "description","duration", "picturePath", "actors", "directors", "genres"})
public class Movie {

    @XmlAttribute
    private int id;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "originalTitle")
    private String origTitle;
    @XmlElement(name = "Description")
    private String description;
    @XmlElement(name = "Duration")
    private int duration;
    @XmlElement(name = "picturePath")
    private String picturePath;
    @XmlElementWrapper
    @XmlElement(name = "actor")
    private List<Actor> actors;
    @XmlElementWrapper
    @XmlElement(name = "director")
    private List<Director> directors;
    @XmlElementWrapper
    @XmlElement(name = "genre")
    private List<Genre> genres;

    public Movie(int id, String title, String origTitle, String description, int duration, String picturePath, List<Actor> actors, List<Director> directors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.origTitle = origTitle;
        this.description = description;
        this.duration = duration;
        this.picturePath = picturePath;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public Movie(int id, String title, String origTitle, String description, int duration, String picturePath) {
        this.id = id;
        this.title = title;
        this.origTitle = origTitle;
        this.description = description;
        this.duration = duration;
        this.picturePath = picturePath;
    }

    public Movie(String title, String origTitle, String description, int duration, String picturePath) {
        this.title = title;
        this.origTitle = origTitle;
        this.description = description;
        this.duration = duration;
        this.picturePath = picturePath;
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

    public String getOrigTitle() {
        return origTitle;
    }

    public void setOrigTitle(String origTitle) {
        this.origTitle = origTitle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {

        return id + " - " + title;

    }

    public void setId(int id) {
        this.id = id;
    }

}
