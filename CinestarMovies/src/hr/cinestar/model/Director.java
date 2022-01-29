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
 * @author vedran
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Director implements Comparable<Director>{

    @XmlAttribute
    private int id;
    private String FirstName;
    private String LastName;

    public Director(int id, String FirstName, String LastName) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public Director(String FirstName, String LastName) {
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public Director() {

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
    public int compareTo(Director o) {
        return Integer.toString(id).compareTo(Integer.toString(o.id));
    }

}
