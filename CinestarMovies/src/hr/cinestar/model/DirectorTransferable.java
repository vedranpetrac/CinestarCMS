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
public class DirectorTransferable implements Transferable{
    public static final DataFlavor DIRECTOR_FLAVOR = new DataFlavor(Director.class, "Director");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {DIRECTOR_FLAVOR};

    private final Director director;

    public DirectorTransferable(Director director) {
        this.director = director;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DIRECTOR_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
       if (isDataFlavorSupported(flavor)) {
            return director;
        }
        throw new UnsupportedFlavorException(flavor);        
    }
}
