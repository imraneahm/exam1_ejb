package clientDesktop;

import webclient.beans.Service.CDService;
import webclient.entites.CD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddCDDialog extends JDialog {
    private JTextField titleField;
    private JTextField authorField;
    private CDService cdService;

    public AddCDDialog(Frame parent, CDService cdService) {
        super(parent, "Ajouter un CD", true);
        this.cdService = cdService;

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Titre:"));
        titleField = new JTextField(20);
        add(titleField);

        add(new JLabel("Auteur:"));
        authorField = new JTextField(20);
        add(authorField);

        JButton saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(this::saveCD);
        add(saveButton);

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    private void saveCD(ActionEvent e) {
        String title = titleField.getText();
        String author = authorField.getText();
        CD newCD = new CD(title, author, "disponible");
        cdService.addCD(newCD);
        dispose();
    }
}
