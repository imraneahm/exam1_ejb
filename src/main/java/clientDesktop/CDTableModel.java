package clientDesktop;

import webclient.entites.CD;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CDTableModel extends AbstractTableModel {
    private List<CD> cds;
    private final String[] columns = {"ID", "Titre", "Auteur", "Statut"};

    public CDTableModel(List<CD> cds) {
        this.cds = cds;
    }

    public void setCDs(List<CD> cds) {
        this.cds = cds;
        fireTableDataChanged();
    }

    public CD getCDAt(int row) {
        return cds.get(row);
    }

    @Override
    public int getRowCount() {
        return cds.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CD cd = cds.get(rowIndex);
        switch (columnIndex) {
            case 0: return cd.getId();
            case 1: return cd.getTitle();
            case 2: return cd.getAuthor();
            case 3: return cd.getStatus();
            default: return null;
        }
    }
}
