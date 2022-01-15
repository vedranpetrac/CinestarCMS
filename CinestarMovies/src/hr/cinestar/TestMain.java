/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar;

import hr.cinestar.model.Movie;
import hr.cinestar.parsers.rss.MoviesParsers;
import java.io.IOException;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Vedran
 */
public class TestMain {
    public static void main(String args[]) throws IOException, XMLStreamException{
        
        MoviesParsers.parse();
    }
}
