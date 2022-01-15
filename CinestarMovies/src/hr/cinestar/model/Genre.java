/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

/**
 *
 * @author Vedran
 */
public class Genre {
    private int id;
    private String Name;

    public Genre(int id, String Name) {
        this.id = id;
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
    
    
}
