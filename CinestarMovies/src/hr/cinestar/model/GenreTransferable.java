/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Vedran
 */
public class GenreTransferable implements Transferable{
    public static final DataFlavor GENRE_FLAVOR = new DataFlavor(Director.class, "Genre");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {GENRE_FLAVOR};

    private final Genre genre;

    public GenreTransferable(Genre genre) {
        this.genre = genre;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(GENRE_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
       if (isDataFlavorSupported(flavor)) {
            return genre;
        }
        throw new UnsupportedFlavorException(flavor);        
    }
}
