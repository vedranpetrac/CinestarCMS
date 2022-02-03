/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Vedran
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Genre implements Comparable<Genre>{
    @XmlAttribute 
    private int id;
    private String Name;

    public Genre(int id, String Name) {
        this.id = id;
        this.Name = Name;
    }

    public Genre(String Name) {
        this.Name = Name;
    }

    public Genre() {
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Genre o) {
        return Integer.toString(id).compareTo(Integer.toString(o.id));
    }
    
    
}
