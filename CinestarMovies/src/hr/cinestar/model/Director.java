/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

/**
 *
 * @author vedran
 */
public class Director {

    private int id;
    private String FirstName;
    private String LastName;

    public Director(int id, String FirstName, String LastName) {
        this.id = id;
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
}
