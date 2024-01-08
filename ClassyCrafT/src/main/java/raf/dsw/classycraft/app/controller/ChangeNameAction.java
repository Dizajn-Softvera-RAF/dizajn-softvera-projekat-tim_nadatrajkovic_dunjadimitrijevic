package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.view.AddAutorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeNameAction implements ActionListener{
    Project project;
    String tekst;
    AddAutorWindow dialog;

    public ChangeNameAction(AddAutorWindow dialog, Project project) {
        this.project = project;

        this.dialog=dialog;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.tekst = dialog.textFromField();

        project.setImeAutora(tekst);

        Component component = (Component) e.getSource();
        JDialog dialog1 = (JDialog) SwingUtilities.getRoot(component);
        dialog1.dispose();
        dialog.dispose();
    }
}
