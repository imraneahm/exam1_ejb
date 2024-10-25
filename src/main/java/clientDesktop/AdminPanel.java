package clientDesktop;

import webclient.beans.Service.CDService;
import webclient.entites.CD;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminPanel extends JFrame {
    private CDService cdService;
    private JTable cdTable;
    private CDTableModel cdTableModel;

    public AdminPanel() {
        setTitle("Gestion des CDs - Admin");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialiser le service EJB
        try {
            cdService = (CDService) new InitialContext().lookup("java:global/exam_1/CDService");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        cdTableModel = new CDTableModel(cdService.listAvailableCDs());
        cdTable = new JTable(cdTableModel);

        // Boutons pour ajouter, modifier, supprimer
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Ajouter CD");
        JButton updateButton = new JButton("Modifier CD");
        JButton deleteButton = new JButton("Supprimer CD");

        addButton.addActionListener(this::addCD);
        updateButton.addActionListener(this::updateCD);
        deleteButton.addActionListener(this::deleteCD);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(new JScrollPane(cdTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addCD(ActionEvent e) {
        AddCDDialog dialog = new AddCDDialog(this, cdService);
        dialog.setVisible(true);
        refreshCDList();
    }

    private void updateCD(ActionEvent e) {
        int selectedRow = cdTable.getSelectedRow();
        if (selectedRow >= 0) {
            CD selectedCD = cdTableModel.getCDAt(selectedRow);
            UpdateCDDialog dialog = new UpdateCDDialog(this, cdService, selectedCD);
            dialog.setVisible(true);
            refreshCDList();
        }
    }

    private void deleteCD(ActionEvent e) {
        int selectedRow = cdTable.getSelectedRow();
        if (selectedRow >= 0) {
            CD selectedCD = cdTableModel.getCDAt(selectedRow);
            cdService.deleteCD(selectedCD.getId());
            refreshCDList();
        }
    }

    private void refreshCDList() {
        List<CD> cds = cdService.listAvailableCDs();
        cdTableModel.setCDs(cds);
    }
}
