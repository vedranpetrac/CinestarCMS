/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author vedran
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Actor implements Comparable<Actor>{
    @XmlAttribute 
    private int id;
    @XmlElement(name = "firstname")
    private String FirstName;
    @XmlElement(name = "lastname")
    private String LastName;

    public Actor(int id, String FirstName, String LastName) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public Actor(String FirstName, String LastName) {
        this.FirstName = FirstName;
        this.LastName = LastName;
    }
    
    

    public Actor() {
        
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Actor a) {
        return Integer.toString(id).compareTo(Integer.toString(a.id));
    }
}
