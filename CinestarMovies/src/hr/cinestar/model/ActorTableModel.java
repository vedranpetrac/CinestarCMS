/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vedran
 */
public class ActorTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Id", "First Name", "Last Name"};

    private List<Actor> actors;

    public ActorTableModel(List<Actor> actors) {
        this.actors = actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return actors.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return actors.get(rowIndex).getId();
            case 1:
                return actors.get(rowIndex).getFirstName();
            case 2:
                return actors.get(rowIndex).getLastName();
            default:
                throw new RuntimeException("No such column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

}
