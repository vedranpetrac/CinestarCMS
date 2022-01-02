/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.parsers;

import hr.cinestar.model.Movie;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Vedran
 */
public class MoviesParses {
    private static final String RSS_URL = "https://slobodnadalmacija.hr/feed";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    private static void handlePicture(Movie article, String url) {
        
        try {
            String ext = url.substring(url.lastIndexOf("."));
            if(ext.length() > 4){
                ext = EXT;
            }
            
            String pictureName = UUID.randomUUID() + ext;
            String path = DIR + File.separator + pictureName;
            
            FileUtils.copyFromUrl(url, path);
            
            article.setPicturePath(path);
        } catch (IOException ex) {
            Logger.getLogger(MoviesParses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       }

    private MoviesParses() {
    }

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> articles = new ArrayList<>();
        
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        
        try(InputStream is = con.getInputStream()){
            XMLEventReader reader = ParserFactory.createStaxParser(is);
            
            StartElement startElement = null;
            Article article = null;
            Optional<TagType> tagType = Optional.empty();
            while(reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                
                switch(event.getEventType()){
                    case XMLStreamConstantsXMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        break;
                    case XMLStreamConstantsXMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();
                        if(tagType.isPresent()){
                            switch(tagType.get()) {
                        case ITEM:
                            article = new Article();
                            articles.add(article);
                            break;
                        case TITLE:
                            if(article != null && !data.isEmpty()){
                                article.setTitle(data);
                            }
                            break;
                        case LINK:
                            if(article != null && !data.isEmpty()){
                                article.setLink(data);
                            }
                            break;
                        case DESCRIPTION:
                            if(article != null && !data.isEmpty()){
                                article.setDescription(data);
                            }
                            break;
                        case ENCLOSURE:
                            if(article != null && startElement != null && article.getPicturePath() == null){
                                Attribute urlAtt = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
                                if(urlAtt != null ){
                                    handlePicture(article, urlAtt.getValue());
                                }
                            }
                            break;
                        case PUB_DATE:
                            if(article != null && !data.isEmpty()){
                               LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                               article.setPublishedDate(publishedDate);
                            }
                            break;
                                
                            }
                        }
                        break;
                }
            }
        }
         
        
        return articles;
    }
    
    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESCRIPTION("description"),
        ENCLOSURE("enclosure"),
        PUB_DATE("pubDate");
        
        private final String name;

        private TagType(String name) {
            this.name = name;
        }
        
        public static Optional<TagType> from(String name){
        
            for (TagType value : TagType.values()) {
                if(value.name.equals(name)){
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    
    }
}
