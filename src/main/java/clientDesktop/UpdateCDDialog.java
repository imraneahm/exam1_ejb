package clientDesktop;

import webclient.beans.Service.CDService;
import webclient.entites.CD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UpdateCDDialog extends JDialog {
    private JTextField titleField;
    private JTextField authorField;
    private CDService cdService;
    private CD cd;

    public UpdateCDDialog(Frame parent, CDService cdService, CD cd) {
        super(parent, "Modifier un CD", true);
        this.cdService = cdService;
        this.cd = cd;

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Titre:"));
        titleField = new JTextField(cd.getTitle(), 20);
        add(titleField);

        add(new JLabel("Auteur:"));
        authorField = new JTextField(cd.getAuthor(), 20);
        add(authorField);

        JButton saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(this::saveChanges);
        add(saveButton);

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    private void saveChanges(ActionEvent e) {
        cd.setTitle(titleField.getText());
        cd.setAuthor(authorField.getText());
        cdService.updateCD(cd);
        dispose();
    }
}
